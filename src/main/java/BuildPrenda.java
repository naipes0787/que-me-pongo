import java.awt.*;

public class BuildPrenda {

    public TipoPrenda tipo;
    public Material material;
    public Color colorPrincipal;
    public Color colorSecundario;
    public Trama trama = Trama.LISA;


    //Validar que en todos los setters no se ingrese null

    public void definirTipoPrenda(TipoPrenda tipoPrenda) {
        this.tipo = tipoPrenda;
    }

    public void definirMaterial(Material material) {
        this.material = material;
    }

    public void definirTrama(Trama trama) {
        this.trama = trama;
    }

    public void definirColorPrincipal(Color color) {
        this.colorPrincipal = color;
    }

    public void definirColorSecundario(Color color) {
        this.colorSecundario = color;
    }

    public Prenda generarPrenda(){
        //Pendiente: validar que todo sea no null menos colorSecundario.
        return new Prenda(tipo, material, trama, colorPrincipal, colorSecundario);
    }

}
