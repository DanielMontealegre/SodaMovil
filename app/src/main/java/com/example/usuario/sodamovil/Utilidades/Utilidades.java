package com.example.usuario.sodamovil.Utilidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Danel on 4/12/2017.
 */
/////////------------------------UTILIDADES---------------------------------------/////////
////////---------------------------------------------------------------------------/////////

public class Utilidades {




    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


    public static Bitmap getImageFromBlob(byte [] imgByte){
        return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
    }


}
