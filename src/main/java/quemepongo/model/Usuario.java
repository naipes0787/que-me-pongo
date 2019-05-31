package quemepongo.model;

import com.google.common.collect.Sets;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;

import java.util.Set;
import java.util.stream.Collectors;

public class Usuario {

    private Set<Guardarropa> guardarropas = Sets.newHashSet();

    private Set<Evento> eventos = Sets.newHashSet();

    private TipoUsuario tipoUsuario;

    public Usuario() {
    	tipoUsuario = new UsuarioGratuito();
    };

    public Usuario(TipoUsuario nuevaSuscripcion) {
    	tipoUsuario = nuevaSuscripcion;
    };
    
	  public void agregarGuardarropa(Guardarropa guardarropa) {
        guardarropas.add(guardarropa);
    }

    public Set<Atuendo> sugerencias(Evento evento) {
        Temperatura temperatura = SelectorDeProveedorDeClima.getInstancia().getProovedorDeClima().obtenerTemperaturaActual(evento.getLugar());
        return guardarropas.stream().flatMap(g -> g.sugerencias(temperatura).stream()).collect(Collectors.toSet());
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }
    
    public TipoUsuario getTipoUsuario() {
		    return tipoUsuario;
	  }
    
	  public void cambiarSuscripcion(TipoUsuario tipoUsuario) {
		    this.tipoUsuario = tipoUsuario;
	  }
	
    public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
    	  tipoUsuario.agregarPrenda(prenda, guardarropa);
    }

    public void aceptarSugerencia(Atuendo atuendo) {
        atuendo.aceptar();
    }

    public void rechazarSugerencia(Atuendo atuendo) {
        atuendo.rechazar();
    }

    public void deshacerUltimaOperacion(Atuendo atuendo) {
        atuendo.deshacerDecision();
    }
}

