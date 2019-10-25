package quemepongo.persistencia;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.EventoUnico;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Evento> getEventosByUsuarioId(Long usuario) {
        return createQuery("select e from Usuario u inner join u.eventos e where u.id = :user")
                .setParameter("user", usuario).getResultList();
    }
}
