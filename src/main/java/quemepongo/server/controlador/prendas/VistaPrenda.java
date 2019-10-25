package quemepongo.server.controlador.prendas;

import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.prenda.*;
import java.util.List;

public class VistaPrenda {

    public List<Material> materiales(){return Material.getTodos();}

    public List<Color> colores(){return Color.getTodos();}

    public List<Categoria> categorias(){return Categoria.getTodos();}

    public List<Capa> capas(){return Capa.getTodos();}

    public List<FactorClimatico> factoresclimaticos(){return FactorClimatico.getTodos();}


}
