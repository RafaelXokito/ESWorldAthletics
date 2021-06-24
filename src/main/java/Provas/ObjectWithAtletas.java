package Provas;

import Atletas.Atleta;
import Etapas.Etapa;
import Grupos.Grupo;

import java.util.LinkedList;

public interface ObjectWithAtletas {
    LinkedList<Atleta> atletas = new LinkedList<>();

    default LinkedList<Atleta> getAtletas() {
        return atletas;
    }

    default void adicionarAtleta(Atleta atleta){
        atletas.add(atleta);
    }

    default Atleta removerAtleta(Atleta atleta){
        return atletas.remove(atleta) ? atleta : null;
    }

    default boolean atletaCanBeRemoved(Atleta atleta){
        return false;
    }

}
