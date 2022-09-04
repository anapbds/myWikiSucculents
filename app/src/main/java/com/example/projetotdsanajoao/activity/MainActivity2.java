package com.example.projetotdsanajoao.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import com.example.projetotdsanajoao.R;
import com.example.projetotdsanajoao.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.projetotdsanajoao.adapter.Adapter;
import com.example.projetotdsanajoao.model.Suculenta;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private List<Suculenta> listaSuculentas = new ArrayList<>();
    private RecyclerView recyclerView;
    private CheckBox cbColecao;

    private FirebaseAuth mAuth;
    private User u;
    private Suculenta su;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerView);
        cbColecao = findViewById(R.id.checkBox);
        mAuth = FirebaseAuth.getInstance();

        //codigo menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch ( menuItem.getItemId()) {
                    case R.id.home:
                        return true;

                    case R.id.colecao:
                        startActivity(new Intent(getApplicationContext()
                                ,Colecao.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext()
                                ,tela_inicial.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
        //fim codigo menu

        this.criarSuculentas();

        Adapter adapter = new Adapter(listaSuculentas, this);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayout.VERTICAL));

        recyclerView.setAdapter( adapter );

    }

    public void criarSuculentas(){
        // configuradoras na classe filme
        Suculenta suculenta = new Suculenta("Aeonium", false, R.drawable.a);
        listaSuculentas.add(suculenta);
        suculenta = new Suculenta("Adromishus", false, R.drawable.b);
        listaSuculentas.add(suculenta);
        suculenta = new Suculenta("Aloe", false, R.drawable.c);
        listaSuculentas.add(suculenta);
        suculenta = new Suculenta("Anacampseros", false, R.drawable.d);
        listaSuculentas.add(suculenta);
        suculenta = new Suculenta("Aptenia", false, R.drawable.e);
        listaSuculentas.add(suculenta);
        suculenta = new Suculenta("Cacto", false, R.drawable.f);
        listaSuculentas.add(suculenta);
        suculenta = new Suculenta("Ceropegia", false, R.drawable.g);
        listaSuculentas.add(suculenta);
        suculenta = new Suculenta("Corpuscularia", false, R.drawable.h);
        listaSuculentas.add(suculenta);
        suculenta = new Suculenta("Cotiledon", false, R.drawable.i);
        listaSuculentas.add(suculenta);
        suculenta = new Suculenta("Crassula", false, R.drawable.j);
        listaSuculentas.add(suculenta);


    }

}