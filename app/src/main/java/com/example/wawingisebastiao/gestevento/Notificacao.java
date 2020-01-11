package com.example.wawingisebastiao.gestevento;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Wawingi Sebastiao on 15/11/2019.
 */

public class Notificacao {

    public static void Alerta(Activity activity,String mensagem,String cor){
        Toast toast = Toast.makeText(activity, mensagem, Toast.LENGTH_SHORT);
        View v = toast.getView();
        v.setBackgroundColor(Color.parseColor(cor));
        toast.show();
    }

}
