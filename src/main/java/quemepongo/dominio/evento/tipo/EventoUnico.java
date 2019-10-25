package quemepongo.dominio.evento.tipo;

import quemepongo.excepcion.FechaEventoNoValidaException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue(TipoEventoFactory.UNICO)
public class EventoUnico extends TipoEvento {

    private LocalDateTime fecha;

    public EventoUnico(LocalDateTime fecha) {
        if (fecha.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
            throw new FechaEventoNoValidaException();
        }
        this.fecha = fecha;
    }

    public EventoUnico() {}

    @Override
    public LocalDateTime getFecha() {
        return fecha;
    }
}
