package com.inec.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by root on 21/10/16.
 */

public class UtilGeolocalizacion {
    public static final String TAG = UtilGeolocalizacion.class.getSimpleName();
    public static final double OVERJYL=1205200923021988.12052009;

    public static GeoPunto updateBestLocation(HashSet<GeoPunto> hashSet,Double latitudColoniaDestino, Double longitudColoniaDestino,Context context){
        double distanciaAuxiliar= OVERJYL;
        if(latitudColoniaDestino==0.0 && longitudColoniaDestino==0.0){
            return null;
        }
        HashSet<GeoPunto> hashInterno=new HashSet();
        hashInterno.addAll(hashSet);
        Iterator<GeoPunto> iterador=hashInterno.iterator();
        GeoPunto auxGeo=null;
        while(iterador.hasNext()){
            GeoPunto geo=iterador.next();
            Double distancia=retornarDistancia(geo.getLatitude(),geo.getLongitude(),latitudColoniaDestino,longitudColoniaDestino);
            if(distanciaAuxiliar>distancia){
                distanciaAuxiliar=distancia;
                auxGeo=geo;
                auxGeo.setDistancia(distanciaAuxiliar);
            }
        }
        if(distanciaAuxiliar!=OVERJYL) {
            if (auxGeo != null) {
                return convertGeoPointToGeoNames(auxGeo,context);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }


    public static GeoPunto convertGeoPointToGeoNames(GeoPunto geo,Context context){
        try{
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            Double currentLatitude=geo.getLatitude();
            Double currentLongitude=geo.getLongitude();
            if(currentLatitude!=null && currentLongitude!=null){
                List<Address> addresses = geocoder.getFromLocation(currentLatitude,currentLongitude,10);
                if (addresses == null || addresses.size()  == 0) {
                    return null;
                }else{
                    String currentPais=null;
                    String currentRegion=null;
                    String currentLocalidad=null;
                    String currentProvincia=null;
                    for(int i=0;i<addresses.size();i++){
                        Address address = addresses.get(i);
                        if(address.getCountryName()!=null && !address.getCountryName().isEmpty()){
                            currentPais= FieldVerifier.cleanStringUpper(address.getCountryName());
                            if(address.getAdminArea()!=null && !address.getAdminArea().isEmpty()){
                                currentRegion= FieldVerifier.cleanStringUpper(address.getAdminArea());
                                if(address.getSubAdminArea()!=null && !address.getSubAdminArea().isEmpty()){
                                    currentProvincia= FieldVerifier.cleanStringUpper(address.getSubAdminArea());
                                    if(address.getLocality()!=null && !address.getLocality().isEmpty()){
                                        currentLocalidad= FieldVerifier.cleanStringUpper(address.getLocality());
                                        break;
                                    }else{
                                        currentLocalidad=null;
                                    }
                                }else{
                                    currentProvincia=null;
                                }
                            }else{
                                currentRegion=null;
                            }
                        }else{
                            currentPais=null;
                        }
                    }
                    geo=new GeoPunto();
                    geo.setActiveGeolocalizacion(true);
                    geo.setLatitude(currentLatitude);
                    geo.setLongitude(currentLongitude);
                    geo.setNombrePais(currentPais);
                    geo.setNombreProvincia(currentProvincia);
                    geo.setNombreRegion(currentRegion);
                    geo.setNombreLocalidad(currentLocalidad);
                    return geo;
                }
            }else{
                return null;
            }
        }catch(Exception ex){
            return null;
        }
    }

    public static Double retornarDistancia(Double latitudInicial, Double longitudInicial, Double latitudFinal, Double longitudFinal){
        try {
            BigDecimal radioTierra=new BigDecimal(6371);
            BigDecimal exacSinLatitudInicial= BigDecimal.valueOf(Math.sin(Math.toRadians(latitudInicial)));
            BigDecimal exacSinLatitudFinal= BigDecimal.valueOf(Math.sin(Math.toRadians(latitudFinal)));
            BigDecimal exacCosLatitudInicial= BigDecimal.valueOf(Math.cos(Math.toRadians(latitudInicial)));
            BigDecimal exacCosLatitudFinal= BigDecimal.valueOf(Math.cos(Math.toRadians(latitudFinal)));
            BigDecimal exacRadLongitudInicial= BigDecimal.valueOf(Math.toRadians(longitudInicial));
            BigDecimal exacRadLongitudFinal= BigDecimal.valueOf(Math.toRadians(longitudFinal));
            BigDecimal exacRestoFinalLongitud=exacRadLongitudInicial.subtract(exacRadLongitudFinal);
            BigDecimal exacCosRestoFinalLongitud= BigDecimal.valueOf(Math.cos(exacRestoFinalLongitud.doubleValue()));
            BigDecimal exacSinLatIniFin=exacSinLatitudInicial.multiply(exacSinLatitudFinal);
            BigDecimal exacCosLatIniFin=exacCosLatitudInicial.multiply(exacCosLatitudFinal);
            BigDecimal radDistancia=exacCosLatIniFin.multiply(exacCosRestoFinalLongitud).add(exacSinLatIniFin);
            if(radDistancia.compareTo(BigDecimal.ONE)==1){
                radDistancia= BigDecimal.ONE;
            }else if(radDistancia.compareTo(BigDecimal.valueOf(-1))==-1){
                radDistancia= BigDecimal.valueOf(-1);
            }
            BigDecimal acosRadDistancia= BigDecimal.valueOf(Math.acos(Double.parseDouble(radDistancia.toString())));
            BigDecimal calculoDistancia=acosRadDistancia.multiply(radioTierra) ;
            return Double.parseDouble(calculoDistancia.setScale(6, BigDecimal.ROUND_HALF_UP).toString());
        }catch(Exception ex){
            return -1.0;
        }
    }

}
