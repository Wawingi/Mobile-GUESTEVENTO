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

public class CRUD_UTILIZADOR extends SQLiteOpenHelper {
    private static final String NOME_BD = "gestevento";

    //Tabela PERDIDOS E ACHADOS
    private static final String TABELAUTILIZADOR = "utilizador";
    private static final String id="id";
    private static final String nome="nome";
    private static final String email="email";
    private static final String telefone="telefone";
    private static final String perfil="perfil";

    public CRUD_UTILIZADOR(Context context) {
        super(context, TABELAUTILIZADOR, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("CREATE TABLE " + TABELAUTILIZADOR + "(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT unique, telefone INTEGER unique, perfil TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        bd.execSQL("DROP TABLE IF EXISTS " + TABELAUTILIZADOR);
    }

    public Boolean inserir(Utilizador U){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(nome,U.getNome());
        cv.put(email,U.getEmail());
        cv.put(telefone, U.getTelefone());
        cv.put(perfil,U.getPerfil());
        long res = bd.insert(TABELAUTILIZADOR,null,cv);
        if(res==0) {
            return false;
        }else {
            return true;
        }
    }

    public List<Utilizador> pegaDados(){
        Utilizador u=null;
        SQLiteDatabase bd = this.getWritableDatabase();
        List<Utilizador> lista = new ArrayList<>();
        Cursor c = bd.rawQuery("SELECT * FROM " + TABELAUTILIZADOR ,null);
        c.moveToFirst();
        do{
            u = new Utilizador();
            u.setNome(c.getString(1));
            u.setEmail(c.getString(2));
            u.setTelefone(c.getInt(3));
            u.setPerfil(c.getString(4));
            lista.add(u);
        }while (c.moveToNext());
            c.close();
        return lista;
    }

    /*public List<PerdidoClass> pegaDados(){
        PerdidoClass p=null;
        SQLiteDatabase bd = this.getWritableDatabase();
        List<PerdidoClass> lista = new ArrayList<>();
        Cursor c = bd.rawQuery("SELECT * FROM " + TABELAPERDIDO ,null);
        c.moveToFirst();
        do{
            p = new PerdidoClass();
            p.setNome(c.getString(1));
            p.setDescricao(c.getString(2));
            lista.add(p);
        }while (c.moveToNext());
        c.close();
        return lista;
    }

    public void eliminar(String tit,String desc){
        SQLiteDatabase bd=this.getWritableDatabase();
        String v[] = new String[2];
        v[0]=tit;
        v[1]=desc;
        bd.delete(TABELAPERDIDO,titulo+"=? AND "+descricao+"=?",v);
    }*/

}
