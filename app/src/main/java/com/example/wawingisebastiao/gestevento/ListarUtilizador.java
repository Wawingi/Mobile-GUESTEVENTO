package com.example.wawingisebastiao.gestevento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        AdaptadorUtilizador adaptadorUtilizador = new AdaptadorUtilizador(getApplicationContext(),listaUtilizadores);
        lv.setAdapter(adaptadorUtilizador);
        registerForContextMenu(lv);

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Utilizador utilizador = (Utilizador) lv.getItemAtPosition(i);

                String assentoNome = utilizador.getNome();
                int id = utilizador.getId();

                System.out.println("NAME: "+assentoNome+"ID: "+id);
            }
        });*/

        //Backbutton
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Escolha a opção");
        menu.add(0,v.getId(),0,"Activar");
        menu.add(0,v.getId(),0,"Desactivar");
    }

    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getTitle()=="Activar"){
            if(bd.AlterarEstado("Activo",info.position+1)){
                Notificacao.Alerta(ListarUtilizador.this,"Activado com sucesso.","#33c268");
                Intent intent = new Intent(this, ListarUtilizador.class);
                startActivity(intent);
                finish();
            }
        }
        else if(item.getTitle()=="Desactivar"){
            if(bd.AlterarEstado("Desactivado",info.position+1)){
                Notificacao.Alerta(ListarUtilizador.this,"Desactivado com sucesso.","#33c268");
                Intent intent = new Intent(this, ListarUtilizador.class);
                startActivity(intent);
                finish();
            }
        }
        return true;
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
