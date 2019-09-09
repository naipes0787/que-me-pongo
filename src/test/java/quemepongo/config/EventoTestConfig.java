package quemepongo.config;

import org.junit.Before;
import quemepongo.frecuencia.FormatoFrecuencia;
import quemepongo.frecuencia.Frecuencia;
import quemepongo.model.evento.Evento;
import quemepongo.model.evento.Localizacion;
import quemepongo.model.evento.tipo.EventoRepetitivo;
import quemepongo.model.evento.tipo.TipoEvento;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public abstract class EventoTestConfig {

    protected LocalDateTime fechaHaceDosHoras;
    protected LocalTime horarioHaceDosHoras;
    protected LocalDateTime fechaEnDosHoras;
    protected LocalTime horarioEnDosHoras;
    protected LocalDateTime ahora;
    protected LocalTime horarioAhora;
    protected LocalDateTime fechaHaceDosDias;
    protected LocalDateTime fechaEnDosDias;

    @Before
    public void setup() {
        ahora = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        horarioAhora = ahora.toLocalTime();
        fechaHaceDosHoras = ahora.minusHours(2);
        horarioHaceDosHoras = fechaHaceDosHoras.toLocalTime();
        fechaEnDosHoras = ahora.plusHours(2);
        horarioEnDosHoras = fechaEnDosHoras.toLocalTime();
        fechaHaceDosDias = ahora.minusDays(2);
        fechaEnDosDias = ahora.plusDays(2);
    }

    protected Evento evento(TipoEvento tipo) {
        return new Evento("unEvento", Localizacion.CABA, tipo, Duration.ofHours(1));
    }

    protected Evento eventoDiario(LocalTime horario) {
        Frecuencia frecuencia = new Frecuencia(FormatoFrecuencia.DIARIA)
                                    .setMinuto(horario.getMinute())
                                    .setHora(horario.getHour());
        return evento(new EventoRepetitivo(frecuencia));
    }

    protected Evento eventoMensual(int diaDelMes, LocalTime horario) {
        Frecuencia frecuencia = new Frecuencia(FormatoFrecuencia.MENSUAL)
                .setMinuto(horario.getMinute())
                .setHora(horario.getHour())
                .setDiaDelMes(diaDelMes);
        return evento(new EventoRepetitivo(frecuencia));
    }
}
