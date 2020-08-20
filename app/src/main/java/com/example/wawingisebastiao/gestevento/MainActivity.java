package com.example.wawingisebastiao.gestevento;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout btnMarcarPresenca,btnListarConvidados,btnListarAssento,btnPesquisa,btnContabilidade,btnEstatistica;
    TextView txtnomeUtilizador,txtemailUtilizador,txtperfilUtilizador;
    String FileName = "sessao";
    int id;
    String nome,email,perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Manipulação dos botões da tela principal
        btnMarcarPresenca = (LinearLayout)findViewById(R.id.btnMarcarPresenca);
        btnListarConvidados = (LinearLayout)findViewById(R.id.btnListarConvidados);
        btnListarAssento = (LinearLayout)findViewById(R.id.btnListarAssento);
        btnPesquisa = (LinearLayout)findViewById(R.id.btnPesquisa);
        btnContabilidade = (LinearLayout)findViewById(R.id.btnContabilidade);
        btnEstatistica = (LinearLayout)findViewById(R.id.btnEstatistica);

        btnMarcarPresenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),MarcarPresenca.class);
                startActivity(intent);
            }
        });

        btnListarConvidados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),ListarConvidados.class);
                startActivity(intent);
            }
        });

        btnListarAssento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),ListarAssento.class);
                startActivity(intent);
            }
        });

        btnPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),PesquisarConvidado.class);
                startActivity(intent);
            }
        });

        btnContabilidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),Contabilidade.class);
                startActivity(intent);
            }
        });

        btnEstatistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),Estatistica.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Pegar dados da sessão e associar a menu lateral

        View headview = navigationView.getHeaderView(0);
        txtnomeUtilizador = headview.findViewById(R.id.txtnomeUtilizador);
        txtemailUtilizador = headview.findViewById(R.id.txtemailUtilizador);
        txtperfilUtilizador = headview.findViewById(R.id.txtperfilUtilizador);
        lerSessao();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(perfil.equals("Administrador")) {
            if (id == R.id.registar_utilizador) {
                Intent intent = new Intent(this, RegistarUtilizador.class);
                startActivity(intent);
            } else if (id == R.id.listar_utilizador) {
                Intent intent = new Intent(this, ListarUtilizador.class);
                startActivity(intent);
            } else if (id == R.id.registar_evento) {
                Intent intent = new Intent(this, RegistarEvento.class);
                startActivity(intent);
            } else if (id == R.id.eliminar_evento) {
                Intent intent = new Intent(this, ApagarEvento.class);
                startActivity(intent);
            } else if (id == R.id.gerar_relatorio) {
                //Intent intent = new Intent(this,Login.class);
                //startActivity(intent);
            } else if(id == R.id.sincronizar_dados){
                Intent intent = new Intent(this, SincronizarDados.class);
                startActivity(intent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void lerSessao(){
        SharedPreferences sharedPreferences = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "Default";
        id = sharedPreferences.getInt("id",0);
        nome = sharedPreferences.getString("nome","ADMIN");
        email = sharedPreferences.getString("email","gdw@gdw.co.ao");
        perfil = sharedPreferences.getString("perfil","Administrador");

        txtnomeUtilizador.setText(nome);
        txtemailUtilizador.setText(email);
        txtperfilUtilizador.setText(perfil);
    }
}
