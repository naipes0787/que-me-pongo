package quemepongo.dominio.prenda;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import quemepongo.dominio.Entidad;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class CombinacionPrenda extends Entidad {

    @OneToMany
    @Cascade({CascadeType.REFRESH})
    private Set<Prenda> prendas;

    public CombinacionPrenda(){}

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