package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

public class Guardarropa {
    private Set<Prenda> prendas = Sets.newHashSet();

    public void agregarPrenda(Prenda prenda) {
        prendas.add(prenda);
    }

    public Set<Atuendo> sugerencias() {
        int categoriasDistintas = Math.toIntExact(prendas.stream().map(Prenda::getCategoria).distinct().count());
        Set<Set<Prenda>> combinaciones = Sets.combinations(prendas, categoriasDistintas);
        return combinaciones.stream().map(Atuendo::new).filter(Atuendo::valido).collect(Collectors.toSet());
    }
}
