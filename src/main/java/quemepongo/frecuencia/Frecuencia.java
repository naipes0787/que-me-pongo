package quemepongo.frecuencia;

import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.cronutils.model.CronType.QUARTZ;

public class Frecuencia {

    private static final CronParser PARSER = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(QUARTZ));

    private Cron cron;

    public Frecuencia(String expresionCron) {
        this.cron = PARSER.parse(expresionCron);
    }

    public LocalDateTime calcularProximaOcurrencia() {
        ZonedDateTime ahora = LocalDateTime.now().atZone(ZoneId.systemDefault());
        ZonedDateTime proxima = ExecutionTime.forCron(cron).nextExecution(ahora)
                .orElseThrow(() -> new RuntimeException("No se pudo calcular la próxima fecha de ejecución del evento repetitivo"));
        return proxima.toLocalDateTime();
    }

    // TODO: se deja listo este método para cuando se tenga que usar desde el converter de hibernate
    public String expresion() {
        return cron.asString();
    }

}
