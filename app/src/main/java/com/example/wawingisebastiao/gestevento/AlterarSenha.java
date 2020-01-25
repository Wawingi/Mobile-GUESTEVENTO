package com.example.wawingisebastiao.gestevento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Wawingi Sebastiao on 20/01/2020.
 */

public class AlterarSenha extends AppCompatActivity {
    EditText editSenha1,editSenha2;
    Button btnAlterar;
    int id=0;
    CRUD_UTILIZADOR bd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_senha);
        editSenha1 = (EditText)findViewById(R.id.editSenha1);
        editSenha2 = (EditText)findViewById(R.id.editSenha2);
        btnAlterar = (Button) findViewById(R.id.btnAlterar);
        bd = new CRUD_UTILIZADOR(this);

        //Recebe email da outra tela em função do utilizador a trocar a senha
        Intent recebeDados = getIntent();

        id = recebeDados.getIntExtra("id",-1);

        System.out.println("EMAIL:"+id);


        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alterar();
            }
        });

        //APP BAR PERSONALIZADO
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Backbutton
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void Alterar(){

        if(TextUtils.isEmpty(editSenha1.getText().toString().trim()) || TextUtils.isEmpty(editSenha2.getText().toString().trim())){
            editSenha1.setError("Preencha o campo vazio");
        }else if(!editSenha1.getText().toString().trim().equals(editSenha2.getText().toString().trim())){
            editSenha1.setError("As senhas devem ser iguais.");
            editSenha2.setError("As senhas devem ser iguais.");
        }else{
            if(bd.AlterarSenha(editSenha2.getText().toString().trim(),id)){
                Notificacao.Alerta(this,"Senha alterada com sucesso.","#33c268");
                Intent intent = new Intent(this,Login.class);
                startActivity(intent);
                finish();
            }else{
                Notificacao.Alerta(this,"Erro ao alterar a senha.","#FFFC3939");
            }
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
