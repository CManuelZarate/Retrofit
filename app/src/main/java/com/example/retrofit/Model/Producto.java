package com.example.retrofit.Model;

import android.content.ContentValues;

import com.example.retrofit.database.ProductosContract;

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private String precioUnidad;
    private String unidad;

    public Producto(String id,String nombre, String descripcion, String precioUnidad, String unidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnidad = precioUnidad;
        this.unidad = unidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioUnidad(String precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecioUnidad() {
        return precioUnidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public ContentValues toContentValue(){
        //agregar todos los valores a la BD
        ContentValues contenedor =new ContentValues();
        contenedor.put(ProductosContract.ProductoEntry.ID, id);
        contenedor.put(ProductosContract.ProductoEntry.NOMBRE, nombre);
        contenedor.put(ProductosContract.ProductoEntry.DESCRIPCION, descripcion);
        contenedor.put(ProductosContract.ProductoEntry.PRECIOUNIDAD, precioUnidad);
        contenedor.put(ProductosContract.ProductoEntry.UNIDAD, unidad);

        return contenedor;
    }
}
