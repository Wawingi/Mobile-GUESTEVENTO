package com.example.wawingisebastiao.gestevento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wawingi Sebastiao on 16/01/2020.
 */

public class ListarConvidadosAssento extends AppCompatActivity {
    TextView txtAssento;
    ListView lv;
    String assento;
    CRUD_CONVIDADO bd;
    List<ConvidadoClass> listaConvidados;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_convidados_assento);
        txtAssento = (TextView)findViewById(R.id.txtAssento);
        lv = (ListView)findViewById(R.id.lstview);
        listaConvidados = new ArrayList<>();
        bd = new CRUD_CONVIDADO(this);

        //Recebe dados da outra tela em função do clique
        Intent recebeDados = getIntent();

        assento = recebeDados.getStringExtra("assentoNome");

        txtAssento.setText("ASSENTO: "+assento);

        //Pegar os convidados de um assento na base de dasos
        try{
            listaConvidados = bd.listarConvidadosAssento(assento);
            AdaptadorConvidado adaptador = new AdaptadorConvidado(getApplicationContext(),listaConvidados);
            lv.setAdapter(adaptador);
        }catch(Exception e){

        }

        //APP BAR PERSONALIZADO
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Backbutton
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    //Método do Back buttom
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
