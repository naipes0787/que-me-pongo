package quemepongo.model.prenda;

import com.google.common.collect.Sets;
import quemepongo.model.Entidad;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class CombinacionPrenda extends Entidad {

    public CombinacionPrenda(){//Para Habernite
         }

    public CombinacionPrenda(Set<Prenda> prendas){
        this.prendas = prendas;
    }

    @ManyToMany
    private Set<Prenda> prendas = Sets.newHashSet();

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