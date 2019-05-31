package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

public class CombinadorSimple implements Combinador {
    private Guardarropa guardarropa;
    private Categoria categoria;

    public CombinadorSimple(Guardarropa guardarropa, Categoria categoria){
        this.guardarropa = guardarropa;
        this.categoria = categoria;
    }

    private Set<Prenda> prendas() {
        return guardarropa.prendasDeCategoria(categoria);
    }

    public Set<CombinacionPrenda> combinar() {
      return prendas()
                .stream()
                .map(prenda -> generarCombinacion(prenda))
                .collect(Collectors.toSet());
    }

    private CombinacionPrenda generarCombinacion(Prenda prenda){
        return new CombinacionPrenda(Sets.newHashSet(prenda));
    }

}

