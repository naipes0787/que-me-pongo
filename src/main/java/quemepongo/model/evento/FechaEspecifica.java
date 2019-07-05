package quemepongo.model.evento;

import java.time.LocalDateTime;

public class FechaEspecifica extends Ocurrencia {

    private LocalDateTime fecha;

    public FechaEspecifica(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public LocalDateTime fechaDelEvento() {
        return fecha;
    }
}
