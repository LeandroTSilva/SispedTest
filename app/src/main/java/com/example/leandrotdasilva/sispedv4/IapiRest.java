package com.example.leandrotdasilva.sispedv4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by leandrot.dasilva on 09/04/2018.
 */

public interface IapiRest {

    @POST("WebApplication/webresources/ws.dbusuarios/inserir")
    Call<Void> addUsuario(@Body Usuario usuario);

    @GET("WebApplication/webresources/ws.dbusuarios")
    Call<List<Usuario>> getUsuarios();

    @GET("WebApplication/webresources/ws.dbusuarios/buscar/{id}")
    Call<Usuario> getUsuariosPorId(@Path("id") String id);


    @PUT("WebApplication/webresources/ws.dbusuarios/editar/{id}")
    Call<Void> AlterarUsuario(@Path("id") String id, @Body Usuario usuario);

    @DELETE("WebApplication/webresources/ws.dbusuarios/remover/{id}")
    Call<Void> excluirUsuarios(@Path("id") String id);





}
