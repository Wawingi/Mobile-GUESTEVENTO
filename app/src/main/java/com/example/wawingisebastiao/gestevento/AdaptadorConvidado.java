package com.example.wawingisebastiao.gestevento;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Wawingi Sebastiao on 29/12/2019.
 */

public class AdaptadorConvidado extends BaseAdapter {
    Context context;
    List<ConvidadoClass> listaconvidados;


    public AdaptadorConvidado(Context context, List<ConvidadoClass> listaconvidados) {
        this.context = context;
        this.listaconvidados = listaconvidados;
    }

    @Override
    public int getCount() {
        return listaconvidados.size();
    }

    @Override
    public Object getItem(int posicao) {
        return listaconvidados.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicao, View convertview, ViewGroup parent) {
        View vista = convertview;
        LayoutInflater inflate =LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.adapterconvidados,null);

        TextView txtnome = (TextView)vista.findViewById(R.id.txtnome);
        TextView txtacompanhante = (TextView)vista.findViewById(R.id.txtacompanhante);
        TextView txtassento = (TextView)vista.findViewById(R.id.txtassento);
        TextView txtestado = (TextView)vista.findViewById(R.id.txtestado);
        TextView txtchegada = (TextView)vista.findViewById(R.id.txtchegada);
        LinearLayout linearestado = (LinearLayout)vista.findViewById(R.id.linearestado);

        txtnome.setText(listaconvidados.get(posicao).getNome().toString());
        if(listaconvidados.get(posicao).getAcompanhante().equals("null")) {
            txtacompanhante.setText("Sem Acompanhante");
        }else{
            txtacompanhante.setText(listaconvidados.get(posicao).getAcompanhante().toString());
        }
        txtassento.setText(listaconvidados.get(posicao).getAssento().toString());
        txtestado.setText(String.valueOf(listaconvidados.get(posicao).getEstado()));
        if(listaconvidados.get(posicao).getEstado().toString().equals("Presente")) {
            linearestado.setBackgroundColor(Color.parseColor("#82f2ab"));
        }
        //Pega somente hora
        String [] texto = listaconvidados.get(posicao).getChegada().toString().split(" ");
        txtchegada.setText(texto[1]);
        return vista;
    }
}
