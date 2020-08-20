package com.example.wawingisebastiao.gestevento;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wawingi Sebastiao on 16/01/2020.
 */

public class Contabilidade extends AppCompatActivity {
    TextView txttotalconvidados,txttotalacompanhantes,txttotalpessoas,txttotalassentos;
    CRUD_CONVIDADO bd;
    List<ConvidadoClass> listaConvidados;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contabilidade);
        txttotalconvidados = (TextView)findViewById(R.id.txttotalconvidados);
        txttotalassentos = (TextView)findViewById(R.id.txttotalassentos);
        txttotalacompanhantes = (TextView)findViewById(R.id.txttotalacompanhantes);
        txttotalpessoas = (TextView)findViewById(R.id.txttotalpessoas);

        listaConvidados = new ArrayList<>();
        bd = new CRUD_CONVIDADO(this);

        try{
            listaConvidados = bd.pegaConvidados();
            txttotalconvidados.setText(String.valueOf(listaConvidados.size()));
            txttotalacompanhantes.setText(String.valueOf(ConvidadoClass.totalAcompanhantes(listaConvidados)));
            txttotalpessoas.setText(String.valueOf(listaConvidados.size()+ConvidadoClass.totalAcompanhantes(listaConvidados)));
            txttotalassentos.setText(String.valueOf(bd.pegaAssentos().size()));
        }catch(Exception e){
            System.out.println("BUG: "+e);
        }

        //APP BAR PERSONALIZADO
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Backbuton
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    //Contar acompanhantes
    /*public int totalAcompanhantes(){
        List<ConvidadoClass> listaConvidado = new ArrayList<>();
        ConvidadoClass c1 = new ConvidadoClass();
        c1.setNome("Salas");
        c1.setAcompanhante("Lucia");
        listaConvidado.add(c1);
        ConvidadoClass c2 = new ConvidadoClass();
        c2.setNome("Gui");
        c2.setAcompanhante("Catarina | Juliana");
        listaConvidado.add(c2);
        ConvidadoClass c3 = new ConvidadoClass();
        c3.setNome("Sediamuana");
        c3.setAcompanhante("Olga | Maya | Felly");
        listaConvidado.add(c3);
        ConvidadoClass c4 = new ConvidadoClass();
        c4.setNome("Dalton");
        c4.setAcompanhante("Gizela");
        listaConvidado.add(c4);
        int totAcomp = 0;


        for(ConvidadoClass conv : listaConvidado){
            if(!conv.getAcompanhante().equals("Sem Acompanhante")){
                if(conv.getAcompanhante().contains("|")){
                    char ac[] = conv.getAcompanhante().toCharArray();
                    for(int i=0; i< ac.length;i++){
                        if(ac[i]=='|') {
                            totAcomp++;
                        }
                    }
                    totAcomp++;
                }else{
                    totAcomp++;
                }
            }
        }
        return totAcomp;
    }*/

    //Método do Back buttom
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
