package quemepongo.persistencia;

import quemepongo.dominio.evento.Evento;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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

    public List<Evento> getEventosByUsuarioId(Long usuario) {
        return createQuery("select e from Usuario u inner join u.eventos e where u.id = :user")
                .setParameter("user", usuario).getResultList();
    }

    public Optional<Evento> buscarEvento(Long id) {
        return Optional.ofNullable(find(Evento.class, id));
    }
}
