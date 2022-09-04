package com.example.projetotdsanajoao.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetotdsanajoao.config.ConfiguracaoFirebase;
import com.example.projetotdsanajoao.model.Suculenta;
import com.example.projetotdsanajoao.R;
import com.example.projetotdsanajoao.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Suculenta> listaSuculentas;
    private Activity activity;
    private FirebaseAuth mAuth;
    private User u;
    private Suculenta su;

    FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

    //Construtor para gerar a lista de filme
    // Recebendo a lista no Main
    public Adapter(List<Suculenta> lista, Activity activity) {
        this.activity = activity;
        this.listaSuculentas = lista;
    }

    //Criar a listagem inicial e cria os objetos para exibir na lista
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Converter objeto xml em uma view
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista, parent, false);
        return new MyViewHolder(itemLista);
    }

    //Vai criar a visualização na lista
    //position controla o posição dos itens

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Suculenta suculenta = listaSuculentas.get( position );
        holder.titulo.setText( suculenta.getTituloSuculenta() );
        holder.colecao.setChecked( suculenta.isColecao() );
        holder.imagem.setImageResource( suculenta.getImagemSuculenta() );
        holder.colecao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                suculenta.setColecao(true);
                receberDados(suculenta);
                salvarDados(suculenta);
                Toast.makeText(activity.getApplicationContext(),"Foi adicionada a sua colecao: " +
                        suculenta.getTituloSuculenta(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    // retornar a quantidade de itens que vai ser exibido;
    @Override
    public int getItemCount() {
        return listaSuculentas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        CheckBox colecao;
        ImageView imagem;

        public MyViewHolder(View itemView){
            super(itemView);
            //Objeto tipo view, utilizando myviewholder
            titulo = itemView.findViewById(R.id.textTitulo);
            colecao = itemView.findViewById(R.id.checkBox);
            imagem = (ImageView) itemView.findViewById(R.id.imageSuculent);
        }
    }

    private void receberDados(Suculenta suculenta) {
        suculenta.setTituloSuculenta(suculenta.getTituloSuculenta().toString());
        suculenta.setColecao(suculenta.isColecao());
        suculenta.setImagemSuculenta(suculenta.getImagemSuculenta());
    }

    public void salvarDados(Suculenta suculenta) {
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("Usuarios").child(suculenta.getUserID()).child("Suculentas").child(suculenta.getTituloSuculenta()).
                child("tituloSuculenta").setValue(suculenta.getTituloSuculenta());
        firebase.child("Usuarios").child(suculenta.getUserID()).child("Suculentas").child(suculenta.getTituloSuculenta()).
                child("colecao").setValue(suculenta.isColecao());
        firebase.child("Usuarios").child(suculenta.getUserID()).child("Suculentas").child(suculenta.getTituloSuculenta()).
                child("imagemSuculenta").setValue(suculenta.getImagemSuculenta());
    }
}
