package com.example.wawingisebastiao.gestevento;

/**
 * Created by Wawingi Sebastiao on 07/11/2019.
 */

public class Utilizador {
    int id;
    String nome;
    String email;
    String senha;
    int telefone;
    String perfil;
    String estado;
    int qtdVezes;

    public Utilizador() {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.perfil = perfil;
        this.estado = estado;
        this.qtdVezes = qtdVezes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getQtdVezes() {
        return qtdVezes;
    }

    public void setQtdVezes(int qtdVezes) {
        this.qtdVezes = qtdVezes;
    }
}
