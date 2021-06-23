package Eventos;

import Eventos.Evento;

import java.io.Serializable;
import java.util.LinkedList;

public class GestorEventos implements Serializable {
    private LinkedList<Evento> eventos;

    public GestorEventos() {
        eventos = new LinkedList<Evento>();
    }

    public void addEvento(Evento evento){
        eventos.add(evento);
    }

    public LinkedList<Evento> getEventos() {
        return eventos;
    }

    private static class SingletonHolder {

        private static final GestorEventos INSTANCE = new GestorEventos();
    }

    // gets the one and only instance of Service
    public static GestorEventos getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
