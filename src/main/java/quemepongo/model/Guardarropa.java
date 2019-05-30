package quemepongo.model;

import com.google.common.collect.Sets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Guardarropa {
    private double margenError = 0.1;
    private Set<Prenda> prendas = Sets.newHashSet();

    private Set<Prenda> prendasSuperiores() {return prendasDeCategoria(Categoria.PRENDA_SUPERIOR);}
    private Set<Prenda> prendasInferiores() {return prendasDeCategoria(Categoria.PRENDA_INFERIOR);}
    private Set<Prenda> calzados() { return prendasDeCategoria(Categoria.CALZADO);}
    private Set<Prenda> accesorios() {prendasDeCategoria(Categoria.ACCESORIO);}

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
    private Set<Prenda> prendasDeCategoria(Categoria categoria){
        return prendas.stream().
                filter(prenda -> prenda.getCategoria() == categoria)
                .collect(Collectors.toSet());
    }

    private Set<Prenda> prendasSuperioresPorCapa (Capa capa) {
        return prendasSuperiores()
                .stream()
                .filter(prenda -> prenda.getCapa() == capa)
                .collect(Collectors.toSet());
    }

    private Set<Set<Prenda>> combinacionesSuperior(double nivelAbrigoNecesario){
        Set<List<Prenda>> combinaciones = Sets.cartesianProduct(prendasSuperioresPorCapa(Capa.BASE),
                                                                      prendasSuperioresPorCapa(Capa.INTERMEDIO) ,
                                                                      prendasSuperioresPorCapa(Capa.ULTIMA_PRENDA) );
        Set<Set<Prenda>> combinacionesValidas = Sets.newHashSet();
        combinaciones.stream()
                     .forEach(c -> {
                           if (abrigaLoSuficiente(nivelAbrigoNecesario, c.get(0), c.get(1), c.get(2))) {
                               combinacionesValidas.add(Sets.newHashSet(c.get(0), c.get(1), c.get(2)));
                           }
                     });
         return combinacionesValidas;
    }

    private boolean abrigaLoSuficiente(double nivelAbrigoNecesario, Prenda prenda1, Prenda prenda2, Prenda prenda3){
        double nivelAbrigo = prenda1.getNivelAbrigo() + prenda2.getNivelAbrigo() + prenda3.getNivelAbrigo();
        return (margenInferior(nivelAbrigoNecesario) <= nivelAbrigo && nivelAbrigo <= margenSuperior(nivelAbrigoNecesario));
    }

    private double getMargenError(){ return margenError;}
    private double margenInferior(double nivelAbrigoNecesario){return nivelAbrigoNecesario * (1 - getMargenError());}
    private double margenSuperior(double nivelAbrigoNecesario){ return nivelAbrigoNecesario * (1 + getMargenError());}




}