package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Guardarropa {
    private double margenError = 0.1;
    private Set<Prenda> prendas = Sets.newHashSet();

    private Set<Prenda> prendasSuperiores() {
        return prendasDeCategoria(Categoria.PRENDA_SUPERIOR);
    }

    private Set<Prenda> prendasInferiores() {
        return prendasDeCategoria(Categoria.PRENDA_INFERIOR);
    }

    private Set<Prenda> calzados() {
        return prendasDeCategoria(Categoria.CALZADO);
    }

    private Set<Prenda> accesorios() {
        return prendasDeCategoria(Categoria.ACCESORIO);
    }

    public void agregarPrenda(Prenda prenda) {
        prendas.add(prenda);
    }

    /*
        public Set<Atuendo> sugerencias() {
            Set<List<Prenda>> combinaciones = Sets.cartesianProduct(prendasSuperiores(), prendasInferiores(), calzados());
            return combinaciones.stream().map(c -> new Atuendo(c.get(0), c.get(1), c.get(2))).collect(Collectors.toSet());
        }

        public Set<Atuendo> sugerenciasConAccesorios() {
            Set<List<Prenda>> combinaciones = Sets.cartesianProduct(prendasSuperiores(), prendasInferiores(), calzados(), accesorios());
            return combinaciones.stream().map(c -> new Atuendo(c.get(0), c.get(1), c.get(2)).conAccesorio(c.get(3))).collect(Collectors.toSet());
        }
    */
    private Set<Prenda> prendasDeCategoria(Categoria categoria) {
        return prendas.stream().
                filter(prenda -> prenda.getCategoria() == categoria)
                .collect(Collectors.toSet());
    }

    private Set<Prenda> prendasSuperioresPorCapa(Capa capa) {
        return prendasSuperiores()
                .stream()
                .filter(prenda -> prenda.getCapa() == capa)
                .collect(Collectors.toSet());
    }

    public Set<Atuendo> generarSugerencias(/*Temperatura Temp*/) {
        Set<List<CombinacionPrenda>> combinaciones = Sets.cartesianProduct(
                combinacionSuperior(100 * 0.5),
                combinacionInferior(100 * 0.35),
                combinacionCalzado(100 * 0.15)
        );
        return combinaciones.stream().map(c -> new Atuendo(c.get(0), c.get(1), c.get(2))).collect(Collectors.toSet());
    }

    private Set<CombinacionPrenda> combinacionSuperior(double nivelAbrigoNecesario) {
        Set<CombinacionPrenda> combinacionesValidas = Sets.newHashSet();

        Set<List<Prenda>> combinaSimple = Sets.cartesianProduct(
                prendasSuperioresPorCapa(Capa.BASE));
        combinaSimple.stream()
                .forEach(c -> {
                    CombinacionPrenda combinacion = new CombinacionPrenda(Sets.newHashSet(c.get(0)));
                    if (combinacion.abrigaLoSuficiente(nivelAbrigoNecesario, margenError)) {
                        combinacionesValidas.add(combinacion);
                    }
                });

        Set<List<Prenda>> combinaDoble = Sets.cartesianProduct(
                prendasSuperioresPorCapa(Capa.BASE),
                prendasSuperioresPorCapa(Capa.INTERMEDIO));
        combinaDoble.stream()
                .forEach(c -> {
                    CombinacionPrenda combinacion = new CombinacionPrenda(Sets.newHashSet(c.get(0), c.get(1)));
                    if (combinacion.abrigaLoSuficiente(nivelAbrigoNecesario, margenError)) {
                        combinacionesValidas.add(combinacion);
                    }
                });
        Set<List<Prenda>> combinaTriple = Sets.cartesianProduct(
                prendasSuperioresPorCapa(Capa.BASE),
                prendasSuperioresPorCapa(Capa.INTERMEDIO),
                prendasSuperioresPorCapa(Capa.ULTIMA_PRENDA));
        combinaTriple.stream()
                .forEach(c -> {
                    CombinacionPrenda combinacion = new CombinacionPrenda(Sets.newHashSet(c.get(0), c.get(1), c.get(2)));
                    if (combinacion.abrigaLoSuficiente(nivelAbrigoNecesario, margenError)) {
                        combinacionesValidas.add(combinacion);
                    }
                });
        return combinacionesValidas;
    }

    private Set<CombinacionPrenda> combinacionSimple(double nivelAbrigoNecesario, Set<Prenda> prendas ) {
        Set<CombinacionPrenda> combinacionesValidas = Sets.newHashSet();
        prendas.stream()
               .forEach(prenda -> {
                    CombinacionPrenda combinacion = new CombinacionPrenda(Sets.newHashSet(prenda));
                    if (combinacion.abrigaLoSuficiente(nivelAbrigoNecesario, margenError)) {
                        combinacionesValidas.add(combinacion);
                    }
                });
        return combinacionesValidas;
    }

    private Set<CombinacionPrenda> combinacionInferior(double nivelAbrigoNecesario) {
        return combinacionSimple(nivelAbrigoNecesario, prendasInferiores());
    }

    private Set<CombinacionPrenda> combinacionCalzado(double nivelAbrigoNecesario) {
        return combinacionSimple(nivelAbrigoNecesario, calzados());
    }
    
}