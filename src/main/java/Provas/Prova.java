package Provas;

import Atletas.Atleta;
import Etapas.Etapa;
import Eventos.Evento;
import Grupos.Grupo;
import Utils.Data;
import Utils.TipoProva;

import java.util.LinkedList;

public class Prova extends WithCriterio implements ObjectWithAtletas {
    private TipoProva tipoProva;
    private Data dataInicio;
    private String criterioProva;
    private Data dataFimPrevisto;
    private LinkedList<Atleta> atletas;
    private LinkedList<Etapa> etapas;

    private Evento evento;

    public Prova(TipoProva tipoProva, String criterioProva, Data dataInicio, Data dataFimPrevisto, Evento evento, char OperadorCriterio) {
        super(OperadorCriterio);
        this.tipoProva = tipoProva;
        this.dataInicio = dataInicio;
        this.dataFimPrevisto = dataFimPrevisto;
        this.atletas = new LinkedList<>();
        this.etapas = new LinkedList<>();
        this.evento = evento;
        this.criterioProva = criterioProva;
    }

    public Evento getEvento() {
        return evento;
    }

    @Override
    public char getOperador() {
        return super.getOperador();
    }

    @Override
    public int compare(double val1, double val2) {
        return super.compare(val1, val2);
    }


    public boolean atletaCanBeRemoved(Atleta atleta){
        for (Etapa etapa : etapas) {
            if (etapa.atletaCanBeRemoved(atleta) && etapa.getAtletas().contains(atleta))
                return false;
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

    public String getCriterioProva() {
        return criterioProva;
    }

    public void setCriterioProva(String criterioProva) {
        this.criterioProva = criterioProva;
    }

    public void setTipoProva(TipoProva tipoProva) {
        this.tipoProva = tipoProva;
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
