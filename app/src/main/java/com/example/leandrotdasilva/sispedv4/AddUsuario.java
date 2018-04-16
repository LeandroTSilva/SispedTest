package com.example.leandrotdasilva.sispedv4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUsuario extends AppCompatActivity {

    ProgressDialog dialog;

    EditText edit_Nome ;
    EditText edit_Tel ;
    EditText edit_Login ;
    EditText edit_Senha ;
    Spinner perfilUsuario ;
    FloatingActionButton btn_Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_usuario);


        edit_Nome = findViewById(R.id.edit_nome);
        edit_Tel = findViewById(R.id.edit_tel);
        edit_Login = findViewById(R.id.edit_login);
        edit_Senha = findViewById(R.id.edit_senha);
        btn_Add = findViewById(R.id.btn_Add);
        perfilUsuario = findViewById(R.id.spn_Perfil);



        ArrayAdapter adapter =
                ArrayAdapter.createFromResource(this, R.array.perfil_usuario,
                        R.layout.support_simple_spinner_dropdown_item);

        perfilUsuario.setAdapter(adapter);

        adicionar();
    }

    public  void adicionar(){
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ProgressDialog(AddUsuario.this);
                dialog.setTitle("Aguarde");
                dialog.setMessage(" Salvando novo usuario");
                dialog.setCancelable(false);
                dialog.show();

                Usuario usuario = new Usuario();
                usuario.setUsuNome(edit_Nome.getText().toString());
                usuario.setUsuTel(edit_Tel.getText().toString());
                usuario.setUsuLogin(edit_Login.getText().toString());
                usuario.setUsuSenha(edit_Senha.getText().toString());
                usuario.setUsuPerfil(perfilUsuario.getSelectedItem().toString());

                IapiRest iapiSisped = ApiRest.getRetrofit().create(IapiRest.class);

                Call<Void> call = iapiSisped.addUsuario(usuario);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if((edit_Nome.getText().equals("")) || (edit_Tel.getText().equals("")) || (edit_Login.getText().equals(""))|| (edit_Senha.getText().equals("")) ){
                            Toast.makeText(getBaseContext()," Favor preencha todos os campos",Toast.LENGTH_SHORT).show();

                        }else {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            Toast.makeText(getBaseContext(), " Usuario salvo com sucesso", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AddUsuario.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), " Falha na Requisição", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
