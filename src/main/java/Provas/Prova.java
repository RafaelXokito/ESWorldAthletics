package Provas;

import Atletas.Atleta;
import Etapas.Etapa;
import Grupos.Grupo;
import Utils.Data;
import Utils.TipoProva;

import java.util.LinkedList;

public class Prova {
    private TipoProva tipoProva;
    private String criterioProva;
    private Data dataInicio;
    private Data dataFimPrevisto;
    private LinkedList<Atleta> atletas;
    private LinkedList<Etapa> etapas;

    public Prova(TipoProva tipoProva, String criterioProva, Data dataInicio, Data dataFimPrevisto) {
        this.tipoProva = tipoProva;
        this.criterioProva = criterioProva;
        this.dataInicio = dataInicio;
        this.dataFimPrevisto = dataFimPrevisto;
        this.atletas = new LinkedList<>();
        this.etapas = new LinkedList<>();
    }

    public boolean atletaCanBeRemoved(Atleta atleta){
        for (Etapa etapa : etapas) {
            for (Grupo grupo : etapa.getGrupos()) {
                if (grupo.getAtletas().contains(atleta))
                    return false;
            }
        }
        return true;
    }

    public Atleta removerAtleta(Atleta atleta){
        return atletas.remove(atleta) ? atleta : null;
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

    public Data getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Data getDataFimPrevisto() {
        return dataFimPrevisto;
    }

    public void setDataFimPrevisto(Data dataFimPrevisto) {
        this.dataFimPrevisto = dataFimPrevisto;
    }
}
