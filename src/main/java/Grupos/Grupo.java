package Grupos;

import Atletas.Atleta;
import Etapas.Etapa;
import Provas.ObjectWithAtletas;
import Provas.Prova;
import Resultados.Resultado;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Grupo implements ObjectWithAtletas {
    private int num; //Número do grupo
    private LinkedList<Atleta> atletas;
    private LinkedList<Resultado> resultados;
    private LinkedList<Atleta> vencedores;

    private Etapa etapa;

    public Grupo(int numGrupo, LinkedList<Atleta> atletas, Etapa etapa) {
        this.atletas = atletas;
        resultados = new LinkedList<>();
        this.num = numGrupo;
        vencedores = new LinkedList<>();
        this.etapa = etapa;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public LinkedList<Atleta> getVencedores() {
        return vencedores;
    }

    public void computeVencedores(){
        Collections.sort(resultados, new SortbyValor());
        for (int i = 0; i < (getAtletas().size()/2)+1; i++) {
            vencedores.add(resultados.get(i).getAtleta());
        }
    }

    public void adicionarVencedor(Atleta atleta){
        if (etapa.getProva().getAtletas().contains(atleta))
            vencedores.add(atleta);
    }

    public Atleta removerVencedor(Atleta atleta){
        return vencedores.remove(atleta) ? atleta : null;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public LinkedList<Resultado> getResultados() {
        return resultados;
    }

    public Resultado getResultado(Atleta atleta) {
        for (Resultado resultado: resultados) {
            if (resultado.getAtleta().equals(atleta))
                return resultado;
        }
        return null;
    }

    public void adicionarResultado(Resultado resultado) {
        if (atletas.contains(resultado.getAtleta()))
            resultados.add(resultado);
    }

    public LinkedList<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(LinkedList<Atleta> atletas) {
        this.atletas.addAll(atletas);
    }

    public boolean atletaCanBeRemoved(Atleta atleta){
        for (Resultado resultado: resultados) {
            if (resultado.getAtleta().equals(atleta))
                return false;
        }
        return true;
    }
}

class SortbyValor implements Comparator<Resultado>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Resultado a, Resultado b)
    {
        return a.compare(b.getValor());
    }
}
