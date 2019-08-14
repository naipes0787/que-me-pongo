package quemepongo.model.prenda;

import com.google.common.collect.Sets;
import quemepongo.model.FactorClimatico;

import java.util.Set;

/*
Se modela con un Abstract Factory donde el TipoPrenda será instanciado a partir de diseniarTipo llamando a
un Fabricador de Tipo (TipoSuperior, TipoInferior, TipoCalzado, TipoAccesorio), el cual instanciará con la
Categoria y listado de materiales validos que corresponde.
 */

public class TipoPrenda {
    public Categoria categoria;
    public Set<Material> materialesValidos;
    public double nivelAbrigo;
    public Capa capa;
    private Set<FactorClimatico> aptoPara;

    public static TipoPrenda diseniarTipo(FabricaTipoPrenda tipo) {
        return new TipoPrenda(tipo.getCategoria(), tipo.getMaterialesValidos(), tipo.getNivelAbrigo(), tipo.getCapa(), Sets.newHashSet());
    }

    public static TipoPrenda diseniarTipo(FabricaTipoPrenda tipo, Set<FactorClimatico> aptoPara) {
        return new TipoPrenda(tipo.getCategoria(), tipo.getMaterialesValidos(), tipo.getNivelAbrigo(), tipo.getCapa(), aptoPara);
    }

    private TipoPrenda(Categoria categoria, Set<Material> materialesValidos, double nivelAbrigo, Capa capa, Set<FactorClimatico> aptoPara) {
        this.categoria = categoria;
        this.materialesValidos = materialesValidos;
        this.nivelAbrigo = nivelAbrigo;
        this.capa = capa;
        this.aptoPara = aptoPara;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public double getNivelAbrigo() {
    	return this.nivelAbrigo;
    }
    
    public Set<Material> getMaterialesValidos(){
    	return this.materialesValidos;
    }

    public Capa getCapa() {
    	return this.capa;
    }

    public boolean esAptoPara(FactorClimatico factorClimatico) {
        return aptoPara.contains(factorClimatico);
    }
}
