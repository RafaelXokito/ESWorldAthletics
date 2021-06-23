package Grupos;

import Atletas.Atleta;
import Resultados.Resultado;

import java.util.LinkedList;

public class Grupo {
    private LinkedList<Atleta> atletas;
    private LinkedList<Resultado> resultados;

    public Grupo(LinkedList<Atleta> atletas) {
        this.atletas = atletas;
        resultados = new LinkedList<>();
    }

    public LinkedList<Resultado> getResultados() {
        return resultados;
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
}
