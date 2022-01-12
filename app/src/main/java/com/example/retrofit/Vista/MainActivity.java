package com.example.retrofit.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.Interface.JsonPlaceHolderApi;
import com.example.retrofit.Model.Producto;
import com.example.retrofit.Model.Productos;
import com.example.retrofit.Network;
import com.example.retrofit.R;
import com.example.retrofit.database.AdminBD;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;
    AdminBD adminBD = new AdminBD(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = findViewById(R.id.jsonText);
        getProductos();
        if(Network.isOnline(this)){
            Toast.makeText(this,"Si hay red",Toast.LENGTH_LONG).show();
            //CONSUMIR LA DATA DE LA API Y GUARDO EN MI BD

            //AdminBD adminBD = new AdminBD(MainActivity.this);
            ejecucionBD();


        }else{
            Toast.makeText(this,"No hay red",Toast.LENGTH_LONG).show();
            //CONSUMIR LA DATA LOCAL
            getLocal();
        }



        //

    }

    private void getLocal(){

        SQLiteDatabase database = adminBD.getWritableDatabase();
        //database.query(null,null,null,null,null,null,null);

        /*Cursor datos = database.rawQuery("SELECT NOMBRE FROM PRODUCTOS",null);
        while (datos.moveToNext()){
            String nombre = datos.getString(datos.getInt(0));
        }*/

        String[] columnas = {"NOMBRE"};
        Cursor datos = database.query("PRODUCTO", columnas, null, null,null,null,null);

        while (datos.moveToNext()){
            String name = datos.getString(datos.getInt(0));
            if( name !=null){
                mJsonTxtView.append(name);
            }

        }
        /*
        while (datos.moveToNext()){
            int c=0;
            String content = "";
            content += "nombre : "+datos.getString(c) + "\n\n";
            mJsonTxtView.append(content);
            c++;
        }*/

    }

    private void getProductos(){
        //creamos instancia de retro
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://emark-backend-nodejs.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Productos> call =jsonPlaceHolderApi.getProductos();

        call.enqueue(new Callback<Productos>() {
            @Override
            public void onResponse(Call<Productos> call, Response<Productos> response) {
                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }

                List<Producto> productosList = response.body().getMensaje();
                Log.d("CRE","GAAAA"+productosList);
                for(Producto p: productosList){
                    String content = "";
                    content += "id : "+p.getId() + "\n";
                    content += "nombre : "+p.getNombre() + "\n";
                    content += "descripcion : "+p.getDescripcion() + "\n";
                    content += "precio : "+p.getPrecioUnidad() + "\n";
                    content += "unidad : "+p.getUnidad() + "\n\n";
                    mJsonTxtView.append(content);
                    adminBD.guardarProducto(new Producto(p.getId(),p.getNombre(),p.getDescripcion(),p.getPrecioUnidad(),p.getUnidad()));
                }
            }

            @Override
            public void onFailure(Call<Productos> call, Throwable t) {

            }
        });
    }

    public void ejecucionBD(){
        //Ejecucion de la BD
        //AdminBD adminBD = new AdminBD(MainActivity.this);
        SQLiteDatabase database = adminBD.getWritableDatabase();

        if( database != null ){
            Toast.makeText(this,"se ha creado la BD correctamente",Toast.LENGTH_LONG).show();
            //adminBD.guardarProducto();

        }else{
            Toast.makeText(this,"NO ha creado la BD correctamente",Toast.LENGTH_LONG).show();
        }


        //database.query(null,null,null,null,null,null,null);
        /*Cursor datos = database.rawQuery("SELECT NOMBRE  FROM PRODUCTOS WHERE ID=100",null);
        while (datos.moveToNext()){
            String nombre = datos.getString(datos.getInt(0));
        }*/
    }
/*
    private void getProductos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/ ")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Productos>> call =jsonPlaceHolderApi.getProductos();

        call.enqueue(new Callback<List<Productos>>() {
            @Override
            public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }

                List<Productos> productosList = response.body();
                for(Productos p: productosList){
                    String content = "";
                    content += "userID : "+p.getUserId() + "\n";
                    content += "id : "+p.getId() + "\n";
                    content += "title : "+p.getTitle() + "\n";
                    content += "body : "+p.getBody() + "\n\n";
                    mJsonTxtView.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Productos>> call, Throwable t) {

            }
        });
    }

 */
}