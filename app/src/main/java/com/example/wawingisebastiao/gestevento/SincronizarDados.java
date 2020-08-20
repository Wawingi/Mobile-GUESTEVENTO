package com.example.wawingisebastiao.gestevento;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SincronizarDados extends AppCompatActivity {
    TextView txtconvidadospresentes,txtconvidadosausentes;
    Button btnSincronizarConvidados;
    CRUD_CONVIDADO bd;
    List<ConvidadoClass> listaConvidados;
    private static String URL = "http://192.168.1.100/gestevento/public/api/convidadoMudarEstadoAPI/";
    int contAusentes=0;
    boolean isActualizado=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sincronizardados);
        txtconvidadospresentes = (TextView)findViewById(R.id.txtconvidadospresentes);
        txtconvidadosausentes = (TextView)findViewById(R.id.txtconvidadosausentes);
        btnSincronizarConvidados = (Button)findViewById(R.id.btnSincronizarConvidados);

        listaConvidados = new ArrayList<>();
        bd = new CRUD_CONVIDADO(this);

        //Verificação da conexão para webservice
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = cm.getActiveNetworkInfo();

        try{
            if(bd.contConvidados("Ausente")!=null){
                contAusentes = bd.contConvidados("Ausente").size();
            }
            txtconvidadospresentes.setText(String.valueOf(bd.contConvidados("Presente").size()));
            txtconvidadosausentes.setText(String.valueOf(contAusentes));
            listaConvidados = bd.contConvidados("Presente");

            btnSincronizarConvidados.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(netInfo != null && netInfo.isConnectedOrConnecting()){
                        for (ConvidadoClass conv:listaConvidados) {
                            URL = URL + conv.getId() + "/"+conv.getChegada();
                            marcarPresenca();
                            URL = "http://192.168.1.100/gestevento/public/api/convidadoMudarEstadoAPI/";
                        }
                        System.out.println("IS ACT:"+isActualizado);
                        if(isActualizado){
                            Notificacao.Alerta(SincronizarDados.this,"Dados sincronizados com sucesso.","#33c268");
                        }else{
                            Notificacao.Alerta(SincronizarDados.this,"Houve um erro ao sincronizar, tente novamente.","#FFFC3939");
                        }
                    }else{
                        Notificacao.Alerta(SincronizarDados.this,"Conecte-se a internet para sincronizar","#FFFC3939");
                    }
                }
            });
        }catch(Exception e){
            System.out.println("BUG: "+e);
        }

        //APP BAR PERSONALIZADO
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Backbuton
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    //função marcar presença via web service
    public void marcarPresenca() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processando...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("data");
                            JSONObject o = array.getJSONObject(0);

                            if(o.getString("estado").equals("Presente")){
                                isActualizado=true;System.out.println("IS ACT Y:"+isActualizado);
                            }else{
                                isActualizado=false;System.out.println("IS ACT N:"+isActualizado);
                            }
                            URL = "http://192.168.1.100/gestevento/public/api/convidadoMudarEstadoAPI/";
                        } catch (JSONException e) {
                            e.printStackTrace();
                            URL = "http://192.168.1.100/gestevento/public/api/convidadoMudarEstadoAPI/";
                            System.out.println("BUG DAQUI: "+e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
