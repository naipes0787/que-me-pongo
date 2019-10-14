package quemepongo.dominio.prenda;

import com.google.common.collect.Sets;
import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.Entidad;

import javax.persistence.*;
import java.util.Set;

/*
Se modela con un Abstract Factory donde el TipoPrenda será instanciado a partir de diseniarTipo llamando a
un Fabricador de Tipo (TipoSuperior, TipoInferior, TipoCalzado, TipoAccesorio), el cual instanciará con la
Categoria y listado de materiales validos que corresponde.
 */
@Entity
public class TipoPrenda extends Entidad {

    @Enumerated
    @Column(columnDefinition = "smallint")
    public Categoria categoria;

    @ElementCollection(targetClass = Material.class)
    @CollectionTable(name = "material", joinColumns = @JoinColumn(name = "tipo_prenda_id"))
    @Column(name = "material", nullable = false)
    @Enumerated(EnumType.STRING)
    public Set<Material> materialesValidos;

    @Column
    public double nivelAbrigo;

    @Enumerated
    @Column(columnDefinition = "smallint")
    public Capa capa;

    @ElementCollection(targetClass = FactorClimatico.class)
    @CollectionTable(name = "factor_climatico", joinColumns = @JoinColumn(name = "tipo_prenda_id"))
    @Column(name = "factor_climatico", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<FactorClimatico> aptoPara;

    public TipoPrenda() {
    }

    public static TipoPrenda diseniarTipo(FabricaTipoPrenda tipo) {
        return new TipoPrenda(tipo.getCategoria(), tipo.getMaterialesValidos(), tipo.getNivelAbrigo(), tipo.getCapa(), Sets.newHashSet());
    }

    public static TipoPrenda diseniarTipo(FabricaTipoPrenda tipo, Set<FactorClimatico> aptoPara) {
        return new TipoPrenda(tipo.getCategoria(), tipo.getMaterialesValidos(), tipo.getNivelAbrigo(), tipo.getCapa(), aptoPara);
    }

    private TipoPrenda(Categoria categoria, Set<Material> materialesValidos, double nivelAbrigo, Capa capa, Set<FactorClimatico> aptoPara) {
        this.categoria = categoria;
        this.materialesValidos = materialesValidos;
        this.nivelAbrigo = nivelAbrigo;
        this.capa = capa;
        this.aptoPara = aptoPara;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public double getNivelAbrigo() {
    	return this.nivelAbrigo;
    }
    
    public Set<Material> getMaterialesValidos(){
    	return this.materialesValidos;
    }

    public Capa getCapa() {
    	return this.capa;
    }

    public boolean esAptoPara(FactorClimatico factorClimatico) {
        return aptoPara.contains(factorClimatico);
    }
}
