package quemepongo.persistencia;

import quemepongo.dominio.evento.Evento;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public List<Evento> getEventosByUsuarioId(Long usuarioId) {
        TypedQuery<Evento> query = createQuery("from Evento e WHERE e.usuarioId = :username", Evento.class)
                .setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }
}
