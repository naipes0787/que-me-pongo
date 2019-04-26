package quemepongo.model;

import java.util.Set;

public interface FabricadorTiposPrenda {
    public Categoria getCategoria();
    public Set<Material> materialesValidos();
}
