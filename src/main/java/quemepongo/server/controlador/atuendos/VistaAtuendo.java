package quemepongo.server.controlador.atuendos;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.sugerencia.Atuendo;

public class VistaAtuendo {

    private Atuendo atuendo;
    private Evento evento;

    public VistaAtuendo(Atuendo atuendo, Evento evento) {
        this.atuendo = atuendo;
        this.evento = evento;
    }

    public Atuendo getAtuendo() {
        return atuendo;
    }

    public Evento getEvento() {
        return evento;
    }
}
