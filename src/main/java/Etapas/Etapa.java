package Etapas;

import Grupos.Grupo;
import Utils.Genero;

import java.util.LinkedList;

public class Etapa {
    private String dataInicio;
    private String hora;
    private String ronda;
    private String diaCompeticao;
    private Genero genero;
    private String minimos;
    private LinkedList<Grupo> grupos;
    private boolean concluido;

    public Etapa(String dataInicio, String hora, String ronda, String diaCompeticao, Genero genero, String minimos) {
        this.dataInicio = dataInicio;
        this.hora = hora;
        this.ronda = ronda;
        this.diaCompeticao = diaCompeticao;
        this.genero = genero;
        this.minimos = minimos;
        grupos = new LinkedList<>();
    }

    public void adicionarGrupo(Grupo grupo){
        grupos.add(grupo);
    }

    public LinkedList<Grupo> getGrupos() {
        return grupos;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
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
}
