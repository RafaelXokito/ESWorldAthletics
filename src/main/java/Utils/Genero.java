package Utils;

public enum Genero {
    M ("Masculino"),
    F ("Feminino"),
    Nao_Defenido ("Não Defenido"); //Misto

    private final String name;

    private Genero(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
