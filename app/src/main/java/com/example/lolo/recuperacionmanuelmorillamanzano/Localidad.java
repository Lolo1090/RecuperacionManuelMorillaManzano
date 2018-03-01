package com.example.lolo.recuperacionmanuelmorillamanzano;

import java.io.Serializable;

/**
 * Created by Lolo on 01/03/2018.
 */

public class Localidad implements Serializable{

    String nombre;
    String latitud;
    String longitud;
    String codigo;

    public Localidad(String nombre, String latitud, String longitud, String codigo) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.codigo = codigo;
    }

    public Localidad(){}


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
