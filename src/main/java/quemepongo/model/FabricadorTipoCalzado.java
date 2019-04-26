package quemepongo.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricadorTipoCalzado implements FabricaTipoPrenda {
	
    public Categoria getCategoria() {
        return Categoria.CALZADO;
    }

    public Set<Material> getMaterialesValidos() {
        return Stream.of(
                Material.CUERO,
                Material.LONA,
                Material.POLIESTER)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
