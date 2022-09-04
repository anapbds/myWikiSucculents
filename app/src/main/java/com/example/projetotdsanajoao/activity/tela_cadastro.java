package com.example.projetotdsanajoao.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetotdsanajoao.R;
import com.example.projetotdsanajoao.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class tela_cadastro extends AppCompatActivity {

    private EditText textNome;
    private EditText textEmail;
    private EditText textSenha;
    private Button buttonCadastrar;
    private FirebaseAuth mAuth;
    private User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        textNome = findViewById(R.id.textNome);
        textEmail = findViewById(R.id.textEmail);
        textSenha =  findViewById(R.id.textSenha);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        mAuth = FirebaseAuth.getInstance();

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDados();
                criarLogin();
            }
        });
    }

    private void criarLogin() {
        mAuth.createUserWithEmailAndPassword(u.getEmail(),u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            u.setId(user.getUid());
                            u.salvarDados();
                            startActivity(new Intent(tela_cadastro.this, MainActivity2.class));
                        }
                        else{
                            Toast.makeText(tela_cadastro.this, "Erro ao criar um Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void recuperarDados() {
        if(textNome.getText().toString()==""|| textEmail.getText().toString()=="" || textSenha.getText().toString()==""){
            Toast.makeText(this, "Você deve preencher todos os dados", Toast.LENGTH_LONG);
        }
        else{
            u = new User();
            u.setNome(textNome.getText().toString());
            u.setEmail(textEmail.getText().toString());
            u.setSenha(textSenha.getText().toString());
        }
    }
}