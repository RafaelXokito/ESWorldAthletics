package Resultados;

import Atletas.Atleta;
import Grupos.Grupo;
import Utils.Data;

import java.util.Comparator;

public class Resultado {
    private Atleta atleta;
    private double valor;
    private Data data;

    private Grupo grupo;

    public Resultado(Atleta atleta, double valor, Data data, Grupo grupo) {
        this.atleta = atleta;
        this.valor = valor;
        this.data = data;
        this.grupo = grupo;
    }


    public int compare(double valor){
        return this.grupo.getEtapa().getProva().compare(this.valor, valor);
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

