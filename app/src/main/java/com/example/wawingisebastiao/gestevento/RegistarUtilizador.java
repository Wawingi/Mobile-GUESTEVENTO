package com.example.wawingisebastiao.gestevento;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wawingi Sebastiao on 23/10/2019.
 */

public class RegistarUtilizador extends AppCompatActivity {
    Button registarUtilizador;
    EditText txtnome,txtemail,txttelefone;
    RadioGroup radioGroup;


    String nome;
    String email;
    int telefone = 0;
    String perfil="";


    CRUD_UTILIZADOR bd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registar_utilizador);
        registarUtilizador = (Button)findViewById(R.id.btnRegistarUtilizador);
        txtnome = (EditText)findViewById(R.id.nome);
        txtemail = (EditText)findViewById(R.id.email);
        radioGroup = findViewById(R.id.radioGrupo);
        txttelefone = (EditText)findViewById(R.id.telefone);

        //APP BAR PERSONALIZADO
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bd = new CRUD_UTILIZADOR(this);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.admin:
                        perfil="Administrador";
                        break;
                    case R.id.protocolo:
                        perfil="Protocolo";
                        break;
                }

            }
        });

        registarUtilizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registar();
            }
        });

        //registarUtilizador.setEnabled(false);
        //registarUtilizador.setBackgroundColor(Color.parseColor("red"));

        //registarUtilizador.setFocusable(true);0x7f070070

        //Backbuton
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }



    public boolean emailValidacao(String email){
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PADRAO = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PADRAO);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void registar(){

        if(TextUtils.isEmpty(txtnome.getText().toString().trim()) || TextUtils.isEmpty(txtemail.getText().toString().trim()) || TextUtils.isEmpty(txttelefone.getText().toString().trim()) || perfil.isEmpty()){
            txtnome.setError("Preencha o campo vazio");
        }else if(!emailValidacao(txtemail.getText().toString())){
            txtemail.setError("Informe um email válido !!");
        }else{

            telefone = Integer.parseInt(txttelefone.getText().toString());
            nome = txtnome.getText().toString();
            email = txtemail.getText().toString();

            Utilizador u = new Utilizador();
            u.setNome(this.nome);
            u.setEmail(this.email);
            u.setTelefone(telefone);
            u.setPerfil(perfil);
            u.setEstado("Activo");

            Boolean retorno = bd.inserir(u);

            if (retorno == true) {
                Notificacao.Alerta(RegistarUtilizador.this,"Utilizador criado com sucesso.","#33c268");
                txtnome.setText("");
                txtemail.setText("");
                txttelefone.setText("");
                radioGroup.setOnCheckedChangeListener(null);
                Intent intent = new Intent(this,ListarUtilizador.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(RegistarUtilizador.this, "Erro ao importar", Toast.LENGTH_SHORT).show();
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
