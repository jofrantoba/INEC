package com.inec.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import inec.com.R;

/**
 * Created by CARLOS on 27/09/2016.
 */
public class BadgeDrawable extends Drawable {

    private Paint mBadgePaint;
    private Paint mBadgePaint1;
    private Paint mTextPaint;
    private Rect mTxtRect = new Rect();

    private String mCount = "";
    private boolean mWillDraw;

    public BadgeDrawable(Context context) {
        float mTextSize = context.getResources().getDimension(R.dimen.badge_text_size);

        mBadgePaint = new Paint();
        mBadgePaint.setColor(Color.parseColor("#ff9800"));
        mBadgePaint.setAntiAlias(true);
        mBadgePaint.setStyle(Paint.Style.FILL);
        mBadgePaint1 = new Paint();
        mBadgePaint1.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.colorAccent));
        mBadgePaint1.setAntiAlias(true);
        mBadgePaint1.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTypeface(Typeface.DEFAULT);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void draw(Canvas canvas) {

        if (!mWillDraw) {
            return;
        }
        Rect bounds = getBounds();
        float width = bounds.right - bounds.left;
        float height = bounds.bottom - bounds.top;

        // Position the badge in the top-right quadrant of the icon.

         /*Using Math.max rather than Math.min */

        float radius = ((Math.max(width, height) / 2)) / 2;
        float centerX = (width - radius - 1) +5;
        float centerY = radius -5;
        if(mCount.length() <= 2){
            canvas.drawCircle(centerX, centerY, (int)(radius+4), mBadgePaint1);
            canvas.drawCircle(centerX, centerY, (int)(radius+2), mBadgePaint);
        }else{
            canvas.drawCircle(centerX, centerY, (int)(radius+4), mBadgePaint1);
            canvas.drawCircle(centerX, centerY, (int)(radius+2), mBadgePaint);
        }

        mTextPaint.getTextBounds(mCount, 0, mCount.length(), mTxtRect);
        float textHeight = mTxtRect.bottom - mTxtRect.top;
        float textY = centerY + (textHeight / 2f);
        if(mCount.length() > 2)
            canvas.drawText("99+", centerX, textY, mTextPaint);
        else
            canvas.drawText(mCount, centerX, textY, mTextPaint);
    }

    /*
       Sets the count (i.e notifications) to display.
        */
    public void setCount(String count) {
        mCount = count;

        // Only draw a badge if there are notifications.
        mWillDraw = !count.equalsIgnoreCase("0");
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {
        // do nothing
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        // do nothing
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}