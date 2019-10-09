package quemepongo.dominio.prenda;

import javafx.scene.paint.Color;
import net.coobird.thumbnailator.Thumbnails;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import quemepongo.excepcion.PathInvalidoException;
import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.Entidad;
import quemepongo.dominio.prenda.conversor.ConversorColor;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Entity
public class Prenda extends Entidad {

    public static final Integer ANCHO_FOTO = 200;
    public static final Integer ALTO_FOTO = 200;

    private String nombre;

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

    public Prenda(String nombre, TipoPrenda tipo, Material material, Color colorPrincipal, Color colorSecundario,
                  String urlFoto){
        this.nombre = nombre;
        this.tipo = tipo;
        this.material = material;
        this.colorPrincipal = colorPrincipal;
        this.colorSecundario = colorSecundario;
        this.urlFoto = urlFoto;
    }

    public Prenda() {}

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

    public String getNombre() {
        return nombre;
    }

    public Color getColorPrincipal() {
        return colorPrincipal; //TODO se usa desde el handlebars, ver cómo hacer para que se muestre el nombre del color (capaz tengamos que hacer una clase Color nuestra)
    }

    public String getUrlFoto() {
        return urlFoto;
    }
}
