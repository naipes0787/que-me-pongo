package quemepongo.ui.arena.evento;

import org.apache.commons.collections15.Transformer;

import quemepongo.model.sugerencia.Atuendo;

/**
 * En el caso de existir un atuendo, devuelve sí. Caso contrario, devuelve no
 * 
 */
public final class AtuendoToSiNoTransformer implements Transformer<Atuendo, String> {
	@Override
	public String transform(Atuendo atuendo) {
		if(atuendo != null) {
			return "Sí";
		} else {
			return "No";
		}
	}
}