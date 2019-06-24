package quemepongo.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Prenda {
	
	private TipoPrenda tipo;
	private Material material;
    private Color colorPrincipal;
    private Color colorSecundario;
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
}
