
package quemepongo;

import quemepongo.model.evento.Evento;
import quemepongo.model.evento.Localizacion;
import quemepongo.model.evento.tipo.Anticipacion;
import quemepongo.model.evento.tipo.EventoUnico;
import quemepongo.ui.arena.evento.ListarEventosWindow;

import java.time.LocalDateTime;
import java.time.Month;

public class QueMePongoApplication {

	public static void main(String[] args) {
		new Evento("Casamiento de Roberto Carlos", Localizacion.CABA,
				new EventoUnico(LocalDateTime.of(2020, Month.JANUARY, 15, 0, 0))
				, Anticipacion.UNA_HORA_ANTES);
		new Evento("Cumpleaños de Martita", Localizacion.CABA,
				new EventoUnico(LocalDateTime.of(2020, Month.JULY, 1, 0, 0))
				, Anticipacion.UNA_HORA_ANTES);
		new Evento("Hackaton UTN", Localizacion.CABA,
				new EventoUnico(LocalDateTime.of(2020, Month.NOVEMBER, 1, 0, 0))
				, Anticipacion.UNA_HORA_ANTES);
		new ListarEventosWindow().startApplication();
	}

}