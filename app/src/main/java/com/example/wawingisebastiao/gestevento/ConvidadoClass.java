package com.example.wawingisebastiao.gestevento;

import java.util.List;

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
    private String chegada;

    public ConvidadoClass() {
        this.id = id;
        this.nome = nome;
        this.assento = assento;
        this.acompanhante = acompanhante;
        this.id_evento = id_evento;
        this.estado = estado;
        this.chegada = chegada;
    }

    public String getChegada() {
        return chegada;
    }

    public void setChegada(String chegada) {
        this.chegada = chegada;
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

    //Contar acompanhantes de um convidado
    public static int totalAcompanhantes(List<ConvidadoClass> listaConvidados){
        int totAcomp = 0;

        for(ConvidadoClass conv : listaConvidados){
            if(!conv.getAcompanhante().equals("null")){
                if(conv.getAcompanhante().contains("|")){
                    char ac[] = conv.getAcompanhante().toCharArray();
                    for(int i=0; i< ac.length;i++){
                        if(ac[i]=='|') {
                            totAcomp++;
                            System.out.println("CONT 1: "+conv.getAcompanhante());
                        }
                    }
                    totAcomp++;
                }else{
                    totAcomp++;    System.out.println("CONT 2: "+conv.getAcompanhante());
                }
            }
        }
        return totAcomp;
    }

    //Contar acompanhantes ausente(s) de um convidado
    public static int totalAcompanhantesAusentes(List<ConvidadoClass> listaConvidados){
        int totAcomp = 0;

        for(ConvidadoClass conv : listaConvidados){
            if(!conv.getAcompanhante().equals("null") && conv.getEstado().equals("Ausente")){
                if(conv.getAcompanhante().contains("|")){
                    char ac[] = conv.getAcompanhante().toCharArray();
                    for(int i=0; i< ac.length;i++){
                        if(ac[i]=='|') {
                            totAcomp++;
                        }
                    }
                    totAcomp++;
                }else{
                    totAcomp++;
                }
            }
        }
        return totAcomp;
    }

    //Contar acompanhantes presente(s) de um convidado
    public static int totalAcompanhantesPresentes(List<ConvidadoClass> listaConvidados){
        int totAcomp = 0;

        for(ConvidadoClass conv : listaConvidados){
            if(!conv.getAcompanhante().equals("null") && conv.getEstado().equals("Presente")){
                if(conv.getAcompanhante().contains("|")){
                    char ac[] = conv.getAcompanhante().toCharArray();
                    for(int i=0; i< ac.length;i++){
                        if(ac[i]=='|') {
                            totAcomp++;
                        }
                    }
                    totAcomp++;
                }else{
                    totAcomp++;
                }
            }
        }
        return totAcomp;
    }
}
