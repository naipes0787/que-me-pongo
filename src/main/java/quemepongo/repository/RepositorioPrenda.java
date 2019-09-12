package quemepongo.repository;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import quemepongo.model.prenda.Prenda;

import javax.transaction.Transactional;

/**
 * Repositorio de las {@link Prenda}s del sistema
 */
public class RepositorioPrenda implements WithGlobalEntityManager {

	public void guardarPrenda(Prenda prenda){
		entityManager().getTransaction().begin();
		entityManager().persist(prenda);
		entityManager().getTransaction().commit();
	}

	public void eliminarPrenda(Prenda prenda){
		entityManager().getTransaction().begin();
		entityManager().remove(prenda);
		entityManager().getTransaction().commit();
	}

	public Prenda obtenerPrenda(Long prendaId){
		return entityManager()
				.createQuery("from Prenda where id = :idPrenda", Prenda.class)
				.setParameter("idPrenda", prendaId)
				.getSingleResult();
	}
	
}
