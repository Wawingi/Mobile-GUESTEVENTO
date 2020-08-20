package com.example.wawingisebastiao.gestevento;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wawingi Sebastiao on 15/03/2020.
 */

public class Estatistica extends AppCompatActivity {
    String descricao[] = {"Ausentes","Presentes"};
    CRUD_CONVIDADO bd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estatistica);

        PieChart grafico = (PieChart)findViewById(R.id.graficoId);
        List<PieEntry> entradaGrafico = new ArrayList<>();
        bd = new CRUD_CONVIDADO(this);

        try{
            int item[] = {bd.contConvidados("Ausente").size()+ConvidadoClass.totalAcompanhantesAusentes(bd.contConvidados("Ausente")), bd.contConvidados("Presente").size()+ConvidadoClass.totalAcompanhantesPresentes(bd.contConvidados("Presente"))};

            for(int i=0;i<item.length;i++){
                entradaGrafico.add(new PieEntry(item[i],descricao[i]));
            }

            PieDataSet dataSet = new PieDataSet(entradaGrafico,"");
            dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            dataSet.setValueTextColor(Color.WHITE);
            dataSet.setValueTextSize(10f);
            dataSet.setSliceSpace(5f);

            PieData pieData = new PieData(dataSet);

            grafico.getDescription().setText("Convidados do Evento");
            grafico.getDescription().setTextSize(10f);
            grafico.setData(pieData);
            grafico.invalidate();
        }catch (Exception e){

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

    //Método do Back buttom
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
