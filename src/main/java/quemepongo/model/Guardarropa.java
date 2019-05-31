package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Guardarropa {
    private double margenError = 0.1;
    private Set<Prenda> prendas = Sets.newHashSet();

    private Combinador combinadorSuperior = new CombinadorMultiple(this, Categoria.PRENDA_SUPERIOR);
    private Combinador combinadorInferior = new CombinadorSimple(this, Categoria.PRENDA_INFERIOR);
    private Combinador combinadorCalzado = new CombinadorSimple(this, Categoria.CALZADO);

    public void agregarPrenda(Prenda prenda) {
        prendas.add(prenda);
    }

    public Set<Prenda> prendasDeCategoria(Categoria categoria) {
        return prendas.stream().
                filter(prenda -> prenda.getCategoria() == categoria)
                .collect(Collectors.toSet());
    }

    public Set<Atuendo> generarSugerencias(/*Temperatura Temp*/) {
        return Sets.cartesianProduct(
                        combinacionSuperior(),
                        combinacionInferior(),
                        combinacionCalzado())
                .stream()
                .map(c -> new Atuendo(c.get(0), c.get(1), c.get(2)))
                .filter(atuendo -> atuendo.abrigaLoSuficiente(10, margenError))
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


}