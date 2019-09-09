package quemepongo.frecuencia;

import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;

import java.util.function.Function;

import static com.cronutils.model.CronType.QUARTZ;

// Formato de expresion: {segundo} {minuto} {hora} {diaDelMes} {mes} {diaDeSemana} {año}
public enum FormatoFrecuencia {
    DIARIA(f -> String.format("0 %s %s * * ? *", f.getMinuto(), f.getHora())),
    SEMANAL(f -> String.format("0 %s %s ? * %s *", f.getMinuto(), f.getHora(), f.getDiaDeLaSemana())),
    MENSUAL(f -> String.format("0 %s %s %s * ? *", f.getMinuto(), f.getHora(), f.getDiaDelMes())),
    ANUAL(f -> String.format("0 %s %s %s %s ? *", f.getMinuto(), f.getHora(), f.getDiaDelMes(), f.getMes()));

    private Function<Frecuencia, String> formateador;
    private static final CronParser PARSER = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(QUARTZ));

    FormatoFrecuencia(Function<Frecuencia, String> formateador) {
        this.formateador = formateador;
    }

    public Cron obtenerExpresionCron(Frecuencia frecuencia) {
        return PARSER.parse(formateador.apply(frecuencia));
    }

}
