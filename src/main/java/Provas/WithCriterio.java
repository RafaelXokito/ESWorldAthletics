package Provas;

public abstract class WithCriterio {
    private char operador;
    public WithCriterio(char operador) {
        this.operador = operador;
    }

    public char getOperador() {
        return operador;
    }

    //0 => val1 equals val2
    //>0 => val1 better val2
    //<0 => val1 worst val2
    public int compare(double val1, double val2){
        if (val1 == val2)
            return 0;
        switch (operador){
            case '>':
                return val1 > val2 ? 1 : -1;
            case '<':
                return val1 < val2 ? 1 : -1;
        }
        return -2;
    }
}
