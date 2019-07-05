package quemepongo.model.evento;

import java.time.Duration;
import java.time.LocalDateTime;

public class CadaCiertoTiempo extends Ocurrencia {

    private Duration frecuencia;
    private LocalDateTime proximaVez;

    public CadaCiertoTiempo(Duration frecuencia, LocalDateTime fechaInicio) {
        this.frecuencia = frecuencia;
        this.proximaVez = fechaInicio;
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
