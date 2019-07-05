package quemepongo.model.evento;

import quemepongo.exceptions.FechaEventoNoValidaException;

import java.time.LocalDateTime;

public class FechaEspecifica extends Ocurrencia {

    private LocalDateTime fecha;

    public FechaEspecifica(LocalDateTime fecha) {
        if(fecha.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
            throw new FechaEventoNoValidaException();
        }
        this.fecha = fecha;
    }

    @Override
    public LocalDateTime fechaDelEvento() {
        return fecha;
    }
}
