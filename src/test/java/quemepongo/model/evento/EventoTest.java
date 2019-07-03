package quemepongo.model.evento;

import org.junit.Test;
import quemepongo.exceptions.FechaEventoNoValidaException;
import quemepongo.model.evento.Evento;
import quemepongo.model.evento.Localizacion;

import java.time.LocalDateTime;

public class EventoTest {
	
	@Test(expected = FechaEventoNoValidaException.class)
	public void arrojarExcepcionSiSeIntentaCrearUnEventoConUnaFechaAnteriorALaActual(){
		new Evento(Localizacion.CABA, LocalDateTime.now().minusDays(1));
	}
}