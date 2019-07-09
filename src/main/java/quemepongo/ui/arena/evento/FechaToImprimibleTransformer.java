package quemepongo.ui.arena.evento;

import java.time.format.DateTimeFormatter;

import org.apache.commons.collections15.Transformer;

import quemepongo.model.evento.Ocurrencia;

/**
 * Formatea la fecha en un formato más legible
 * 
 */
public final class FechaToImprimibleTransformer implements Transformer<Ocurrencia, String> {
	@Override
	public String transform(Ocurrencia ocurrencia) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return (ocurrencia.fechaDelEvento().format(formatter));
	}
}