package quemepongo.model;

import java.util.*;

/*
Se modela con un Abstract Factory donde el TipoPrenda será instanciado a partir de diseniarTipo llamando a
un Fabricador de Tipo (TipoSuperior, TipoInferior, TipoCalzado, TipoAccesorio), el cual instanciará con la
Categoria y listado de materiales validos que corresponde.
 */

public class TipoPrenda {
    public Categoria categoria;
    public Set<Material> materialesValidos = new HashSet<>();

    public static TipoPrenda diseniarTipo(FabricaTipoPrenda tipo) {
        return new TipoPrenda(tipo.getCategoria(), tipo.materialesValidos());
    }

    private TipoPrenda(Categoria categoria, Set<Material> materialesValidos) {
        this.categoria = categoria;
        this.materialesValidos = materialesValidos;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public Set<Material> getMaterialesValidos(){
    	return this.materialesValidos;
    }
}
