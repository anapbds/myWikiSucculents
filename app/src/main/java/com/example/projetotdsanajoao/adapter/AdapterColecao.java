package com.example.projetotdsanajoao.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetotdsanajoao.activity.Colecao;
import com.example.projetotdsanajoao.activity.MainActivity;
import com.example.projetotdsanajoao.activity.MainActivity2;
import com.example.projetotdsanajoao.config.ConfiguracaoFirebase;
import com.example.projetotdsanajoao.model.Suculenta;
import com.example.projetotdsanajoao.R;
import com.example.projetotdsanajoao.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterColecao extends RecyclerView.Adapter<AdapterColecao.MyViewHolder> {

    private List<Suculenta> listaSuculentas;
    private Activity activity;
    private FirebaseAuth mAuth;
    private User u;
    private Suculenta su;
    FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

    //Construtor para gerar a lista de filme
    // Recebendo a lista no Main
    public AdapterColecao(List<Suculenta> lista, Activity activity) {
        this.activity = activity;
        this.listaSuculentas = lista;
    }

    //Criar a listagem inicial e cria os objetos para exibir na lista
    @Override
    public AdapterColecao.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Converter objeto xml em uma view
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_colecao, parent, false);
        return new AdapterColecao.MyViewHolder(itemLista);
    }

    //Vai criar a visualização na lista
    //position controla o posição dos itens
    @Override
    public void onBindViewHolder(AdapterColecao.MyViewHolder holder, int position){
        Suculenta suculenta = listaSuculentas.get( position );
        holder.titulo.setText( suculenta.getTituloSuculenta() );
        holder.imagem.setImageResource( suculenta.getImagemSuculenta() );
        holder.deletaColecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suculenta.setColecao(false);
                deletaSuculenta(suculenta);
                atualizaActivity(activity);
                Toast.makeText(activity.getApplicationContext(),"Deletar: " +
                        suculenta.getTituloSuculenta(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void atualizaActivity(Activity activity) {
        activity.recreate();
    }

    // retornar a quantidade de itens que vai ser exibido;
    @Override
    public int getItemCount() {
        return listaSuculentas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        ImageView imagem;
        ImageView deletaColecao;

        public MyViewHolder(View itemView){
            super(itemView);
            //Objeto tipo view, utilizando myviewholder
            titulo = itemView.findViewById(R.id.textTitulo);
            imagem = (ImageView) itemView.findViewById(R.id.imageSuculent);
            deletaColecao = (ImageView) itemView.findViewById(R.id.deletaColecao);
        }
    }

    private void deletaSuculenta(Suculenta suculenta) {
        //deleta suculenta do banco do usuario
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("Usuarios").child(suculenta.getUserID()).child("Suculentas").child(suculenta.getTituloSuculenta()).removeValue();
    }

}
