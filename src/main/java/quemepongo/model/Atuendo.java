package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.Set;

public class Atuendo {

    private CombinacionPrenda prendasSuperiores;
    private CombinacionPrenda prendaInferior;
    private CombinacionPrenda calzado;
    private Prenda accesorio;
    
    private EstadoAtuendo estado = EstadoAtuendo.NUEVO;  
  
    public Atuendo(CombinacionPrenda prendasSuperiores, CombinacionPrenda prendaInferior, CombinacionPrenda calzado){
        this.prendasSuperiores = prendasSuperiores;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
    }
  
    public Atuendo conAccesorio(Prenda accesorio) {
        this.accesorio = accesorio;
        return this;
    }

    public double getNivelAbrigo(){
        return (prendasSuperiores.getNivelAbrigo() + prendaInferior.getNivelAbrigo() + calzado.getNivelAbrigo());
    }
  
    public Prenda getAccesorio() {
        return accesorio;
    }
  
    public boolean abrigaLoSuficiente(Temperatura temperatura, double margenError){
        return (margenInferior(temperatura.nivelDeAbrigo(), margenError) <= getNivelAbrigo()
                && getNivelAbrigo() <= margenSuperior(temperatura.nivelDeAbrigo(), margenError));
    }

    double nivelDeAbrigoPara(Temperatura temperatura){
        return 100;
    }

    private double margenInferior(double nivelAbrigoNecesario, double margenError){return nivelAbrigoNecesario * (1 - margenError);}
    private double margenSuperior(double nivelAbrigoNecesario, double margenError){return nivelAbrigoNecesario * (1 + margenError);}
    
    public void aceptar() {
        if (EstadoAtuendo.NUEVO.equals(estado)) {
            estado = EstadoAtuendo.ACEPTADO;
        }
    }

    public void rechazar() {
        if (EstadoAtuendo.NUEVO.equals(estado)) {
            estado = EstadoAtuendo.RECHAZADO;
        }
    }

    public void deshacerDecision() {
        estado = EstadoAtuendo.NUEVO;
    }

    public EstadoAtuendo getEstado() {
        return estado;
    }
}
