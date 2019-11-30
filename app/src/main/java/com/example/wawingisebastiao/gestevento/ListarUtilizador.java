package com.example.wawingisebastiao.gestevento;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wawingi Sebastiao on 09/11/2019.
 */

public class ListarUtilizador extends AppCompatActivity {
    ListView lv;
    List<Utilizador> listaUtilizadores;
    CRUD_UTILIZADOR bd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_utilizador);

        lv = (ListView)findViewById(R.id.lstview);
        listaUtilizadores = new ArrayList<>();
        bd = new CRUD_UTILIZADOR(this);

        //APP BAR PERSONALIZADO
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            listaUtilizadores = bd.pegaDados();
        } catch (Exception e){

        }

        Adaptador adaptador = new Adaptador(getApplicationContext(),listaUtilizadores);
        lv.setAdapter(adaptador);

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
