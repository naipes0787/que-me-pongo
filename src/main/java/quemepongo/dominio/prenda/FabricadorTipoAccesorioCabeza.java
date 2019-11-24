package quemepongo.dominio.prenda;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricadorTipoAccesorioCabeza  extends FabricaTipoPrenda {

    public FabricadorTipoAccesorioCabeza(){
        super.nivelAbrigo = 0;
    }

    public Categoria getCategoria() {
        return Categoria.ACCESORIO_CABEZA;
    }

    public Set<Material> getMaterialesValidos() {
        return Stream.of(
                Material.LANA)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Capa getCapa() {
        return Capa.BASE;
    }

    @Override
    public String getNombre() {
        return "Accesorio para cabeza";
    }

}

