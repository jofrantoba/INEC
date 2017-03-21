package com.inec.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.inec.uicapturarfoto.UiCapturarFoto;

import java.io.File;
import java.io.IOException;

import static com.inec.uicapturarfoto.UiCapturarFoto.CAMERA_REQUEST;
import static com.inec.uicapturarfoto.UiCapturarFotoImpl.fotoVisible;

/**
 * Created by root on 12/10/16.
 */

public class ColocarImagen {

//    public static String resourceImagen=null;
//    public static void TomarFoto(Activity activity){
//        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        String resource="image_"+new java.util.Date().getTime()+".jpg";
//        resourceImagen=resource;
//        File image = getFileImage();
//        Uri uriSavedImage = Uri.fromFile(image);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
//        activity.startActivity(intent);
//        activity.startActivityForResult(intent, CAMERA_REQUEST);
//    }
//
//    public static File getFileImage(){
//        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "InecImages");
//        if (!imagesFolder.exists()) imagesFolder.mkdirs();
//        File image = new File(imagesFolder, resourceImagen);
////        resourcesFile=image;
//        return image;
//    }
//
//    public static Bitmap VerImagen(Bitmap bm){
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        ExifInterface exif=null;
//        File image=getFileImage();
//        long length = image.length();
//        length = length/1024;
//        if(length>2000) {
//            options.inSampleSize = 6;
//        }else if(length>1000) {
//            options.inSampleSize = 4;
//        }else if(length>700) {
//            options.inSampleSize = 3;
//        }else if(length>250) {
//            options.inSampleSize = 2;
//        }
//        bm=BitmapFactory.decodeFile(image.getPath(),options);
//        int orientation=0;
//        int angle=0;
//
//        try {
//            exif = new ExifInterface(image.getPath());
//            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        switch(orientation) {
//            case ExifInterface.ORIENTATION_ROTATE_90:
//                angle= 90;
//                break;
//            case ExifInterface.ORIENTATION_ROTATE_180:
//                angle= 180;
//                break;
//            case ExifInterface.ORIENTATION_ROTATE_270:
//                angle=270;
//                break;
//        }
//        Matrix mat = new Matrix();
//        mat.postRotate(angle);
//        bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), mat, true);
//        return bm;
////        imageView.setImageBitmap(bm);
////        fotoVisible=true;
//    }
}
