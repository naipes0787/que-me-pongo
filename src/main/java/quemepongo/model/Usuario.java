package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

public class Usuario {

    private Set<Guardarropa> guardarropas = Sets.newHashSet();
    private TipoUsuario tipoUsuario;
    
    public Usuario() {
    	tipoUsuario = new UsuarioFree();
    };

    public Usuario(TipoUsuario nuevaSuscripcion) {
    	tipoUsuario = nuevaSuscripcion;
    };
    
	public void agregarGuardarropa(Guardarropa guardarropa) {
        guardarropas.add(guardarropa);
    }

    public Set<Atuendo> sugerencias() {
        return guardarropas.stream().flatMap(g -> g.sugerencias().stream()).collect(Collectors.toSet());
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
}
