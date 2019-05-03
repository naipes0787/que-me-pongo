package quemepongo.model;

import java.awt.*;

public class Prenda {
	
    public TipoPrenda tipo;
    public Material material;
    public Color colorPrincipal;
    public Color colorSecundario;

    public Prenda(TipoPrenda tipo, Material material, Color colorPrincipal, Color colorSecundario){
        this.tipo = tipo;
        this.material = material;
        this.colorPrincipal = colorPrincipal;
        this.colorSecundario = colorSecundario;
    }

    public Categoria getCategoria(){
        return this.tipo.getCategoria();
    }

}
