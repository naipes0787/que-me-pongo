package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.Set;

public class Atuendo {

    private CombinacionPrenda prendasSuperiores;
    private CombinacionPrenda prendaInferior;
    private CombinacionPrenda calzado;
    private Prenda accesorio;

    public Atuendo(CombinacionPrenda prendasSuperiores, CombinacionPrenda prendaInferior, CombinacionPrenda calzado){
        this.prendasSuperiores = prendasSuperiores;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
    }

    public double getNivelAbrigo(){
        return (prendasSuperiores.getNivelAbrigo() + prendaInferior.getNivelAbrigo() + calzado.getNivelAbrigo());
    }

   /*

    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;
    private Prenda accesorio;

    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado) {
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
    }

    public Atuendo conAccesorio(Prenda accesorio) {
        this.accesorio = accesorio;
        return this;
    }


    public Prenda getPrendaSuperior() {
        return prendaSuperior;
    }

    public Prenda getPrendaInferior() {
        return prendaInferior;
    }

    public Prenda getCalzado() {
        return calzado;
    }

    public Prenda getAccesorio() {
        return accesorio;
    }*/
}
