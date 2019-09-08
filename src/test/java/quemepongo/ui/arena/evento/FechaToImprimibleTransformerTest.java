package quemepongo.ui.arena.evento;

import org.junit.Test;
import quemepongo.model.evento.CadaCiertoTiempo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class FechaToImprimibleTransformerTest {

    @Test
    public void siElTransformerRecibeUnaOcurrencia_DevuelveFechaBienFormateada(){
        FechaToImprimibleTransformer transformer = new FechaToImprimibleTransformer();
        LocalDateTime fechaEvento = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 30);
        assertEquals("01-01-2014", transformer.transform(fechaEvento));
    }

}