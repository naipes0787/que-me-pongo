package quemepongo.model.prenda;

import com.google.common.collect.Sets;

import java.util.Set;

public class CombinacionPrenda {
    private Set<Prenda> prendas = Sets.newHashSet();

    public CombinacionPrenda(Set<Prenda> prendas){
        this.prendas = prendas;
    }

    public double getNivelAbrigo() {
        return prendas.stream().mapToDouble(prenda -> prenda.getNivelAbrigo()).sum();
    }

    public int getCantPrendas(){
    	return prendas.size();
    }

    public Set<Prenda> getPrendas() {
        return prendas;
    }

    public void agregarPrenda(Prenda prenda){
        prendas.add(prenda);
    }
}