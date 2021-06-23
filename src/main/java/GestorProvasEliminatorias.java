import Etapas.Etapa;

import java.util.LinkedList;

public class GestorProvasEliminatorias {
    private LinkedList<Etapa> etapasEliminatorias;

    public GestorProvasEliminatorias() {
        etapasEliminatorias = new LinkedList<>();
    }

    public LinkedList<Etapa> getEtapasEliminatorias() {
        return etapasEliminatorias;
    }

    public void adicionarEtapaEliminatoria(Etapa etapa) {
        etapasEliminatorias.add(etapa);
    }


    private static class SingletonHolder {

        private static final GestorProvasEliminatorias INSTANCE = new GestorProvasEliminatorias();
    }

    // gets the one and only instance of Service
    public static GestorProvasEliminatorias getInstance() {
        return GestorProvasEliminatorias.SingletonHolder.INSTANCE;
    }
}
