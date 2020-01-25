package com.example.wawingisebastiao.gestevento;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Wawingi Sebastiao on 12/01/2020.
 */

public class MarcarPresenca extends AppCompatActivity {
    LinearLayout linearestado;
    Dialog epicDialog;
    TextView txtnome,txtacompanhante,txtassento,txtestado;
    ImageView btnfechar,btnQRMarcar;
    Button btnMarcar;
    ConvidadoClass convidado;
    private IntentIntegrator qrScan;
    CRUD_CONVIDADO bd;
    Boolean retorno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marcar_presenca);
        btnQRMarcar = (ImageView)findViewById(R.id.btnQRMarcar);
        bd = new CRUD_CONVIDADO(this);

        //QR Code
        qrScan = new IntentIntegrator(this);

        btnQRMarcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();
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

    //Chamar a modal com os dados pesquisados
    public void modalMarcarPresenca(final int idConvidado){
        epicDialog = new Dialog(this);
        epicDialog.setContentView(R.layout.modalmarcarpresenca);
        txtnome = (TextView)epicDialog.findViewById(R.id.txtnome);
        txtacompanhante = (TextView)epicDialog.findViewById(R.id.txtacompanhante);
        txtassento = (TextView)epicDialog.findViewById(R.id.txtassento);
        txtestado = (TextView)epicDialog.findViewById(R.id.txtestado);
        linearestado = (LinearLayout)epicDialog.findViewById(R.id.linearestado);
        btnfechar = (ImageView)epicDialog.findViewById(R.id.btnfechar);
        btnMarcar = (Button)epicDialog.findViewById(R.id.btnMarcar);

        txtnome.setText(convidado.getNome().toString());
        txtacompanhante.setText(convidado.getAcompanhante().toString());
        txtassento.setText(convidado.getAssento().toString());
        txtestado.setText(convidado.getEstado().toString());

        if(convidado.getEstado().toString().equals("Presente")){
            linearestado.setBackgroundColor(Color.parseColor("#33c268"));
            btnMarcar.setVisibility(View.GONE);
        }

        btnfechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
            }
        });


        btnMarcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bd.ActualizarEstado(idConvidado)) {
                    Notificacao.Alerta(MarcarPresenca.this, "Convidado marcado com sucesso.", "#33c268");
                    epicDialog.dismiss();
                }else{
                    Notificacao.Alerta(MarcarPresenca.this,"Este convidado já encontra-se presente.","#ffdc4e");
                }
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    //Função que chama o QR Code
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Notificacao.Alerta(MarcarPresenca.this,"Operação cancelada.","#FFFC3939");
            } else {
                try {
                    convidado = bd.pegaConvidadoById(Integer.parseInt(result.getContents()));
                    modalMarcarPresenca(Integer.parseInt(result.getContents()));
                } catch (Exception e) {
                    Notificacao.Alerta(MarcarPresenca.this,"Nenhum registo encontrado","#FFFC3939");
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
