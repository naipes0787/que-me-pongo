package quemepongo.model.prenda;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Clase que se encarga de combinar prendas que pueden tener superposiciones
 */
public class Combinador {
	
    public static Set<CombinacionPrenda> combinarSimple(Set<Prenda> prendas) {
    	return prendas
                .stream()
                .map(prenda -> generarCombinacion(prenda))
                .collect(Collectors.toSet());
    }
    
    public static Set<CombinacionPrenda> combinarMultiple(Set<Prenda> prendas) {
        Set<CombinacionPrenda> combinaciones = Sets.newHashSet();
        combinaciones.addAll(
                Sets.cartesianProduct(
                        prendasPorCapa(Capa.BASE, prendas))
                        .stream()
                        .map(c -> new CombinacionPrenda(Sets.newHashSet(c.get(0))))
                        .collect(Collectors.toSet())
        );

        combinaciones.addAll(
                Sets.cartesianProduct(
                        prendasPorCapa(Capa.BASE, prendas),
                        prendasPorCapa(Capa.INTERMEDIO, prendas))
                        .stream()
                        .map(c -> new CombinacionPrenda(Sets.newHashSet(c.get(0), c.get(1))))
                        .collect(Collectors.toSet())
        );
        combinaciones.addAll(
                Sets.cartesianProduct(
                        prendasPorCapa(Capa.BASE, prendas),
                        prendasPorCapa(Capa.INTERMEDIO, prendas),
                        prendasPorCapa(Capa.ULTIMA_PRENDA, prendas))
                        .stream()
                        .map(c -> new CombinacionPrenda(Sets.newHashSet(c.get(0), c.get(1), c.get(2))))
                        .collect(Collectors.toSet())
        );
        return combinaciones;
    }

    public static CombinacionPrenda generarCombinacion(Prenda prenda){
        return new CombinacionPrenda(Sets.newHashSet(prenda));
    }
    
    private static Set<Prenda> prendasPorCapa(Capa capa, Set<Prenda> prendas) {
        return prendas
                .stream()
                .filter(prenda -> prenda.getCapa() == capa)
                .collect(Collectors.toSet());
    }

}

