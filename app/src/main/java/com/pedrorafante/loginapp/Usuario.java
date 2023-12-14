package com.pedrorafante.loginapp;

public class Usuario {

    private int id;
    private String user;
    private String senha;

    public Usuario() {

    }

    public Usuario(int id, String user, String senha) {
        this.id = id;
        this.user = user;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
