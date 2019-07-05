package quemepongo.ui.arena.evento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.collections15.Transformer;

/**
 * En el caso de existir un atuendo, devuelve sí. Caso contrario, devuelve no
 * 
 */
public final class FechaToImprimibleTransformer implements Transformer<LocalDateTime, String> {
	@Override
	public String transform(LocalDateTime fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return (fecha.format(formatter));
	}
}