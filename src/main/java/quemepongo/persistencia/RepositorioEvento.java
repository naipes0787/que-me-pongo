package quemepongo.persistencia;

import quemepongo.model.evento.Evento;

import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioEvento extends Repositorio<Evento> {

	private static RepositorioEvento instancia;

	private RepositorioEvento() {
	}
	
    public static RepositorioEvento instancia() {
        if (instancia == null) {
        	instancia = new RepositorioEvento();
        }
        return instancia;
    }

    public List<Evento> getEventos() {
        TypedQuery<Evento> query = entityManager().createQuery("from Evento", Evento.class);
        return query.getResultList();
    }
}