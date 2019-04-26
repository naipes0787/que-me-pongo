package quemepongo.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TipoSuperior implements FabricadorTiposPrenda {
    public Categoria getCategoria() {
        return Categoria.PRENDA_SUPERIOR;
    }

    public Set<Material> materialesValidos() {
        return Stream.of(
                Material.ALGODON,
                Material.PIQUE,
                Material.LINO,
                Material.GABARDINA,
                Material.SEDA,
                Material.OXFORD
        )
                .collect(Collectors.toCollection(HashSet::new));
    }
}


