package quemepongo.dominio.evento;

import org.junit.Test;
import quemepongo.config.EventoTestConfig;
import quemepongo.dominio.evento.tipo.EventoUnico;
import quemepongo.excepcion.FechaEventoNoValidaException;

import static org.junit.Assert.assertEquals;

public class EventoTest extends EventoTestConfig {

    @Test(expected = FechaEventoNoValidaException.class)
    public void siSeIntentaCrearUnEventoConUnaFechaAnteriorALaActual_seArrojaExcepcion() {
        evento(new EventoUnico(fechaHaceDosDias));
    }

    @Test
    public void siUnEventoUnicoSeCreaConFechaDentroDeDosDias_suFechaDeberiaSerEsa() {
        Evento evento = evento(new EventoUnico(fechaEnDosDias));
        assertEquals(fechaEnDosDias, evento.getFecha());
    }

    @Test
    public void siUnEventoDiarioTieneHorarioPrevioAlActual_suFechaDeberiaSerElDiaSiguiente() {
        Evento evento = eventoDiario(horarioHaceDosHoras);
        assertEquals(fechaHaceDosHoras.plusDays(1), evento.getFecha());
    }

    @Test
    public void siUnEventoDiarioTieneHorarioPosteriorAlActual_suFechaDeberiaSerHoy() {
        Evento evento = eventoDiario(horarioEnDosHoras);
        assertEquals(fechaEnDosHoras, evento.getFecha());
    }

    @Test
    public void siYaPasoElDiaDeUnEventoMensual_suFechaDeberiaSerElMesSiguiente() {
        Evento evento = eventoMensual(fechaHaceDosDias.getDayOfMonth(), horarioAhora);
        assertEquals(fechaHaceDosDias.plusMonths(1), evento.getFecha());
    }

    @Test
    public void siNoPasoElDiaDeUnEventoMensual_suFechaDeberiaSerEsteMes() {
        Evento evento = eventoMensual(fechaEnDosDias.getDayOfMonth(), horarioAhora);
        assertEquals(fechaEnDosDias, evento.getFecha());
    }


}