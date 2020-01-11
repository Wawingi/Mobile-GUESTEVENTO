package com.example.wawingisebastiao.gestevento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wawingi Sebastiao on 07/11/2019.
 */

public class CRUD_CONVIDADO extends SQLiteOpenHelper {
    long res;
    private static final String NOME_BD = "gestevento";
    //Tabela PERDIDOS E ACHADOS
    private static final String TABELACONVIDADO = "convidado";
    private static final String id="id";
    private static final String nome="nome";
    private static final String acompanhante="acompanhante";
    private static final String assento="assento";
    private static final String estado="estado";
    private static final String id_evento="id_evento";

    public CRUD_CONVIDADO(Context context) {
        super(context, TABELACONVIDADO, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("CREATE TABLE " + TABELACONVIDADO + "(id INTEGER PRIMARY KEY, nome TEXT, acompanhante TEXT, assento TEXT, estado TEXT, id_evento INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        bd.execSQL("DROP TABLE IF EXISTS " + TABELACONVIDADO);
    }

    public Boolean inserir(ConvidadoClass conv){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(id,conv.getId());
        cv.put(nome,conv.getNome());
        cv.put(acompanhante,conv.getAcompanhante());
        cv.put(assento,conv.getAssento());
        cv.put(estado,conv.getEstado());
        cv.put(id_evento,conv.getId_evento());
        res = bd.insert(TABELACONVIDADO,null,cv);
        if(res==0) {
            return false;
        }else {
            return true;
        }
    }

    public Boolean eliminar(int id){
        SQLiteDatabase bd=this.getWritableDatabase();
        res = bd.delete(TABELACONVIDADO,id_evento + "=" + id,null);
        if(res==0) {
            return false;
        }else {
            return true;
        }
    }

    public List<ConvidadoClass> pegaConvidados(){
        ConvidadoClass conv=null;
        SQLiteDatabase bd = this.getWritableDatabase();
        List<ConvidadoClass> lista = new ArrayList<>();
        Cursor c = bd.rawQuery("SELECT * FROM " + TABELACONVIDADO ,null);
        c.moveToFirst();
        do{
            conv = new ConvidadoClass();
            conv.setNome(c.getString(1));
            conv.setAcompanhante(c.getString(2));
            conv.setAssento(c.getString(3));
            conv.setEstado(c.getString(4));
            lista.add(conv);
        }while (c.moveToNext());
        c.close();
        return lista;
    }

    public ConvidadoClass pegaConvidado(String nomeconvidado){
        ConvidadoClass conv=null;
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor c = bd.rawQuery("SELECT * FROM " + TABELACONVIDADO + " WHERE " + nome + " LIKE '%" + nomeconvidado + "%'",null);
        c.moveToFirst();
        do{
            conv = new ConvidadoClass();
            conv.setId(c.getInt(0));
            conv.setNome(c.getString(1));
            conv.setAcompanhante(c.getString(2));
            conv.setAssento(c.getString(3));
            conv.setEstado(c.getString(4));
        }while (c.moveToNext());
        c.close();
        return conv;
    }

    public List<ConvidadoClass> pegaAssentos(){
        ConvidadoClass ass=null;
        SQLiteDatabase bd = this.getWritableDatabase();
        List<ConvidadoClass> lista = new ArrayList<>();
        Cursor c = bd.rawQuery("SELECT DISTINCT("+ assento +")FROM " + TABELACONVIDADO ,null);
        c.moveToFirst();
        do{
            ass = new ConvidadoClass();
            //assento.setNome(c.getString(1));
            //assento.setAcompanhante(c.getString(2));
            ass.setAssento(c.getString(0));
            //assento.setEstado(c.getString(4));
            lista.add(ass);
        }while (c.moveToNext());
        c.close();
        return lista;
    }
}
