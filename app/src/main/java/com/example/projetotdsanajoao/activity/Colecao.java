package com.example.projetotdsanajoao.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.projetotdsanajoao.adapter.AdapterColecao;
import com.example.projetotdsanajoao.config.ConfiguracaoFirebase;
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
import java.util.ArrayList;
import java.util.List;

import com.example.projetotdsanajoao.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Colecao extends AppCompatActivity {
    private List<Suculenta> listaSuculentas = new ArrayList<>();
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    AdapterColecao adapter;
    private Activity activity = this;
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colecao);
        recyclerView = findViewById(R.id.recyclerView);

        firebase.child("Usuarios").child(userID).child("Suculentas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn:snapshot.getChildren()){
                    Suculenta suculenta = dn.getValue(Suculenta.class);
                    listaSuculentas.add(suculenta);
                    AdapterColecao adapter = new AdapterColecao(listaSuculentas, activity);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter( adapter );
                    recyclerView.addItemDecoration(new DividerItemDecoration(activity,
                            LinearLayout.VERTICAL));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
///////////////////////////////////////////////codigo menu////////////////////////////
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setSelectedItemId(R.id.colecao);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch ( menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity2.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.colecao:
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
///////////////////////////////////// //fim codigo menu///////////////////////////////
    }
}