package com.example.leandrotdasilva.sispedv4;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leandrot.dasilva on 09/04/2018.
 */

public class Usuario {
    @SerializedName("usuId")
    private int usuId;

    @SerializedName("usuNome")
    private String usuNome;

    @SerializedName("usuTel")
    private String usuTel;

    @SerializedName("usuLogin")
    private String usuLogin;

    @SerializedName("usuSenha")
    private String usuSenha;

    @SerializedName("usuPerfil")
    private String usuPerfil;

    //metodo construtor padrao sem argumentos
    public Usuario(){}

    //metodos acessores e modificadores


    public int getUsuId() {
        return usuId;
    }

    public void setUsuId(int usuId) {
        this.usuId = usuId;
    }

    public String getUsuNome() {
        return usuNome;
    }

    public void setUsuNome(String usuNome) {
        this.usuNome = usuNome;
    }

    public String getUsuTel() {
        return usuTel;
    }

    public void setUsuTel(String usuTel) {
        this.usuTel = usuTel;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuSenha() {
        return usuSenha;
    }

    public void setUsuSenha(String usuSenha) {
        this.usuSenha = usuSenha;
    }

    public String getUsuPerfil() {
        return usuPerfil;
    }

    public void setUsuPerfil(String usuPerfil) {
        this.usuPerfil = usuPerfil;
    }
}
