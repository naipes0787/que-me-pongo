package quemepongo.persistencia;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.EventoUnico;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Arrays;
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
//        TypedQuery<Evento> query = createQuery("from Evento", Evento.class);
//        return query.getResultList();
        Evento e1= new Evento("unEvento", Localizacion.CABA, new EventoUnico(LocalDateTime.now()), Anticipacion.UNA_HORA_ANTES);
        Evento e2 = new Evento("otroEvento", Localizacion.CABA, new EventoUnico(LocalDateTime.now()), Anticipacion.UNA_HORA_ANTES);
        return Arrays.asList(e1,e2);
    }

    public List<Evento> getEventosByUsuarioId(Long usuarioId) {
        TypedQuery<Evento> query = createQuery("from Usuario u inner join u.eventos where u.id = :user", Evento.class)
                .setParameter("user", usuarioId);
        return query.getResultList();
    }
}
