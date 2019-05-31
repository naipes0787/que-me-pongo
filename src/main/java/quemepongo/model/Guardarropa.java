package quemepongo.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class Guardarropa {
	
    private double margenError = 0.1;
    private Set<Prenda> prendas = Sets.newHashSet();

    public void agregarPrenda(Prenda prenda) {
        prendas.add(prenda);
    }

    public Set<Prenda> prendasDeCategoria(Categoria categoria) {
        return prendas.stream().
                filter(prenda -> prenda.getCategoria() == categoria)
                .collect(Collectors.toSet());
    }

    public Set<Atuendo> sugerencias(Temperatura temperatura) {
        return Sets.cartesianProduct(
        				CombinadorSuperposicion.combinarMultiple(
        						this.prendasDeCategoria(Categoria.PRENDA_SUPERIOR)),
						CombinadorSuperposicion.combinarSimple(
								this.prendasDeCategoria(Categoria.PRENDA_INFERIOR)),
						CombinadorSuperposicion.combinarSimple(
								this.prendasDeCategoria(Categoria.CALZADO)))
                .stream()
                .map(c -> new Atuendo(c.get(0), c.get(1), c.get(2)))
                .filter(atuendo -> atuendo.abrigaLoSuficiente(temperatura, margenError))
                .collect(Collectors.toSet());
    }

    public Set<Atuendo> sugerenciasConAccesorios(Temperatura temperatura) {
        return Sets.cartesianProduct(
					CombinadorSuperposicion.combinarMultiple(
							this.prendasDeCategoria(Categoria.PRENDA_SUPERIOR)),
					CombinadorSuperposicion.combinarSimple(
							this.prendasDeCategoria(Categoria.PRENDA_INFERIOR)),
					CombinadorSuperposicion.combinarSimple(
							this.prendasDeCategoria(Categoria.CALZADO)),
					CombinadorSuperposicion.combinarSimple(
							this.prendasDeCategoria(Categoria.ACCESORIO)))
                .stream()
                .map(c -> new Atuendo(c.get(0), c.get(1), c.get(2)).conAccesorio(c.get(3))
                	)
                .filter(atuendo -> atuendo.abrigaLoSuficiente(temperatura, margenError))
                .collect(Collectors.toSet());
    }

    public int cantidadDePrendas() {
    	return prendas.size();
    }

}