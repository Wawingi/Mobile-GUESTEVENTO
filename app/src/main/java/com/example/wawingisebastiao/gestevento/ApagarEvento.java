package com.example.wawingisebastiao.gestevento;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Wawingi Sebastiao on 01/01/2020.
 */

public class ApagarEvento extends AppCompatActivity {
    CRUD_CONVIDADO bd;
    EditText editIdEvento;
    Button btnApagarEvento;
    int id_evento = 0;
    View viewLayout;
    TextView alerta;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apagarevento);
        editIdEvento = (EditText)findViewById(R.id.editIdEvento);
        btnApagarEvento = (Button)findViewById(R.id.btnApagarEvento);
        bd = new CRUD_CONVIDADO(this);

        //Alerta de notificação
        LayoutInflater layoutInflater = getLayoutInflater();
        viewLayout = layoutInflater.inflate(R.layout.alerta,(ViewGroup) findViewById(R.id.custom_alerta));

        btnApagarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(editIdEvento.getText().length()==0){
                        editIdEvento.setError("Numero do evento obrigatório.");
                        editIdEvento.requestFocus();
                    }else{
                        Confirma();

                    }
                }catch(Exception e){

                }
            }
        });

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

    public void Confirma(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("ALERTA");
        builder.setMessage("Tem a certeza que deseja apagar este evento?");
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(bd.eliminar(Integer.parseInt(editIdEvento.getText().toString()))){
                    editIdEvento.setText("");
                    Notificacao.Alerta(ApagarEvento.this,"Evento eliminado com sucesso.","#33c268");
                }else{
                    editIdEvento.setText("");
                    Notificacao.Alerta(ApagarEvento.this,"Erro ao eliminar evento.","#FFFC3939");
                }
            }
        })
                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }
}
