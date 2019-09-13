package quemepongo.persistencia;

import quemepongo.model.prenda.Prenda;

/**
 * Repositorio de las {@link Prenda}s del sistema
 */
public class RepositorioPrenda extends Repositorio<Prenda> {

	private static RepositorioPrenda instancia;

	private RepositorioPrenda() {

	}

	public static RepositorioPrenda instancia() {
		if (instancia == null) {
			instancia = new RepositorioPrenda();
		}
		return instancia;
	}

	public Prenda obtenerPrenda(Long prendaId){
		return entityManager().find(Prenda.class, prendaId);
	}

}
