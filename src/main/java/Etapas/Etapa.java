package Etapas;

import Atletas.Atleta;
import Grupos.Grupo;
import Provas.ObjectWithAtletas;
import Provas.Prova;
import Utils.Data;
import Utils.Genero;

import java.util.LinkedList;

public class Etapa  implements ObjectWithAtletas {
    private Data dataInicio;
    private String hora;
    private String ronda;
    private String diaCompeticao;
    private Genero genero;
    private String minimos;
    private LinkedList<Grupo> grupos;
    private boolean concluido;
    private LinkedList<Atleta> atletas;

    private Prova prova;

    public Etapa(Data dataInicio, String hora, String ronda, String diaCompeticao, Genero genero, String minimos, Prova prova) {
        this.dataInicio = dataInicio;
        this.hora = hora;
        this.ronda = ronda;
        this.diaCompeticao = diaCompeticao;
        this.genero = genero;
        this.minimos = minimos;
        grupos = new LinkedList<>();
        atletas = getAtletas();
        this.prova = prova;
    }

    public Prova getProva() {
        return prova;
    }

    public void adicionarGrupo(Grupo grupo){
        grupos.add(grupo);
    }

    public LinkedList<Grupo> getGrupos() {
        return grupos;
    }

    public Data getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public String getDiaCompeticao() {
        return diaCompeticao;
    }

    public void setDiaCompeticao(String diaCompeticao) {
        this.diaCompeticao = diaCompeticao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getMinimos() {
        return minimos;
    }

    public void setMinimos(String minimos) {
        this.minimos = minimos;
    }

    public void setConcluido(){
        this.concluido = true;
    }

    public boolean getConcluido(){
        return this.concluido;
    }

    public LinkedList<Atleta> getAtletas() {
        LinkedList<Atleta> atletaLinkedList = new LinkedList<>();
        for (Grupo grupo : grupos) {
            for (Atleta atleta : grupo.getAtletas()) {
                if (!atletaLinkedList.contains(atleta))
                    atletaLinkedList.add(atleta);
            }
        }
        return atletaLinkedList;
    }

    public boolean atletaCanBeRemoved(Atleta atleta){
        for (Grupo grupo : grupos) {
            if (grupo.atletaCanBeRemoved(atleta) && grupo.getAtletas().contains(atleta))
                return false;
        }
        return true;
    }

}
