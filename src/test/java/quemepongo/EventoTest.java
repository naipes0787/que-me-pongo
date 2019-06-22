package quemepongo;

import org.junit.Test;
import quemepongo.exceptions.FechaEventoNoValidaException;
import quemepongo.model.Evento;
import quemepongo.model.Localizacion;

import java.time.LocalDateTime;

public class EventoTest {
	
	@Test(expected = FechaEventoNoValidaException.class)
	public void arrojarExcepcionSiSeIntentaCrearUnEventoConUnaFechaAnteriorALaActual(){
		new Evento(Localizacion.CABA, LocalDateTime.now().minusDays(1));
	}
}