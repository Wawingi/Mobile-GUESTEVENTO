package com.example.wawingisebastiao.gestevento;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wawingi Sebastiao on 09/11/2019.
 */

public class Adaptador extends BaseAdapter {
    Context context;
    List<Utilizador> listautilizadores;


    public Adaptador(Context context, List<Utilizador> listautilizadores) {
        this.context = context;
        this.listautilizadores = listautilizadores;
    }

    @Override
    public int getCount() {
        return listautilizadores.size();
    }

    @Override
    public Object getItem(int posicao) {
        return listautilizadores.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicao, View convertview, ViewGroup parent) {
        View vista = convertview;
        LayoutInflater inflate =LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.adapterutilizador,null);

        TextView txtnome = (TextView)vista.findViewById(R.id.txtnome);
        TextView txtemail = (TextView)vista.findViewById(R.id.txtemail);
        TextView txttelefone = (TextView)vista.findViewById(R.id.txttelefone);
        TextView txtperfil = (TextView)vista.findViewById(R.id.txtperfil);

        txtnome.setText(listautilizadores.get(posicao).getNome().toString());
        txtemail.setText(listautilizadores.get(posicao).getEmail().toString());
        txttelefone.setText(String.valueOf(listautilizadores.get(posicao).getTelefone()));
        txtperfil.setText(listautilizadores.get(posicao).getPerfil().toString());

        return vista;
    }
}
