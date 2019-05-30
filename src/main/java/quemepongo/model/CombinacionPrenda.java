package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.Set;

public class CombinacionPrenda {
    Set<Prenda> prendas = Sets.newHashSet();

    public CombinacionPrenda(Set<Prenda> prendas){
        this.prendas = prendas;
    }

    public double getNivelAbrigo() {
        return prendas.stream().mapToDouble(prenda -> prenda.getNivelAbrigo()).sum();
    }

    public boolean abrigaLoSuficiente(double nivelAbrigoNecesario, double margenError){
        return (margenInferior(nivelAbrigoNecesario, margenError) <= getNivelAbrigo()
                && getNivelAbrigo() <= margenSuperior(nivelAbrigoNecesario, margenError));
    }

    private double margenInferior(double nivelAbrigoNecesario, double margenError){return nivelAbrigoNecesario * (1 - margenError);}
    private double margenSuperior(double nivelAbrigoNecesario, double margenError){return nivelAbrigoNecesario * (1 + margenError);}


}
