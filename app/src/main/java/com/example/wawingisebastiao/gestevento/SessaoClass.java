package com.example.wawingisebastiao.gestevento;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Wawingi Sebastiao on 22/01/2020.
 */

public class SessaoClass {
    String FileName = "sessao";

    public void guardarSessao(){
        /*SharedPreferences sharedPreferences = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nome","Wawingi Sebastiao");
        editor.commit();*/
    }

    public void lerSessao(){
        /*SharedPreferences sharedPreferences = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "Default";
        String nome = sharedPreferences.getString("nome",defaultValue);
        System.out.println("NOME SESSAO: "+nome);*/
    }
}
