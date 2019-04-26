package quemepongo.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TipoAccesorio implements FabricadorTiposPrenda {
    public Categoria getCategoria() {
        return Categoria.ACCESORIO;
    }

    public Set<Material> materialesValidos() {
        return Stream.of(
                Material.ORO,
                Material.PLATA,
                Material.BRONCE,
                Material.PLASTICO)
                .collect(Collectors.toCollection(HashSet::new));
    }
}

