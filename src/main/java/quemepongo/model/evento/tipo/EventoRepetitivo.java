package quemepongo.model.evento.tipo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventoRepetitivo implements TipoEvento {

    private Duration frecuencia;
    private LocalTime horario;

    public EventoRepetitivo(Duration frecuencia, LocalTime horario) {
        this.frecuencia = frecuencia;
        this.horario = horario;
    }

    @Override
    public LocalDateTime getFecha() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime hoyAlHorario = ahora.with(horario);
        return ahora.isAfter(hoyAlHorario) ? hoyAlHorario.plus(frecuencia) : hoyAlHorario;
    }
}
