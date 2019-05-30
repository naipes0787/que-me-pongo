package quemepongo.model;

import quemepongo.exceptions.CapaInvalidaException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricadorTipoSuperiorBase extends FabricaTipoPrenda {

    public FabricadorTipoSuperiorBase(double nivelAbrigo){
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
                Material.OXFORD
        )
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Capa getCapa() { return Capa.BASE;}

}


