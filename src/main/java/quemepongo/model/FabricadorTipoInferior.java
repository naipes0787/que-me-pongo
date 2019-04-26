package quemepongo.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricadorTipoInferior implements FabricaTipoPrenda {

    public Categoria getCategoria() {
        return Categoria.PRENDA_INFERIOR;
    }

    public Set<Material> materialesValidos() {
        return Stream.of(
                Material.ALGODON,
                Material.CUERO,
                Material.LINO,
                Material.GABARDINA,
                Material.SEDA,
                Material.OXFORD)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
