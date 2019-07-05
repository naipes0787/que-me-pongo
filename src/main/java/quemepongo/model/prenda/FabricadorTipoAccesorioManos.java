package quemepongo.model.prenda;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricadorTipoAccesorioManos  extends FabricaTipoPrenda {

    public FabricadorTipoAccesorioManos(){
        super.nivelAbrigo = 0;
    }

    public Categoria getCategoria() {
        return Categoria.ACCESORIO_MANOS;
    }

    public Set<Material> getMaterialesValidos() {
        return Stream.of(
                Material.LANA)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Capa getCapa() {
        return Capa.BASE;
    }

}
