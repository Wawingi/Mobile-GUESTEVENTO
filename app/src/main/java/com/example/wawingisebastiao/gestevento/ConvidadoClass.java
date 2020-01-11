package com.example.wawingisebastiao.gestevento;

/**
 * Created by Wawingi Sebastiao on 29/12/2019.
 */

public class ConvidadoClass {
    private int id;
    private String nome;
    private String assento;
    private String acompanhante;
    private int id_evento;
    private String estado;

    public ConvidadoClass() {
        this.id = id;
        this.nome = nome;
        this.assento = assento;
        this.acompanhante = acompanhante;
        this.id_evento = id_evento;
        this.estado = estado;
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

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    public String getAcompanhante() {
        return acompanhante;
    }

    public void setAcompanhante(String acompanhante) {
        this.acompanhante = acompanhante;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
