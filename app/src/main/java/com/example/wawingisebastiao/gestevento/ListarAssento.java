package com.example.wawingisebastiao.gestevento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Wawingi Sebastiao on 04/01/2020.
 */

public class ListarAssento extends AppCompatActivity {
    ListView lv;
    CRUD_CONVIDADO bd;
    List<ConvidadoClass> listaAssentos;
    LinearLayout lstSemAssento;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_assento);
        lv = (ListView)findViewById(R.id.lstview);
        lstSemAssento = (LinearLayout)findViewById(R.id.lstSemAssento);
        bd = new CRUD_CONVIDADO(this);

        try{
            listaAssentos = bd.pegaAssentos();
            AdaptadorAssento adaptador = new AdaptadorAssento(getApplicationContext(),listaAssentos);
            lv.setAdapter(adaptador);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ConvidadoClass assento = (ConvidadoClass) lv.getItemAtPosition(i);

                    String assentoNome = assento.getAssento();

                    Intent intent = new Intent(ListarAssento.this,ListarConvidadosAssento.class);
                    intent.putExtra("assentoNome",assentoNome);
                    startActivity(intent);
                }
            });
        } catch (Exception e){
                //Mostrar a linear layout caso nao tenha nenhum convidado
                lstSemAssento.setVisibility(View.VISIBLE);
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
