package quemepongo.model.evento.tipo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Anticipacion {

    private ChronoUnit unidadTiempo;
    private long tiempo;

    public static final Anticipacion UNA_HORA_ANTES = new Anticipacion(ChronoUnit.HOURS, 1);

    public Anticipacion(ChronoUnit unidadTiempo, long tiempo) {
        this.unidadTiempo = unidadTiempo;
        this.tiempo = tiempo;
    }

    public LocalDateTime getFecha(LocalDateTime fechaEvento) {
        return fechaEvento.minus(Duration.of(tiempo, unidadTiempo));
    }

    public ChronoUnit getUnidadTiempo() {
        return unidadTiempo;
    }

    public long getTiempo() {
        return tiempo;
    }

}
