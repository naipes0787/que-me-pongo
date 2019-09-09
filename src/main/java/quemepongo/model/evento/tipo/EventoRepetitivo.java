package quemepongo.model.evento.tipo;

import com.cronutils.model.Cron;
import com.cronutils.model.time.ExecutionTime;
import quemepongo.frecuencia.Frecuencia;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class EventoRepetitivo implements TipoEvento {

    private Frecuencia frecuencia;

    public EventoRepetitivo(Frecuencia frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public LocalDateTime getFecha() {
        Cron cron = frecuencia.obtenerExpresionCron();
        ZonedDateTime ahora = LocalDateTime.now().atZone(ZoneId.systemDefault());
        ZonedDateTime proxima = ExecutionTime.forCron(cron).nextExecution(ahora)
                .orElseThrow(() -> new RuntimeException("No se pudo calcular la próxima fecha de ejecución del evento repetitivo"));
        return proxima.toLocalDateTime();
    }
}
