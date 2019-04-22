package quemepongo.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Atuendo {

    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;
    private Prenda accesorio;

    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado) {
        this.prendaSuperior = prendaValida(prendaSuperior, Categoria.PARTE_SUPERIOR);
        this.prendaInferior = prendaValida(prendaInferior, Categoria.PARTE_INFERIOR);
        this.calzado = prendaValida(calzado, Categoria.CALZADO);
    }

    public Atuendo conAccesorio(Prenda accesorio) {
        this.accesorio = prendaValida(accesorio, Categoria.ACCESORIO);
        return this;
    }

    private Prenda prendaValida(Prenda prenda, Categoria categoria) {
        checkNotNull(prenda, "Se requiere una prenda de categoría '%s'", categoria);
        checkArgument(categoria.equals(prenda.getCategoria()), "La prenda debería ser de categoría '%s', pero es de categoría '%s'", categoria, prenda.getCategoria());
        return prenda;
    }
}
