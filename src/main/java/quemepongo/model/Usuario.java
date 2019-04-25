package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

public class Usuario {

    private Set<Guardarropa> guardarropas = Sets.newHashSet();

    public void agregarGuardarropa(Guardarropa guardarropa) {
        guardarropas.add(guardarropa);
    }

    public Set<Atuendo> sugerencias() {
        return guardarropas.stream().flatMap(g -> g.sugerencias().stream()).collect(Collectors.toSet());
    }
}
