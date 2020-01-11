package com.example.wawingisebastiao.gestevento;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wawingi Sebastiao on 29/12/2019.
 */

public class AdaptadorAssento extends BaseAdapter {
    Context context;
    List<ConvidadoClass> listaassentos;


    public AdaptadorAssento(Context context, List<ConvidadoClass> listaassentos) {
        this.context = context;
        this.listaassentos = listaassentos;
    }

    @Override
    public int getCount() {
        return listaassentos.size();
    }

    @Override
    public Object getItem(int posicao) {
        return listaassentos.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicao, View convertview, ViewGroup parent) {
        View vista = convertview;
        LayoutInflater inflate =LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.adapterassento,null);

        TextView txtassento = (TextView)vista.findViewById(R.id.txtassento);

        txtassento.setText(listaassentos.get(posicao).getAssento().toString());

        return vista;
    }
}
