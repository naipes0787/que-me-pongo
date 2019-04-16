package quemepongo.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* TODO: Falta definir si esto sería una clase o un Enum, 
 * ejemplo de cómo se vería como Enum en:
 *  	https://github.com/naipes0787/quemepongo/blob/master/src/main/java/dds/quemepongo/model/TipoPrenda.java
 */
public class TipoPrenda {
    public Categoria categoria;
    public List<Material> materialesValidos = new ArrayList<>();

    public TipoPrenda(Categoria categoria, List<Material> materialesValidos){
        this.categoria = Objects.requireNonNull(categoria, "Categoria Obligatorio");
        this.materialesValidos = Objects.requireNonNull(materialesValidos, "Materiales validos al menos uno");
    }

    public boolean tramaValida(Material material){
        return materialesValidos.contains(material);
    }

    public Categoria getCategoria(){
        return categoria;
    }
}
