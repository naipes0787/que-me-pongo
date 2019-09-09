package quemepongo.model.evento.tipo;

import quemepongo.frecuencia.Frecuencia;

import java.time.LocalDateTime;

public class EventoRepetitivo implements TipoEvento {

    private Frecuencia frecuencia;

    public EventoRepetitivo(Frecuencia frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public LocalDateTime getFecha() {
        return frecuencia.proximaOcurrencia();
    }
}
