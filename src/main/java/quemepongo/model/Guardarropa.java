package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Guardarropa {
    private Set<Prenda> prendasSuperiores = Sets.newHashSet();
    private Set<Prenda> prendasInferiores = Sets.newHashSet();
    private Set<Prenda> calzados = Sets.newHashSet();
    private Set<Prenda> accesorios = Sets.newHashSet();

    public void agregarPrendaSuperior(Prenda prenda) {
        prendasSuperiores.add(prenda);
    }

    public void agregarPrendaInferior(Prenda prenda) {
        prendasInferiores.add(prenda);
    }

    public void agregarCalzado(Prenda prenda) {
        calzados.add(prenda);
    }

    public void agregarAccesorio(Prenda prenda) {
        accesorios.add(prenda);
    }

    public Set<Atuendo> sugerencias() {
        Set<List<Prenda>> combinaciones = Sets.cartesianProduct(prendasSuperiores, prendasInferiores, calzados);
        return combinaciones.stream().map(c -> new Atuendo(c.get(0), c.get(1), c.get(2))).collect(Collectors.toSet());
    }

    public Set<Atuendo> sugerenciasConAccesorios() {
        Set<List<Prenda>> combinaciones = Sets.cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios);
        return combinaciones.stream().map(c -> new Atuendo(c.get(0), c.get(1), c.get(2)).conAccesorio(c.get(3))).collect(Collectors.toSet());
    }
    
    public int cantidadDePrendas() {
    	return prendasSuperiores.size() + prendasInferiores.size() + calzados.size() + accesorios.size();
    }
    
    public void agregarPrenda(Prenda prenda) {
    	// TODO - consultar si se va seguir manteniendo los 4 Sets o si se unen en uno.
    	
    	switch(prenda.getCategoria()) {
    		case PRENDA_SUPERIOR:
    			agregarPrendaSuperior(prenda);
    			break;
    		case PRENDA_INFERIOR:
    			agregarPrendaInferior(prenda);
    			break;
    		case CALZADO:
    			agregarCalzado(prenda);
    			break;
    		case ACCESORIO:
    			agregarCalzado(prenda);
    			break;
    	}
    }
}