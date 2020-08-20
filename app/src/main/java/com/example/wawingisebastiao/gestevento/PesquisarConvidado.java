package com.example.wawingisebastiao.gestevento;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Wawingi Sebastiao on 09/01/2020.
 */

public class PesquisarConvidado extends AppCompatActivity {
    LinearLayout linearestado;
    Button btnPesquisa;
    EditText editConvidado;
    TextView txtnome,txtacompanhante,txtassento,txtestado;
    ImageView btnQRPesuisar;
    Dialog epicDialog;
    ImageView btnfechar;
    CRUD_CONVIDADO bd;
    ConvidadoClass convidado;
    IntentIntegrator qrScan;
    int idConvidado;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesquisar_convidado);
        editConvidado = (EditText)findViewById(R.id.editConvidado);
        btnPesquisa = (Button)findViewById(R.id.btnPesquisa);
        btnQRPesuisar = (ImageView)findViewById(R.id.btnQRPesuisar);
        bd = new CRUD_CONVIDADO(this);

        //QR Code
        qrScan = new IntentIntegrator(this);
        qrScan.setPrompt("Capture o código QR de modo legível");
        qrScan.setOrientationLocked(false);
        qrScan.setDesiredBarcodeFormats(qrScan.QR_CODE_TYPES);

        btnQRPesuisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    qrScan.initiateScan();

                }catch (Exception e){
                    Notificacao.Alerta(PesquisarConvidado.this,"Nenhum registo encontrado.","#FFFC3939");
                }
            }
        });

        btnPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editConvidado.getText().toString().trim())){
                    editConvidado.setError("Preencha o campo vazio");
                }else {
                    try {
                        convidado = bd.pegaConvidado(editConvidado.getText().toString());
                        if(!convidado.equals(null))
                            editConvidado.setText("");
                        modalConvidado();
                    }catch (Exception e){
                        Notificacao.Alerta(PesquisarConvidado.this,"Nenhum registo encontrado.","#FFFC3939");
                    }
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

    //Chamar a modal com os dados pesquisados
    public void modalConvidado(){
        epicDialog = new Dialog(this);
        epicDialog.setContentView(R.layout.modalconvidadopesquisado);
        txtnome = (TextView)epicDialog.findViewById(R.id.txtnome);
        txtacompanhante = (TextView)epicDialog.findViewById(R.id.txtacompanhante);
        txtassento = (TextView)epicDialog.findViewById(R.id.txtassento);
        txtestado = (TextView)epicDialog.findViewById(R.id.txtestado);
        linearestado = (LinearLayout)epicDialog.findViewById(R.id.linearestado);

        txtnome.setText(convidado.getNome().toString());
        txtacompanhante.setText(convidado.getAcompanhante().toString());
        txtassento.setText(convidado.getAssento().toString());
        txtestado.setText(convidado.getEstado().toString());

        if(convidado.getEstado().toString().equals("Presente")){
            linearestado.setBackgroundColor(Color.parseColor("#82f2ab"));
        }

        btnfechar = (ImageView)epicDialog.findViewById(R.id.btnfechar);
        btnfechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
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
                Notificacao.Alerta(PesquisarConvidado.this,"Operação cancelada.","#FFFC3939");
            } else {
                try {
                    idConvidado = Integer.parseInt(result.getContents());
                    convidado = bd.pegaConvidadoById(Integer.parseInt(result.getContents()));
                    modalConvidado();
                } catch (Exception e) {
                    Notificacao.Alerta(PesquisarConvidado.this,"Nenhum registo encontrado","#FFFC3939");
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
