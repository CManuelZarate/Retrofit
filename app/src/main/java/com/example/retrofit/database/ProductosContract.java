package com.example.retrofit.database;

import android.provider.BaseColumns;

public class ProductosContract {

    public static abstract  class ProductoEntry implements BaseColumns{
        //PLANTILLA DE TABLA PRODUCTOS

        public static final String TABLE_NAME = "PRODUCTO";

        public static final String ID = "ID";
        public static final String NOMBRE = "NOMBRE";
        public static final String DESCRIPCION = "DESCRIPCION";
        public static final String PRECIOUNIDAD = "PRECIOUNIDAD";
        public static final String UNIDAD = "UNIDAD";
    }



}
