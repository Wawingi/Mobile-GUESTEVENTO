package com.example.wawingisebastiao.gestevento;

import android.app.Dialog;
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
import android.widget.TextView;

/**
 * Created by Wawingi Sebastiao on 09/01/2020.
 */

public class PesquisarConvidado extends AppCompatActivity {
    Button btnPesquisa;
    EditText editConvidado;
    TextView txtnome,txtacompanhante,txtassento,txtestado;
    Dialog epicDialog;
    ImageView btnfechar;
    CRUD_CONVIDADO bd;
    ConvidadoClass convidado;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesquisar_convidado);
        editConvidado = (EditText)findViewById(R.id.editConvidado);
        btnPesquisa = (Button)findViewById(R.id.btnPesquisa);
        bd = new CRUD_CONVIDADO(this);

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

        txtnome.setText(convidado.getNome().toString());
        txtacompanhante.setText(convidado.getAcompanhante().toString());
        txtassento.setText(convidado.getAssento().toString());
        txtestado.setText(convidado.getEstado().toString());

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
}
