
package quemepongo;

import java.time.LocalDateTime;

import quemepongo.model.evento.Evento;
import quemepongo.model.evento.Localizacion;
import quemepongo.ui.arena.ListarEventosWindow;

public class QueMePongoApplication {

	public static void main(String[] args) {
		// TODO: Estos son eventos de prueba, se eliminarían al terminar la parte visual
		new Evento(Localizacion.CABA, LocalDateTime.now().plusDays(10), "Evento random");
		new Evento(Localizacion.CABA, LocalDateTime.now().plusDays(30), "Evento random 2");
		new Evento(Localizacion.CABA, LocalDateTime.now().plusDays(90), "Evento random 3");
		new ListarEventosWindow().startApplication();
	}
	
}