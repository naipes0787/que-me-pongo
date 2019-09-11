package quemepongo.model.evento.tipo;

import quemepongo.exceptions.FechaEventoNoValidaException;

import java.time.LocalDateTime;

public class EventoUnico implements TipoEvento {

    private LocalDateTime fecha;

    public EventoUnico(LocalDateTime fecha) {
        if (fecha.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
            throw new FechaEventoNoValidaException();
        }
        this.fecha = fecha;
    }

    @Override
    public LocalDateTime getFecha() {
        return fecha;
    }
}
