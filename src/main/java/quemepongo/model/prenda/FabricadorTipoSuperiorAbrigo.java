package quemepongo.model.prenda;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricadorTipoSuperiorAbrigo extends FabricaTipoPrenda{

    public FabricadorTipoSuperiorAbrigo(double nivelAbrigo){
        super.nivelAbrigo = nivelAbrigo;
    }

    public Categoria getCategoria() {
        return Categoria.PRENDA_SUPERIOR;
    }

    public Set<Material> getMaterialesValidos() {
        return Stream.of(
                Material.ALGODON,
                Material.PIQUE,
                Material.LINO,
                Material.GABARDINA,
                Material.SEDA,
                Material.OXFORD,
                Material.PLASTICO
        )
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Capa getCapa() {
    	return Capa.ABRIGO;
    }
}
