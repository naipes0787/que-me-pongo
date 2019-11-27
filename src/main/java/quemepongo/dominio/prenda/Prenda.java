package quemepongo.dominio.prenda;

import quemepongo.dominio.Entidad;
import quemepongo.dominio.FactorClimatico;

import javax.persistence.*;

@Entity
public class Prenda extends Entidad {

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "tipo_prenda_id")
	private TipoPrenda tipo;

    @Enumerated
    @Column(columnDefinition = "smallint")
	private Material material;

    @Enumerated
    private Color colorPrincipal;

    @Enumerated
    private Color colorSecundario;

    private String urlFoto;

    public Prenda(String nombre, TipoPrenda tipo, Material material, Color colorPrincipal, Color colorSecundario, String urlFoto) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.material = material;
        this.colorPrincipal = colorPrincipal;
        this.colorSecundario = colorSecundario;
        this.urlFoto = urlFoto;
    }

    public Prenda() {}

    public Categoria getCategoria(){
        return this.tipo.getCategoria();
    }

    public TipoPrenda getTipo(){
        return this.tipo;
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
        return colorPrincipal;
    }

    public Imagen getFoto() {
        return urlFoto != null ? new Imagen(urlFoto) : Imagen.PRENDA_DESCONOCIDA;
    }

    public Material getMaterial() {
        return material;
    }

}
