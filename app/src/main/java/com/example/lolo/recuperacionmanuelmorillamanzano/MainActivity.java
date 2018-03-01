package com.example.lolo.recuperacionmanuelmorillamanzano;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //VARIABLES NECESARIAS
    private static final String URL="http://192.168.31.12/ciudades/city.list.json";
    ArrayList<Localidad> localidades;
    Localidad l;

    EditText etLocalidad;
    EditText latitudTexto;
    EditText longitudTexto;
    EditText codigoTexto;
    Button btnBuscar;
    Button btnMostrar;
    Button btnPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLocalidad = (EditText) findViewById(R.id.etLocalidad);
        latitudTexto = (EditText) findViewById(R.id.latitudTexto);
        longitudTexto = (EditText) findViewById(R.id.longitudTexto);
        codigoTexto = (EditText) findViewById(R.id.codigoTexto);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnPreferences=(Button)findViewById(R.id.btnPreferences);

        localidades = new ArrayList<Localidad>();


        /////////EMPIEZO CON EL JSON///////////

        RequestQueue request = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //RECUPERO TODO EL ARCHVIO JSON
                JSONArray jsonArrayPrincipal= null;
                try {
                    jsonArrayPrincipal = new JSONArray(response.toString(0));
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println(jsonArrayPrincipal.toString());

                    for(int i=0;i<jsonArrayPrincipal.length();i++){

                        JSONObject unidad= jsonArrayPrincipal.getJSONObject(i);
                        Localidad p=new Localidad();

                        //nombre y codigo
                        System.out.println(unidad.get("name")); //nombre
                        p.setNombre(unidad.get("name").toString());

                        System.out.println(unidad.get("country")); //codigo
                        p.setNombre(unidad.get("country").toString());

                        //Coordeandas
                        JSONObject coordenadas=unidad.getJSONObject("coord");
                        System.out.println(coordenadas.get("lat"));//latitud
                        p.setLatitud(coordenadas.get("lat").toString());

                        System.out.println(coordenadas.get("lon"));//longitud
                        p.setLatitud(coordenadas.get("lon").toString());

                        //Meto en arrayList
                        localidades.add(p);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });

        request.add(jsonArrayRequest);


        //buscar
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreEscrito=etLocalidad.getText().toString();

                for(int i=0;i<localidades.size();i++){
                    if(nombreEscrito.equalsIgnoreCase(localidades.get(i).getNombre().toString())){
                        System.out.println(localidades.get(i).getNombre().toString());
                        Toast.makeText(getApplicationContext(), "LOCALIDAD ENCONTRADA", Toast.LENGTH_SHORT).show();

                        //mando esta localidad
                        l=localidades.get(i);

                        latitudTexto.setText(localidades.get(i).getLatitud().toString());
                        longitudTexto.setText(localidades.get(i).getLongitud().toString());
                        codigoTexto.setText(localidades.get(i).getCodigo().toString());
                    }
                    else
                        Toast.makeText(getApplicationContext(), "LOCALIDAD NO EXISTE", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //SHARED PREFERENCES
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        etLocalidad.setText(prefe.getString("Localidad",""));

        btnPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencias.edit();
                    editor.putString("Localidad",etLocalidad.getText().toString());
                    editor.commit();

            }


        });


    }

    public void mostrar(View v){
        Intent e = new Intent(getApplicationContext(), Map.class);
        e.putExtra("objeto", (Serializable) l);
        startActivity(e);

    }



}
