package quemepongo.model.evento;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CadaCiertoTiempo extends Ocurrencia {

    private Duration frecuencia;
    private LocalDateTime proximaVez;

    public CadaCiertoTiempo(Duration frecuencia, LocalTime horaInicio) {
        this.frecuencia = frecuencia;
        this.proximaVez = LocalDateTime.now().with(horaInicio);
    }

    @Override
    public LocalDateTime fechaDelEvento() {
        if (eventoYaPaso()) {
            proximaVez = proximaVez.plus(frecuencia);
        }
        return proximaVez;
    }

    private boolean eventoYaPaso() {
        return LocalDateTime.now().isAfter(proximaVez);
    }
}
