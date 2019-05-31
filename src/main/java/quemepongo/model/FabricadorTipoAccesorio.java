package quemepongo.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricadorTipoAccesorio extends FabricaTipoPrenda {

    public FabricadorTipoAccesorio(){
        super.nivelAbrigo = 0;
    }

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

    public Capa getCapa() { return Capa.BASE;}

}

