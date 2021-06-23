package Atletas;

import java.util.LinkedList;

public class GestorAtletas {
    private LinkedList<Atleta> atletas;

    public GestorAtletas() {
        atletas = new LinkedList<>();
    }

    public void addAtleta(Atleta atleta){
        atletas.add(atleta);
    }

    public LinkedList<Atleta> getAtletas() {
        return atletas;
    }

    private static class SingletonHolder {

        private static final GestorAtletas INSTANCE = new GestorAtletas();
    }

    // gets the one and only instance of Service
    public static GestorAtletas getInstance() {
        return GestorAtletas.SingletonHolder.INSTANCE;
    }
}
