package com.example.projetotdsanajoao.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.projetotdsanajoao.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Suculenta {
    private String tituloSuculenta;
    private boolean colecao;
    private int imagemSuculenta;
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public Suculenta(){

    }

    public Suculenta(String tituloSuculenta, boolean colecao, int imagemSuculenta) {
        this.tituloSuculenta = tituloSuculenta;
        this.colecao = colecao;
        this.imagemSuculenta = imagemSuculenta;
    }

    public String getTituloSuculenta() {
        return tituloSuculenta;
    }

    public void setTituloSuculenta(String tituloSuculenta) {
        this.tituloSuculenta = tituloSuculenta;
    }

    public boolean isColecao() {
        return colecao;
    }

    public void setColecao(boolean colecao) {
        this.colecao = colecao;
    }

    public int getImagemSuculenta() {
        return imagemSuculenta;
    }

    public void setImagemSuculenta(int imagemSuculenta) {
        this.imagemSuculenta = imagemSuculenta;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void salvarDados() {
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("Usuarios").child(this.tituloSuculenta).setValue(this);
    }
}