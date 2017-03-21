package com.inec.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

public class FeedImageViewGallery extends ImageView {

    public Bitmap myBitmap;
    public interface ResponseObserver {
        public void onError();
        public void onSuccess();
    }

    private ResponseObserver mObserver;

    public void setResponseObserver(ResponseObserver observer) {
        this.mObserver = observer;
    }

    private String mUrl;

    private int mDefaultImageId;

    private int mErrorImageId;

    private ImageLoader mImageLoader;

    private ImageLoader.ImageContainer mImageContainer;
    private Context context;

    public FeedImageViewGallery(Context context) {
        super(context);
        this.context=context;
    }

    public FeedImageViewGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public FeedImageViewGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
    }

    public void setImageUrl(String url, ImageLoader loader) {
        mUrl = url;
        mImageLoader = loader;
        loadImageIfNecessary(false);
    }

    public void setDefaultImageResId(int defaultImageResId) {
        mDefaultImageId = defaultImageResId;
    }

    public void setErrorImageResId(int errorImageResId) {
        mErrorImageId = errorImageResId;
    }

    private void loadImageIfNecessary(final boolean isInLayoutPass) {
        final int width = getWidth();
        int height = getHeight();

        boolean isFullyWrapContent = getLayoutParams() != null
                && getLayoutParams().height == ViewGroup.LayoutParams.MATCH_PARENT
                && getLayoutParams().width == ViewGroup.LayoutParams.MATCH_PARENT;

        if (width == 0 && height == 0 && !isFullyWrapContent) {
            return;
        }

        if (TextUtils.isEmpty(mUrl)) {
            if (mImageContainer != null) {
                mImageContainer.cancelRequest();
                mImageContainer = null;
            }
            setDefaultImageOrNot();
            return;
        }

        if (mImageContainer != null && mImageContainer.getRequestUrl() != null) {
            if (mImageContainer.getRequestUrl().equals(mUrl)) {
                return;
            } else {
                mImageContainer.cancelRequest();
                setDefaultImageOrNot();
            }
        }

        ImageLoader.ImageContainer newContainer = mImageLoader.get(mUrl,
                new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(final ImageLoader.ImageContainer response, boolean isImmediate) {
                        if (isImmediate && isInLayoutPass) {
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    onResponse(response, false);
                                }
                            });
                            return;
                        }
                        int bWidth = 0, bHeight = 0;
                        Bitmap bitmap = response.getBitmap();
                        if (bitmap != null) {
                            Integer tam=getWidth();
                            if(bitmap.getHeight()>bitmap.getWidth()){
                                tam=500;
                            }
//                            bitmap=Bitmap.createScaledBitmap(bitmap,tam,getHeight(),true);
                            myBitmap=bitmap;
                            setImageBitmap(bitmap);
                            bWidth = bitmap.getWidth();
                            Log.v("ANCHOOO", bWidth+"");
                            bHeight = bitmap.getHeight();

//                            adjustImageAspect(bWidth, bHeight);
                        } else if (mDefaultImageId != 0) {
                            setImageResource(mDefaultImageId);
                        }

                        if (mObserver != null) {
                            mObserver.onSuccess();
                        }

                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (mErrorImageId != 0 ) {
                            setImageResource(mErrorImageId);
                        }
                        if (mObserver != null) {
                            mObserver.onError();
                        }
                    }
                });
        mImageContainer = newContainer;
    }

    private void setDefaultImageOrNot() {
        if (mDefaultImageId != 0) {
            setImageResource(mDefaultImageId);
        } else {
            setImageBitmap(null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        loadImageIfNecessary(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        //取消request
        if (mImageContainer != null) {
            mImageContainer.cancelRequest();
            setImageBitmap(null);
            mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    private void adjustImageAspect(int width, int height) {
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)getLayoutParams();
        if (width == 0 || height == 0) {
            return;
        }
        int sWidth = getWidth();
        int newHeight = sWidth * height / width;
        params.width = sWidth;
        params.height = newHeight;
        setLayoutParams(params);

    }

    public Bitmap getMyBitmap() {
        return myBitmap;
    }
}
