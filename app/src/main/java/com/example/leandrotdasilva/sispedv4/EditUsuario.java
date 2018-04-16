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
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditUsuario extends AppCompatActivity {

    ProgressDialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editusuario);

        final EditText nome = findViewById(R.id.edNome);
        final EditText tel = findViewById(R.id.edTel);
        final EditText login = findViewById(R.id.edLogin);
        final EditText senha = findViewById(R.id.edSenha);
        final TextView mperfil = findViewById(R.id.txtperfil);
        final Spinner perfilUsuario = findViewById(R.id.spnPerfil);




        //configura o Spinner...
        ArrayAdapter adapter =
                ArrayAdapter.createFromResource(this, R.array.perfil_usuario, R.
                        layout.support_simple_spinner_dropdown_item);
        perfilUsuario.setAdapter(adapter);

        // pega os dados ultilizados na MainActivity.

        Intent intent = getIntent();
        final String id = Integer.toString(intent.getIntExtra("id",0));

        final IapiRest iapiRest = ApiRest.getRetrofit().create(IapiRest.class);
        final Call<Usuario> call = iapiRest.getUsuariosPorId(id);

        dialog =  new ProgressDialog(EditUsuario.this);
        dialog.setMessage("Carregando Usuarios");
        dialog.setCancelable(false);
        dialog.show();

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(dialog.isShowing())dialog.dismiss();
                    Usuario user = response.body();
                    nome.setText(user.getUsuNome());
                    tel.setText(user.getUsuTel());
                    login.setText(user.getUsuLogin());
                    senha.setText(user.getUsuSenha());
                    mperfil.setText(user.getUsuPerfil());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                if(dialog.isShowing())dialog.dismiss();
                Toast.makeText(getBaseContext(),"Falha na requisição ",Toast.LENGTH_SHORT).show();

            }
        });


        Button btAlterar = findViewById(R.id.btEditar);
        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Usuario usuario = new Usuario();

                usuario.setUsuId(Integer.parseInt(id));
                usuario.setUsuNome(nome.getText().toString());
                usuario.setUsuTel(tel.getText().toString());
                usuario.setUsuLogin(login.getText().toString());
                usuario.setUsuSenha(senha.getText().toString());
                usuario.setUsuPerfil(perfilUsuario.getSelectedItem().toString());

                final Call<Void> call1 = iapiRest.AlterarUsuario(id,usuario);
                call1.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(dialog.isShowing())dialog.dismiss();
                        Toast.makeText(getBaseContext()," Dados Alterados ",Toast.LENGTH_SHORT).show();

                        Intent voltaTela = new Intent(EditUsuario.this, MainActivity.class);
                        startActivity(voltaTela);


                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getBaseContext(),"Sem conexao ",Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });


        Button btnRemover = findViewById(R.id.btExcluir);
        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dialog = new ProgressDialog(EditUsuario.this);
            dialog.setMessage("Excluindo usuario");
            dialog.setCancelable(false);
            dialog.show();
            Call<Void> call = iapiRest.excluirUsuarios(id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(dialog.isShowing())dialog.dismiss();
                        Toast.makeText(getBaseContext()," Usuario excluido com sucesso",Toast.LENGTH_SHORT).show();

                        Intent voltaTela = new Intent(EditUsuario.this, MainActivity.class);
                        startActivity(voltaTela);

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if(dialog.isShowing())dialog.dismiss();
                        Toast.makeText(getBaseContext(),"Sem conexao ",Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


    }




}














