package Resultados;

import Atletas.Atleta;
import Utils.Data;

public class Resultado {
    private Atleta atleta;
    private double valor;
    private Data data;

    public Resultado(Atleta atleta, double valor, Data data) {
        this.atleta = atleta;
        this.valor = valor;
        this.data = data;
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
