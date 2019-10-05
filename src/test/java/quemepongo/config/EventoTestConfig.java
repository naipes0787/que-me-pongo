package quemepongo.config;

import org.junit.Before;
import quemepongo.dominio.frecuencia.FormatoFrecuencia;
import quemepongo.dominio.frecuencia.Frecuencia;
import quemepongo.dominio.frecuencia.FrecuenciaBuilder;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.EventoRepetitivo;
import quemepongo.dominio.evento.tipo.TipoEvento;

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
        return new Evento("unEvento", Localizacion.CABA, tipo, Anticipacion.UNA_HORA_ANTES);
    }

    protected Evento eventoDiario(LocalTime horario) {
        Frecuencia frecuencia = new FrecuenciaBuilder(FormatoFrecuencia.DIARIA)
                                    .setMinuto(horario.getMinute())
                                    .setHora(horario.getHour())
                                    .build();
        return evento(new EventoRepetitivo(frecuencia));
    }

    protected Evento eventoMensual(int diaDelMes, LocalTime horario) {
        Frecuencia frecuencia = new FrecuenciaBuilder(FormatoFrecuencia.MENSUAL)
                                    .setMinuto(horario.getMinute())
                                    .setHora(horario.getHour())
                                    .setDiaDelMes(diaDelMes)
                                    .build();
        return evento(new EventoRepetitivo(frecuencia));
    }
}
