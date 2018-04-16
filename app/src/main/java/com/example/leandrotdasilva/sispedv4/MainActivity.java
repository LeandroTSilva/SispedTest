package com.example.leandrotdasilva.sispedv4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    List<Usuario> listaUsuarios;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUsuario.class);
                startActivity(intent);
            }
        });

        load();
    }

 public void load(){

     lista = findViewById(R.id.lv_Usuarios);

     IapiRest iapiRest = ApiRest.getRetrofit().create(IapiRest.class);

     dialog = new ProgressDialog(MainActivity.this);
     dialog.setTitle("Aguarde");
     dialog.setMessage("Buscando usuarios");
     dialog.setCancelable(false);
     dialog.show();

     retrofit2.Call<List<Usuario>> call = iapiRest.getUsuarios();
     call.enqueue(new Callback<List<Usuario>>() {
         @Override
         public void onResponse(retrofit2.Call<List<Usuario>> call, Response<List<Usuario>> response) {
             if(dialog.isShowing())
                 dialog.dismiss();
             listaUsuarios = response.body();
             if(listaUsuarios != null){
                 UsuAdapter adapter = new UsuAdapter(getBaseContext(),listaUsuarios);

                 lista.setAdapter(adapter);
                 lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                     @Override
                     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                         Intent intent =  new Intent(MainActivity.this,EditUsuario.class);
                         intent.putExtra("id",listaUsuarios.get(i).getUsuId());
                         startActivity(intent);
                     }
                 });


                 //Toast.makeText(getBaseContext(),"Sucesso na requisição",Toast.LENGTH_SHORT).show();

             }



         }

         @Override
         public void onFailure(retrofit2.Call<List<Usuario>> call, Throwable t) {
             if (dialog.isShowing())
                 dialog.dismiss();

             Toast.makeText(getBaseContext(),"Falha na requisição",Toast.LENGTH_SHORT).show();

         }
     });







 }


}
