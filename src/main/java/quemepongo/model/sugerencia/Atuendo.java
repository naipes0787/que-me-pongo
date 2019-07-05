package quemepongo.model.sugerencia;

import com.google.common.collect.Sets;

import quemepongo.model.prenda.CombinacionPrenda;
import quemepongo.model.prenda.Prenda;

import java.util.Set;
import java.util.stream.Collectors;

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

    public void agregarAccesorio(CombinacionPrenda nuevoAccesorio){
        nuevoAccesorio.getPrendas().stream().forEach(prenda -> this.accesorio.agregarPrenda(prenda));
    }

    public double getNivelAbrigo(){
        return (prendasSuperiores.getNivelAbrigo() + prendaInferior.getNivelAbrigo() + calzado.getNivelAbrigo());
    }

    public boolean abrigaLoSuficiente(double nivelAbrigo, double margenError){
        return (margenInferior(nivelAbrigo, margenError) <= getNivelAbrigo()
                && getNivelAbrigo() <= margenSuperior(nivelAbrigo, margenError));
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

    public Set<Prenda> prendas() {
        Set<CombinacionPrenda> combinaciones = Sets.newHashSet();
        combinaciones.add(prendaInferior);
        combinaciones.add(prendasSuperiores);
        combinaciones.add(prendaInferior);
        combinaciones.add(calzado);
        return combinaciones.stream().flatMap(c -> c.getPrendas().stream()).collect(Collectors.toSet());
    }
}
