package quemepongo.ui.arena.evento;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class FechaToImprimibleTransformerTest {

    @Test
    public void siElTransformerRecibeUnaFecha_DevuelveFechaBienFormateada(){
        FechaToImprimibleTransformer transformer = new FechaToImprimibleTransformer();
        LocalDateTime fechaEvento = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 30);
        assertEquals("01-01-2014", transformer.transform(fechaEvento));
    }

}