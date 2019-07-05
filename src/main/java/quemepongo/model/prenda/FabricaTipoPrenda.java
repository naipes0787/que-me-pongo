package quemepongo.model.prenda;

import quemepongo.model.FactorClimatico;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class FabricaTipoPrenda {

    public double nivelAbrigo;

    public abstract Categoria getCategoria();
    public abstract Set<Material> getMaterialesValidos();
    public abstract Capa getCapa();
    public Set<FactorClimatico> aptoPara() {
        return new HashSet<>(Arrays.asList(FactorClimatico.values()));
    }

    public double getNivelAbrigo(){
    	return this.nivelAbrigo;
    }

}
