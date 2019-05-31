package quemepongo.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class Guardarropa {
    private double margenError = 0.1;
    private Set<Prenda> prendas = Sets.newHashSet();

    private Combinador combinadorSuperior = new CombinadorMultiple(this, Categoria.PRENDA_SUPERIOR);
    private Combinador combinadorInferior = new CombinadorSimple(this, Categoria.PRENDA_INFERIOR);
    private Combinador combinadorCalzado = new CombinadorSimple(this, Categoria.CALZADO);
    private Combinador combinadorAccesorio = new CombinadorSimple(this, Categoria.ACCESORIO);

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
                        combinacionSuperior(),
                        combinacionInferior(),
                        combinacionCalzado())
                .stream()
                .map(c -> new Atuendo(c.get(0), c.get(1), c.get(2)))
                .filter(atuendo -> atuendo.abrigaLoSuficiente(temperatura, margenError))
                .collect(Collectors.toSet());
    }

    public Set<Atuendo> sugerenciasConAccesorios(Temperatura temperatura) {
        return Sets.cartesianProduct(
                        combinacionSuperior(),
                        combinacionInferior(),
                        combinacionCalzado(),
                        combinadorAccesorio())
                .stream()
                .map(c -> new Atuendo(c.get(0), c.get(1), c.get(2)).conAccesorio(c.get(3))
                	)
                .filter(atuendo -> atuendo.abrigaLoSuficiente(temperatura, margenError))
                .collect(Collectors.toSet());
    }


    private Set<CombinacionPrenda> combinacionSuperior() {
        return combinadorSuperior.combinar();
    }

    private Set<CombinacionPrenda> combinacionInferior() {
        return combinadorInferior.combinar();
    }

    private Set<CombinacionPrenda> combinacionCalzado() {
        return combinadorCalzado.combinar();
    }
    
    private Set<CombinacionPrenda> combinadorAccesorio() {
        return combinadorAccesorio.combinar();
    }

    public int cantidadDePrendas() {
    	return prendas.size();
    }

}