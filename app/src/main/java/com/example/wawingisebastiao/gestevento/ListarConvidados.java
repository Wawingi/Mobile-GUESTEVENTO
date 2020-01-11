package com.example.wawingisebastiao.gestevento;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wawingi Sebastiao on 31/12/2019.
 */

public class ListarConvidados extends AppCompatActivity {
    ListView lv;
    LinearLayout lstSemConvidado;
    List<ConvidadoClass> listaConvidados;
    CRUD_CONVIDADO bd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_convidados);
        lv = (ListView)findViewById(R.id.lstview);
        lstSemConvidado = (LinearLayout)findViewById(R.id.lstSemConvidado);
        listaConvidados = new ArrayList<>();
        bd = new CRUD_CONVIDADO(this);

        try{
            listaConvidados = bd.pegaConvidados();
            AdaptadorConvidado adaptador = new AdaptadorConvidado(getApplicationContext(),listaConvidados);
            lv.setAdapter(adaptador);
        } catch (Exception e){
            if(listaConvidados.size()<1){
                //Mostrar a linear layout caso nao tenha nenhum convidado
                lstSemConvidado.setVisibility(View.VISIBLE);
            }
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
