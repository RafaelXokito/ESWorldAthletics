package Utils;

public enum TipoProva {
    Salto_em_Comprimento ("Salto em Comprimento"),
    Salto_em_Altura ("Salto em Altura");

    private final String name;

    private TipoProva(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

    public static TipoProva getTipoProva(String s) {
        for (TipoProva tipoProva : TipoProva.values()) {
            if (s.equals(tipoProva.toString()) || TipoProva.valueOf(s) == tipoProva){
                return tipoProva;
            }
        }
        return null;
    }
}
