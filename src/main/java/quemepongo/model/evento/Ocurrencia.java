package quemepongo.model.evento;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Ocurrencia {

    public boolean estaProxima(Duration tiempoDeAnticipacion) {
        LocalDateTime fechaDelEvento = fechaDelEvento();
        LocalDateTime fechaAnticipacion = fechaDelEvento.minus(tiempoDeAnticipacion);
        LocalDateTime ahora = LocalDateTime.now();

        return ahora.isAfter(fechaAnticipacion) && ahora.isBefore(fechaDelEvento);
    }

    public abstract LocalDateTime fechaDelEvento();
}