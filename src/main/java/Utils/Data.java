package Utils;

public class Data {
    private int ano;
    private int mes;
    private int dia;

    public Data(int ano, int mes, int dia) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }

    @Override
    public String toString() {
        return
                String.valueOf(dia) + '-' +
                String.valueOf(mes) + '-' +
                String.valueOf(ano);
    }
}
