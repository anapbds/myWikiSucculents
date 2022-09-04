package com.example.projetotdsanajoao.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetotdsanajoao.R;

public class tela_inicial extends AppCompatActivity {

    private Button buttonTelaLogin;
    private Button buttonTelaCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        buttonTelaLogin = findViewById(R.id.buttonTelaLogin);
        buttonTelaCadastro = findViewById(R.id.buttonTelaCadastro);

        buttonTelaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaLogar();
            }
        });
        
        buttonTelaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaCadastrar();
            }
        });
    }

    private void telaCadastrar() {
        startActivity(new Intent(this,tela_cadastro.class));
    }

    private void telaLogar() {
        startActivity(new Intent(this,MainActivity.class));
    }
}