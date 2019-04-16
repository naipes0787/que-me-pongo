package quemepongo.model;
import java.awt.*;

public class Prenda {
    public TipoPrenda tipo;
    public Material material;
    public Color colorPrincipal;
    public Color colorSecundario;
    public Trama trama;

    public Prenda(TipoPrenda tipo, Material material, Trama trama, Color colorPrincipal, Color colorSecundario){
        this.tipo = tipo;
        this.material = material;
        this.trama = trama;
        this.colorPrincipal = colorPrincipal;
        this.colorSecundario = colorSecundario;
    }

    public Categoria getCategoria(){
        return this.tipo.getCategoria();
    }

}
