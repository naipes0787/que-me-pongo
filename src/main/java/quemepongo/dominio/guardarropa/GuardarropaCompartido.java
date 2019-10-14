package quemepongo.dominio.guardarropa;

import quemepongo.dominio.sugerencia.Atuendo;
import quemepongo.dominio.usuario.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

public class GuardarropaCompartido extends Guardarropa {

    private Set<Usuario> usuarios;

    public GuardarropaCompartido(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
        usuarios.forEach(usuario -> usuario.agregarGuardarropa(this));
    }

    @Override
    public Set<Atuendo> sugerencias(Usuario usuario, double nivelAbrigo) {
        Set<Atuendo> atuendos = super.sugerencias(usuario, nivelAbrigo);
        return atuendos.stream().filter(a -> usuarios.stream().noneMatch(u -> u.estaUsandoAlgunaPrendaDe(a))).collect(Collectors.toSet());
    }
}
