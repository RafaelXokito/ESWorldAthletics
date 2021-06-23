package Provas;

import Atletas.Atleta;
import Etapas.Etapa;
import Utils.TipoProva;

import java.util.LinkedList;

public class Prova {
    private TipoProva tipoProva;
    private String criterioProva;
    private String dataInicio;
    private String dataFimPrevisto;
    private LinkedList<Atleta> atletas;
    private LinkedList<Etapa> etapas;

    public Prova(TipoProva tipoProva, String criterioProva, String dataInicio, String dataFimPrevisto) {
        this.tipoProva = tipoProva;
        this.criterioProva = criterioProva;
        this.dataInicio = dataInicio;
        this.dataFimPrevisto = dataFimPrevisto;
        this.atletas = new LinkedList<>();
        this.etapas = new LinkedList<>();
    }

    public void adicionarAtleta(Atleta atleta){
        this.atletas.add(atleta);
    }

    public void adicionarEtapa(Etapa etapa){
        this.etapas.add(etapa);
    }

    public LinkedList<Atleta> getAtletas() {
        return atletas;
    }

    public LinkedList<Etapa> getEtapas() {
        return etapas;
    }

    public TipoProva getTipoProva() {
        return tipoProva;
    }

    public void setTipoProva(TipoProva tipoProva) {
        this.tipoProva = tipoProva;
    }

    public String getCriterioProva() {
        return criterioProva;
    }

    public void setCriterioProva(String criterioProva) {
        this.criterioProva = criterioProva;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFimPrevisto() {
        return dataFimPrevisto;
    }

    public void setDataFimPrevisto(String dataFimPrevisto) {
        this.dataFimPrevisto = dataFimPrevisto;
    }
}
