package Eventos;

import Provas.Prova;
import Utils.Data;

import java.util.LinkedList;

public class Evento {
    private LinkedList<Prova> provas;
    private String nome;
    private String pais;
    private String local;
    private Data dataInicio;
    private Data dataFim;

    public Evento(String nome, String pais, String local, Data dataInicio, Data dataFim) {
        this.nome = nome;
        this.pais = pais;
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        provas = new LinkedList<>();
    }

    public void adicionarProva(Prova prova){
        this.provas.add(prova);
    }

    public LinkedList<Prova> getProvas() {
        return provas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Data getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Data getDataFim() {
        return dataFim;
    }

    public void setDataFim(Data dataFim) {
        this.dataFim = dataFim;
    }
}
