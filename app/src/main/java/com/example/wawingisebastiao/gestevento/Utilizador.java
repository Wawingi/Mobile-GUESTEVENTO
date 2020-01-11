package com.example.wawingisebastiao.gestevento;

/**
 * Created by Wawingi Sebastiao on 07/11/2019.
 */

public class Utilizador {
    private String nome;
    private String email;
    int telefone;
    String perfil;
    String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Utilizador() {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.perfil = perfil;
        this.estado = estado;
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
}
