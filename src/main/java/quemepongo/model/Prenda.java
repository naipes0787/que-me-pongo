package quemepongo.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Prenda {
	
	private TipoPrenda tipo;
    @SuppressWarnings("unused")
	private Material material;
    @SuppressWarnings("unused")
    private Color colorPrincipal;
    @SuppressWarnings("unused")
    private Color colorSecundario;
    @SuppressWarnings("unused")
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

}
