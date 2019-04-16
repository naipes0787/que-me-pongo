import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TipoPrenda {
    public Categoria categoria;
    public List<Material> materialesValidos = new ArrayList<>();

    TipoPrenda(Categoria categoria, List<Material> materialesValidos){
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
