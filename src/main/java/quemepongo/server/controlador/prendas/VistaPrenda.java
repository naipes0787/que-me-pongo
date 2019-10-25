package quemepongo.server.controlador.prendas;

import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.prenda.*;
import java.util.List;

public class VistaPrenda {

    public List<Material> materiales;

    public List<Color> colores;

    public List<Categoria> categorias;

    public List<Capa> capas;

    public List<FactorClimatico> factoresclimaticos;

    public VistaPrenda(){
        this.materiales = Material.getTodos();
        this.colores = Color.getTodos();
        this.categorias = Categoria.getTodos();
        this.capas = Capa.getTodos();
        this.factoresclimaticos = FactorClimatico.getTodos();
    }


}
