package com.example.projetotdsanajoao.model;

import com.example.projetotdsanajoao.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nome;
    private String email;
    private String senha;
    private String id;
    private List<Suculenta> listaSuculentas = new ArrayList<>();

    public User(){

    }

    public List<Suculenta> getListaSuculentas() {
        return listaSuculentas;
    }

    public void setListaSuculentas(List<Suculenta> listaSuculentas) {
        this.listaSuculentas = listaSuculentas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void salvarDados() {
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("Usuarios").child(this.id).setValue(this);
    }
}
