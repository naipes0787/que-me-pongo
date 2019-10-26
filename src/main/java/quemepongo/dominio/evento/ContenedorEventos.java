package quemepongo.dominio.evento;

import java.util.List;

public class ContenedorEventos {
    private List<InformacionDeEvento> eventos;

    public List<InformacionDeEvento> getEventos() {
        return eventos;
    }

    public void setEventos(List<InformacionDeEvento> eventos) {
        this.eventos = eventos;
    }

    public ContenedorEventos(List<InformacionDeEvento> eventos) {
        this.eventos = eventos;
    }
}
