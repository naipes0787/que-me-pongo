package quemepongo.model;

import java.util.Set;

public interface FabricaTipoPrenda {
    public Categoria getCategoria();
    public Set<Material> materialesValidos();
}
