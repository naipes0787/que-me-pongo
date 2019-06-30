package quemepongo.model.prenda;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricadorTipoCalzado extends FabricaTipoPrenda {

    public FabricadorTipoCalzado(double nivelAbrigo){
        super.nivelAbrigo = nivelAbrigo;
    }
    
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
