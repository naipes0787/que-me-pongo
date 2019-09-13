package quemepongo.model.prenda;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import quemepongo.model.FactorClimatico;
import quemepongo.model.Entidad;
import quemepongo.model.prenda.conversor.ConversorColor;
import quemepongo.model.prenda.conversor.ConversorImagen;

import javax.persistence.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

@Entity
public class Prenda extends Entidad {

    @OneToOne
    @JoinColumn(name = "tipo_prenda_id", referencedColumnName = "id")
    @Cascade(CascadeType.ALL)
	private TipoPrenda tipo;

    @Enumerated
    @Column(columnDefinition = "smallint")
	private Material material;

    @Column
    @Convert(converter = ConversorColor.class)
    private Color colorPrincipal;

    @Column
    @Convert(converter = ConversorColor.class)
    private Color colorSecundario;

    @Column
    @Convert(converter = ConversorImagen.class)
    @Transient
    private BufferedImage foto;

    public Prenda(TipoPrenda tipo, Material material, Color colorPrincipal, Color colorSecundario, 
    		BufferedImage foto){
        this.tipo = tipo;
        this.material = material;
        this.colorPrincipal = colorPrincipal;
        this.colorSecundario = colorSecundario;
        this.foto = foto;
    }

    public Categoria getCategoria(){
        return this.tipo.getCategoria();
    }

    public double getNivelAbrigo() {
    	return this.tipo.getNivelAbrigo();
    }

    public Capa getCapa(){
    	return this.tipo.getCapa();
    }

    public boolean esAptaPara(FactorClimatico factorClimatico) {
        return tipo.esAptoPara(factorClimatico);
    }
}
