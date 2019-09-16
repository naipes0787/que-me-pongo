package quemepongo.model.sugerencia;

import com.google.common.collect.Sets;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import quemepongo.model.Entidad;
import quemepongo.model.FactorClimatico;
import quemepongo.model.prenda.CombinacionPrenda;
import quemepongo.model.prenda.Prenda;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Atuendo extends Entidad {

    @ManyToOne
    @Cascade({CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private CombinacionPrenda prendasSuperiores;

    @ManyToOne
    @Cascade({CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private CombinacionPrenda prendaInferior;

    @ManyToOne
    @Cascade({CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private CombinacionPrenda calzado;

    @ManyToOne
    @Cascade({CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private CombinacionPrenda accesorio;

    @Transient
    private ComandoAtuendo ultimoComando;

    @Enumerated
    @Column(columnDefinition = "smallint")
    private EstadoAtuendo estado;

    public Atuendo(){}

    public Atuendo(CombinacionPrenda prendasSuperiores, CombinacionPrenda prendaInferior, CombinacionPrenda calzado){
        this.prendasSuperiores = prendasSuperiores;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        this.accesorio = new CombinacionPrenda(Sets.newHashSet());
        this.ultimoComando = new ComandoAtuendoNuevo(this);
        this.estado = EstadoAtuendo.NUEVO;
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

    public boolean esAptoPara(FactorClimatico factorClimatico) {
        return prendas().stream().anyMatch(p -> p.esAptaPara(factorClimatico));
    }

    public void setEstado(EstadoAtuendo estadoAtuendo){
        this.estado = estadoAtuendo;
    }

    public ComandoAtuendo getUltimoComando() {
        return ultimoComando;
    }

    public void setUltimoComando(ComandoAtuendo comandoAtuendo) {
        this.ultimoComando = comandoAtuendo;
    }
}
