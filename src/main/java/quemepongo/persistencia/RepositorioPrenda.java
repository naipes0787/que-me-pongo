package quemepongo.persistencia;

import quemepongo.model.prenda.Prenda;

/**
 * Repositorio de las {@link Prenda}s del sistema
 */
public class RepositorioPrenda extends Repositorio<Prenda> {

	public Prenda obtenerPrenda(Long prendaId){
		return entityManager().find(Prenda.class, prendaId);
	}
}
