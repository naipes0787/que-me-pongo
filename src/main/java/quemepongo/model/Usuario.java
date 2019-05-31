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
