package quemepongo.model.guardarropa;

import quemepongo.model.Temperatura;
import quemepongo.model.sugerencia.Atuendo;
import quemepongo.model.usuario.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

public class GuardarropaCompartido extends Guardarropa {

    private Set<Usuario> usuarios;

    public GuardarropaCompartido(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public Set<Atuendo> sugerencias(Temperatura temperatura) {
        Set<Atuendo> atuendos = super.sugerencias(temperatura);
        return atuendos.stream().filter(a -> usuarios.stream().noneMatch(u -> u.aceptoAlgunaPrendaDe(a))).collect(Collectors.toSet());
    }
}
