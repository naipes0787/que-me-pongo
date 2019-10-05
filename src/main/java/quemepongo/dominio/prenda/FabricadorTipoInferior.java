package quemepongo.dominio.prenda;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricadorTipoInferior extends FabricaTipoPrenda {

    public FabricadorTipoInferior(double nivelAbrigo ){
        super.nivelAbrigo = nivelAbrigo;
    }

    public Categoria getCategoria() {
        return Categoria.PRENDA_INFERIOR;
    }

    public Set<Material> getMaterialesValidos() {
        return Stream.of(
                Material.ALGODON,
                Material.CUERO,
                Material.LINO,
                Material.GABARDINA,
                Material.SEDA,
                Material.OXFORD)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Set<Capa> getCapasValidas() {
        return Stream.of(
                Capa.BASE
        )
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Capa getCapa() {
    	return Capa.BASE;
    }

}
