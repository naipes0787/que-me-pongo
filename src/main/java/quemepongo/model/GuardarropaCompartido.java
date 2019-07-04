package quemepongo.model;

import quemepongo.model.sugerencia.Atuendo;
import quemepongo.model.usuario.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

public class GuardarropaCompartido extends Guardarropa {

    private Set<Usuario> usuarios;

    @Override
    public Set<Atuendo> sugerencias(Temperatura temperatura) {
        Set<Atuendo> atuendos = super.sugerencias(temperatura);
        return atuendos.stream().filter(a -> usuarios.stream().noneMatch(u -> u.aceptoAlgunaPrendaDe(a))).collect(Collectors.toSet());
    }
}
