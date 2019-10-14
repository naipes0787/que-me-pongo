package quemepongo.dominio.prenda;

import java.util.Set;

public abstract class FabricaTipoPrenda {

    public double nivelAbrigo;

    public abstract Categoria getCategoria();
    public abstract Set<Material> getMaterialesValidos();
    public abstract Capa getCapa();
    public double getNivelAbrigo(){
    	return this.nivelAbrigo;
    }

}
