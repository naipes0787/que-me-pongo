package quemepongo.persistencia;

import quemepongo.dominio.evento.Evento;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

/**
 * Repositorio de las {@link Evento}s del sistema
 */
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
        TypedQuery<Evento> query = createQuery("from Evento", Evento.class);
        return query.getResultList();
    }
}
