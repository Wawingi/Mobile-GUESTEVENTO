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
    private static final String senha="senha";
    private static final String telefone="telefone";
    private static final String perfil="perfil";
    private static final String estado="estado";
    private static final String qtdVezes="qtdVezes";


    public CRUD_UTILIZADOR(Context context) {
        super(context, TABELAUTILIZADOR, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("CREATE TABLE " + TABELAUTILIZADOR + "(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT unique, senha TEXT NOT NULL, telefone INTEGER unique, perfil TEXT, estado TEXT, qtdVezes INTEGER)");
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
        cv.put(senha,U.getSenha());
        cv.put(telefone, U.getTelefone());
        cv.put(perfil,U.getPerfil());
        cv.put(estado,U.getEstado());
        cv.put(qtdVezes,U.getQtdVezes());
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
            u.setTelefone(c.getInt(4));
            u.setPerfil(c.getString(5));
            u.setEstado(c.getString(6));
            lista.add(u);
        }while (c.moveToNext());
            c.close();
        return lista;
    }

    //Logar de um utilizador
    public Utilizador Login(String Username,String password){
        String est="Activo";

        Utilizador utilizador=null;
        SQLiteDatabase bd = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABELAUTILIZADOR + " WHERE " + email + " = '" + Username + "'" + "AND "+ senha + " = '" + password + "'" + "AND "+ estado + " = '" + est + "'";
        Cursor c = bd.rawQuery(query,null);
        c.moveToFirst();
        do{
            utilizador = new Utilizador();
            utilizador.setId(c.getInt(0));
            utilizador.setNome(c.getString(1));
            utilizador.setEmail(c.getString(2));
            utilizador.setSenha(c.getString(3));
            utilizador.setTelefone(c.getInt(4));
            utilizador.setPerfil(c.getString(5));
            utilizador.setEstado(c.getString(6));
            utilizador.setQtdVezes(c.getInt(7));
        }while (c.moveToNext());
        c.close();
        return utilizador;
    }

    //Actualizar senha do utilizador
    public Boolean AlterarSenha(String novaSenha,int idLido){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(senha,novaSenha);
        cv.put(qtdVezes,1);
        System.out.println("SENHA:"+novaSenha);
        long res = bd.update(TABELAUTILIZADOR,cv,"id="+idLido,null);
        return res==0 ? false : true;
    }

}
