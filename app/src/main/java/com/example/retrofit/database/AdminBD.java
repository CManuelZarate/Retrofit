package com.example.retrofit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.retrofit.database.ProductosContract.ProductoEntry;

import com.example.retrofit.Model.Producto;

public class AdminBD extends SQLiteOpenHelper {
    //heredamos de una clase q viene de android y permite usar sqlite

    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NOMBRE= "productos.db";

    //TABLAS
    //public static final String TABLA_PRODUCTOS = "PRODUCTOS";


    //Constructor de SQLite
    public AdminBD(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    //OnCreate de SQLite
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //este metodo se ejecuta cuando se esta creando un obj de esta clase

        //indicamos el query, creamos una tabla producto
        /*sqLiteDatabase.execSQL("CREATE TABLE "+TABLA_PRODUCTOS+" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE TEXT NOT NULL," +
                " DESCRIPCION TEXT NOT NULL," +
                " PRECIOUNIDAD INTEGER NOT NULL," +
                " UNIDAD TEXT)");*///ejecutamos un query sql

        sqLiteDatabase.execSQL("CREATE TABLE " +ProductoEntry.TABLE_NAME + " ("
                +ProductoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ProductoEntry.ID + " TEXT ,"
                +ProductoEntry.NOMBRE + " TEXT NOT NULL,"
                +ProductoEntry.DESCRIPCION + " TEXT NOT NULL,"
                +ProductoEntry.PRECIOUNIDAD + " TEXT NOT NULL,"
                +ProductoEntry.UNIDAD + " TEXT NOT NULL,"
                +"UNIQUE (" +ProductoEntry.ID + "))");

        sqLiteDatabase.execSQL("INSERT INTO "+ ProductoEntry.TABLE_NAME + "(ID,NOMBRE,DESCRIPCION,PRECIOUNIDAD,UNIDAD)"+
                " VALUES('100'," +
                "'Placa'," +
                "'cara'," +
                "'100'," +
                "'unidad')");

        //guardarProducto();

    }

    public long guardarProducto(Producto producto){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                ProductoEntry.TABLE_NAME,
                null,
                producto.toContentValue());
    }



    //OnUpgrade de SQLite
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //versionanremos nuestra BD y creamos tablas,insercion de datos
}
