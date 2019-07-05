package quemepongo.model.evento;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Clase (Singleton) que contiene a todos los eventos del sistema
 */
public class RepositorioEvento {

	private static RepositorioEvento repositorioEventos;
	private Set<Evento> eventos;
	
	private RepositorioEvento() {
		eventos = Sets.newHashSet();
	}
	
    public static RepositorioEvento getInstancia() {
        if (repositorioEventos == null) {
        	repositorioEventos = new RepositorioEvento();
        }
        return repositorioEventos;
    }

	public void agregarEvento(Evento evento) {
		eventos.add(evento);
	}
	
	public Set<Evento> getEventos(){
		return this.eventos;
	}
	
}
