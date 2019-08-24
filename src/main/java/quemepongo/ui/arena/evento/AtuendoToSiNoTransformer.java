package quemepongo.ui.arena.evento;

import org.apache.commons.collections15.Transformer;

import quemepongo.model.sugerencia.Atuendo;
import quemepongo.util.Mensajes;

/**
 * En el caso de existir un atuendo, devuelve sí. Caso contrario, devuelve no
 * 
 */
public final class AtuendoToSiNoTransformer implements Transformer<Atuendo, String> {
	@Override
	public String transform(Atuendo atuendo) {
		if(atuendo != null) {
			return Mensajes.SI;
		} else {
			return Mensajes.NO;
		}
	}
}