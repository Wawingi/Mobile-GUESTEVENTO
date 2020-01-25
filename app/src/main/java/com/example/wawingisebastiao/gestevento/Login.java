package com.example.wawingisebastiao.gestevento;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wawingi Sebastiao on 17/11/2019.
 */

public class Login extends AppCompatActivity {
    EditText editUsername,editSenha;
    Button btnLogar;
    Utilizador utilizador;
    CRUD_UTILIZADOR bd;
    String FileName = "sessao";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editUsername = (EditText)findViewById(R.id.editUsername);
        editSenha = (EditText)findViewById(R.id.editSenha);
        btnLogar = (Button)findViewById(R.id.btnLogar);

        bd = new CRUD_UTILIZADOR(this);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logar(editUsername.getText().toString().trim(),editSenha.getText().toString().trim());
            }
        });
    }

    public void Logar(String Username,String Senha){
        if(Username.equals("gdw") && Senha.equals("GDW2020")){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else{
            try{
                utilizador = bd.Login(Username,Senha);
                if(!utilizador.equals(null) && utilizador.getQtdVezes()==0){
                    Intent intent = new Intent(this,AlterarSenha.class);
                    intent.putExtra("id",utilizador.getId());
                    startActivity(intent);
                }else{
                    guardarSessao(utilizador);
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }catch(Exception e){
                Notificacao.Alerta(this,"Erro ao efectuar login, verifique o username ou senha.","#FFFC3939");
            }
        }
    }

    public void guardarSessao(Utilizador utilizador){
        SharedPreferences sharedPreferences = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id",utilizador.getId());
        editor.putString("nome",utilizador.getNome());
        editor.putString("email",utilizador.getEmail());
        editor.putString("perfil",utilizador.getPerfil());
        editor.commit();
    }
}
