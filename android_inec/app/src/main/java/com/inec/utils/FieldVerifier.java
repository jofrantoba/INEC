package com.inec.utils;

import java.text.Normalizer;

/**
 * Created by root on 11/09/16.
 */
public class FieldVerifier {

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }

    public static boolean isEmpty(CharSequence value){
        return value.length()==0;
    }

    public static String cleanStringUpper(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto.toUpperCase();
    }
}
