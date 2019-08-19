package quemepongo.model.evento;

import org.junit.Test;
import quemepongo.exceptions.FechaEventoNoValidaException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class EventoTest {

    @Test(expected = FechaEventoNoValidaException.class)
    public void arrojarExcepcionSiSeIntentaCrearUnEventoConUnaFechaAnteriorALaActual() {
        evento(new FechaEspecifica(LocalDateTime.now().minusDays(1)));
    }

    @Test
    public void eventoRepetitivoConHoraDeInicioPreviaAActual() {
        Duration frecuencia = Duration.of(1, ChronoUnit.DAYS);
        LocalDateTime ahora = LocalDateTime.now();
        LocalTime horaInicio = ahora.minus(2, ChronoUnit.HOURS).toLocalTime();
        Evento evento = evento(new CadaCiertoTiempo(frecuencia, horaInicio));

        assertEquals(ahora.minus(2, ChronoUnit.HOURS).plus(1, ChronoUnit.DAYS), evento.getFecha());
    }

    @Test
    public void eventoRepetitivoConHoraDeInicioPosteriorAActual() {
        Duration frecuencia = Duration.of(1, ChronoUnit.DAYS);
        LocalDateTime fechaInicio = LocalDateTime.now().plus(2, ChronoUnit.HOURS);
        Evento evento = evento(new CadaCiertoTiempo(frecuencia, fechaInicio.toLocalTime()));

        assertEquals(fechaInicio, evento.getFecha());
    }

    private Evento evento(Ocurrencia ocurrencia) {
        return new Evento("unEvento", Localizacion.CABA, ocurrencia);
    }
}