package com.example.wawingisebastiao.gestevento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Wawingi Sebastiao on 15/11/2019.
 */

public class Notificacao extends AppCompatActivity {
    Button btnMenuPrincipal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificacao);

        btnMenuPrincipal = (Button)findViewById(R.id.btnMenuPrincipal);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
