package quemepongo;
import java.time.LocalDateTime;

import org.junit.Test;

import quemepongo.exceptions.FechaEventoNoValidaException;
import quemepongo.model.Evento;
import quemepongo.model.Localizacion;

public class EventoTest {
	
	@Test(expected = FechaEventoNoValidaException.class)
	public void arrojarExcepcionSiSeIntentaCrearUnEventoConUnaFechaAnteriorALaActual(){
		new Evento(Localizacion.CABA, LocalDateTime.now().minusDays(1));
	}
}