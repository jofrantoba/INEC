package com.inec.utils;

import android.location.Location;

import java.io.Serializable;

/**
 * Created by root on 07/09/16.
 */
public class GeoPunto implements Serializable {
    private Double latitude;
    private Double longitude;
    private Location location;
    private String nombrePais;
    private String nombreRegion;
    private String nombreLocalidad;
    private String nombreProvincia;
    private boolean isActiveGeolocalizacion;
    private double distancia;

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }
    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public boolean isActiveGeolocalizacion() {
        return isActiveGeolocalizacion;
    }

    public void setActiveGeolocalizacion(boolean activeGeolocalizacion) {
        isActiveGeolocalizacion = activeGeolocalizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoPunto geoPunto = (GeoPunto) o;

        if (latitude != null ? !latitude.equals(geoPunto.latitude) : geoPunto.latitude != null)
            return false;
        return longitude != null ? longitude.equals(geoPunto.longitude) : geoPunto.longitude == null;

    }

    @Override
    public int hashCode() {
        int result = latitude != null ? latitude.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
