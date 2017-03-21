package com.inec.utils;

import android.content.SharedPreferences;

/**
 * Created by root on 09/10/16.
 */

public class PreferenciasSession {

    public static final String NAME_PREFERENCE="prefSesion";
    public static final String URL_PROFILE_DEFECTO="http://assets.designspark.com/v78/bundles/designsparkdesignandmake/img/default-avatar.jpg";
    public static final String FLICKR_USER_ID="148036258@N06";
    public static final String FLICKR_USER_NAME = "reinaldorico";
    public static final String FLICKR_API_KEY="ccce1fb35a9f48f2b5f041ab7cc06b3b";
    public static final String FLICKR_API_SECRET="b5c347e00a767502";
    public static final String FLICKR_OAUTH_TOKEN="72157673833979901-2f57df0d9eb9cdf7";
    public static final String FLICKR_OAUTH_SECRET="ac3b5023e5420e4d";

    public static final String ENDPOINT="webindiant.appspot.com";
    public static final String PAISNACIMIENTODEFAULT="PERU";
    public static final String REGIONNACIMIENTODEFAULT="LAMBAYEQUE";
    public static final String LOCALIDADNACIMIENTODEFAULT="CHICLAYO";
    public static final String REGISTROTIPOOAUTH="OAUTH";
    public static final String REDSOCIALFLICKR="FLICKR:INDIANT";
    public static final String GENREDTOKENFIRE="GENREDTOKENFIRE";
    public static final String TOKENFIREBASE="TOKENFIREBASE";
    public static final String DEFAULT_ROOT_URL = "https://inec-145618.appspot.com/_ah/api/";
//    public static final String DEFAULT_ROOT_URL = "http://10.0.2.2:8888/_ah/api/";//local


    public static void almacenarValor(SharedPreferences prefSesion, String name, String value){
        SharedPreferences.Editor editor =prefSesion.edit();
        editor.putString(name,value);
        editor.commit();
    }

    public static void almacenarValorBoolean(SharedPreferences prefSesion, String name, boolean value){
        SharedPreferences.Editor editor =prefSesion.edit();
        editor.putBoolean(name,value);
        editor.commit();
    }

    public static void remove(SharedPreferences prefSesion) {
        SharedPreferences.Editor editor = prefSesion.edit();
        editor.clear();
        editor.commit();
    }
}
