package quemepongo.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

/**
 * Clase que se encarga de combinar prendas que pueden tener una múltiple
 * superposición (Es decir, una prenda podría ir sola o acompañada con otras
 * de otras capas)
 */
public class CombinadorSuperposicionMultiple implements CombinadorSuperposicion {
	
    private Guardarropa guardarropa;
    private Categoria categoria;

    public CombinadorSuperposicionMultiple(Guardarropa guardarropa, Categoria categoria) {
        this.guardarropa = guardarropa;
        this.categoria = categoria;
    }

    private Set<Prenda> prendas() {
        return guardarropa.prendasDeCategoria(categoria);
    }

    public Set<CombinacionPrenda> combinar() {
        Set<CombinacionPrenda> combinaciones = Sets.newHashSet();
        combinaciones.addAll(
                Sets.cartesianProduct(
                        prendasPorCapa(Capa.BASE))
                        .stream()
                        .map(c -> new CombinacionPrenda(Sets.newHashSet(c.get(0))))
                        .collect(Collectors.toSet())
        );

        combinaciones.addAll(
                Sets.cartesianProduct(
                        prendasPorCapa(Capa.BASE),
                        prendasPorCapa(Capa.INTERMEDIO))
                        .stream()
                        .map(c -> new CombinacionPrenda(Sets.newHashSet(c.get(0), c.get(1))))
                        .collect(Collectors.toSet())
        );
        combinaciones.addAll(
                Sets.cartesianProduct(
                        prendasPorCapa(Capa.BASE),
                        prendasPorCapa(Capa.INTERMEDIO),
                        prendasPorCapa(Capa.ULTIMA_PRENDA))
                        .stream()
                        .map(c -> new CombinacionPrenda(Sets.newHashSet(c.get(0), c.get(1), c.get(2))))
                        .collect(Collectors.toSet())
        );
        return combinaciones;
    }

    private Set<Prenda> prendasPorCapa(Capa capa) {
        return prendas()
                .stream()
                .filter(prenda -> prenda.getCapa() == capa)
                .collect(Collectors.toSet());
    }
}