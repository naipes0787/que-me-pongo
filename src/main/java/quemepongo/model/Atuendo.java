package quemepongo.model;

import com.google.common.collect.Sets;

import quemepongo.model.prenda.CombinacionPrenda;

public class Atuendo {

    private CombinacionPrenda prendasSuperiores;
    private CombinacionPrenda prendaInferior;
    private CombinacionPrenda calzado;
    private CombinacionPrenda accesorio;
    
    private EstadoAtuendo estado = EstadoAtuendo.NUEVO;  
  
    public Atuendo(CombinacionPrenda prendasSuperiores, CombinacionPrenda prendaInferior, CombinacionPrenda calzado){
        this.prendasSuperiores = prendasSuperiores;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        this.accesorio = new CombinacionPrenda(Sets.newHashSet());
    }
  
    public Atuendo conAccesorio(CombinacionPrenda accesorio) {
        this.accesorio = accesorio;
        return this;
    }

    public double getNivelAbrigo(){
        return (prendasSuperiores.getNivelAbrigo() + prendaInferior.getNivelAbrigo() + calzado.getNivelAbrigo());
    }

    public boolean abrigaLoSuficiente(Temperatura temperatura, double margenError){
        return (margenInferior(temperatura.convertirANivelDeAbrigo(), margenError) <= getNivelAbrigo()
                && getNivelAbrigo() <= margenSuperior(temperatura.convertirANivelDeAbrigo(), margenError));
    }

    private double margenInferior(double nivelAbrigoNecesario, double margenError){
    	return nivelAbrigoNecesario * (1 - margenError);
    }
    
    private double margenSuperior(double nivelAbrigoNecesario, double margenError){
    	return nivelAbrigoNecesario * (1 + margenError);
    }
    
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

    public CombinacionPrenda getPrendasSuperiores(){
    	return prendasSuperiores;
    }
    
    public CombinacionPrenda getPrendaInferior(){
    	return prendaInferior;
    }
    
    public CombinacionPrenda getCalzado() {
    	return calzado;
    }
    
    public CombinacionPrenda getAccesorio(){
    	return accesorio;
    }

    public int getCantidadPrendas(){
        return getPrendasSuperiores().getCantPrendas()
                + getPrendaInferior().getCantPrendas()
                + getCalzado().getCantPrendas()
                + getAccesorio().getCantPrendas();
    }

}
