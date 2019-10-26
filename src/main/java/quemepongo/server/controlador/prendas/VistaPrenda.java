package quemepongo.server.controlador.prendas;

import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.prenda.*;
import java.util.List;

public class VistaPrenda {

    private Long guardarropaId;

    public List<Material> getMateriales() {
        return Material.getTodos();
    }

    public List<Color> getColores() {
        return Color.getTodos();
    }

    public List<Categoria> getCategorias() {
        return Categoria.getTodos();
    }

    public List<Capa> getCapas() {
        return Capa.getTodos();
    }

    public List<FactorClimatico> getFactoresClimaticos() {
        return FactorClimatico.getTodos();
    }

    public VistaPrenda(Long guardarropaId){
        this.guardarropaId = guardarropaId;
    }

    public Long getGuardarropaId(){
        return this.guardarropaId;
    }

}
