package quemepongo.model.prenda;

import net.coobird.thumbnailator.Thumbnails;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import quemepongo.exceptions.PathInvalidoException;
import quemepongo.model.FactorClimatico;
import quemepongo.model.Entidad;
import quemepongo.model.prenda.conversor.ConversorColor;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Entity
public class Prenda extends Entidad {

    public static final Integer ANCHO_FOTO = 200;
    public static final Integer ALTO_FOTO = 200;

    @OneToOne
    @JoinColumn(name = "tipo_prenda_id", referencedColumnName = "id")
    @Cascade({CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
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
    private String urlFoto;

    public Prenda(TipoPrenda tipo, Material material, Color colorPrincipal, Color colorSecundario,
                  String urlFoto){
        this.tipo = tipo;
        this.material = material;
        this.colorPrincipal = colorPrincipal;
        this.colorSecundario = colorSecundario;
        this.urlFoto = urlFoto;
    }

    public Prenda(){}

    public BufferedImage getFoto(){
    	try {
    		return Thumbnails.of(ImageIO.read(new File(this.urlFoto))).
    				forceSize(ANCHO_FOTO, ALTO_FOTO).asBufferedImage();
    	} catch(IOException ex) {
    		throw new PathInvalidoException(this.urlFoto);
    	}
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
