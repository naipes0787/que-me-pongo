package quemepongo.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricadorTipoAccesorio implements FabricaTipoPrenda {
	
    public Categoria getCategoria() {
        return Categoria.ACCESORIO;
    }

    public Set<Material> getMaterialesValidos() {
        return Stream.of(
                Material.ORO,
                Material.PLATA,
                Material.BRONCE,
                Material.PLASTICO)
                .collect(Collectors.toCollection(HashSet::new));
    }
}

