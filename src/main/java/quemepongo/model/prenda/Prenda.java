package quemepongo.model.prenda;

import quemepongo.model.FactorClimatico;

import javax.persistence.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

@Entity
public class Prenda {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "tipo_prenda_id", referencedColumnName = "id")
	private TipoPrenda tipo;

    @Enumerated
    @Column(columnDefinition = "smallint")
	private Material material;

    @Transient
    private Color colorPrincipal;

    @Transient
    private Color colorSecundario;

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
