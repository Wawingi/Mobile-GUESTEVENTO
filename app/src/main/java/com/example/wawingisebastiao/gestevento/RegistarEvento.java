package com.example.wawingisebastiao.gestevento;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

/**
 * Created by Wawingi Sebastiao on 15/11/2019.
 */

public class RegistarEvento extends AppCompatActivity {
    private static String URL = "http://192.168.43.208/gestevento/public/api/convidados/";
    Button registarEventoWeb,registarEventoFicheiro;
    EditText edit_id_evento;
    ListView lv;
    CRUD_CONVIDADO bd;
    Boolean retorno;
    View viewLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registar_evento);
        registarEventoWeb = (Button)findViewById(R.id.btnRegistarEventoWeb);
        registarEventoFicheiro = (Button)findViewById(R.id.btnRegistarEventoFicheiro);
        edit_id_evento = (EditText)findViewById(R.id.IdEvento);

        //Alerta de notificação
        LayoutInflater layoutInflater = getLayoutInflater();
        viewLayout = layoutInflater.inflate(R.layout.alerta,(ViewGroup) findViewById(R.id.custom_alerta));

        bd = new CRUD_CONVIDADO(this);



        //Verificação da conexão para webservice
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            try{
                registarEventoWeb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edit_id_evento.getText().length()==0){
                            edit_id_evento.setError("ID do evento obrigatório.");
                            edit_id_evento.requestFocus();
                        }else{
                            try{
                                if(bd.pegaConvidados().size() > 0){

                                    Notificacao.Alerta(RegistarEvento.this,"Já possui um evento importado.","#ffdc4e");

                                    edit_id_evento.setText("");
                                    return;
                                }else{
                                    URL = URL + "" + edit_id_evento.getText().toString();
                                    pegaConvidados();
                                }
                            }catch (Exception e){
                                URL = URL + "" + edit_id_evento.getText().toString();
                                pegaConvidados();
                            }
                        }
                    }
                });
            }catch (Exception e){
                Toast.makeText(this,"falhei", Toast.LENGTH_SHORT).show();
            }
        }else{

        }

        //APP BAR PERSONALIZADO
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Backbutton
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    //função obter dados via web service
    public void pegaConvidados() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Importando...");
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

                            if(array.length()==0) {
                                URL = "http://192.168.43.208/gestevento/public/api/convidados/";
                                Notificacao.Alerta(RegistarEvento.this,"Nenhum evento encontrado, informe ID válido.","#FFFC3939");
                                return;
                            }
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                ConvidadoClass item = new ConvidadoClass();
                                item.setId(o.getInt("id"));
                                item.setNome(o.getString("nome"));
                                if (o.getString("acompanhante").isEmpty())
                                    item.setAcompanhante("Sem Acompanhante");
                                else {
                                    item.setAcompanhante(FormataAcompanhante(o.getString("acompanhante").trim()));
                                }
                                item.setAssento(o.getString("assento"));
                                item.setEstado("Ausente");
                                item.setId_evento(o.getInt("id_evento"));
                                retorno = bd.inserir(item);
                            }

                            if (retorno == true) {
                                URL = "http://192.168.43.208/gestevento/public/api/convidados/";
                                edit_id_evento.setText("");
                                Notificacao.Alerta(RegistarEvento.this,"Evento importado com sucesso.","#33c268");
                            } else
                                Toast.makeText(RegistarEvento.this, "Erro ao importar dados.", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
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

    //Função que aplica divisões entre acompanhantes, eliminando a do principio
    public static  String FormataAcompanhante(String acompanhante){
        System.out.println("ACOMP LENGTH: "+acompanhante);
        char chars[] = acompanhante.toCharArray();
        chars[0] = ' ';
        acompanhante = new String(chars);
        System.out.println("ACOMP: "+acompanhante);
        return acompanhante.trim();
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
