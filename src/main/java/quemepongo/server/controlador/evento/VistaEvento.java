package quemepongo.server.controlador.evento;

import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.prenda.Capa;
import quemepongo.dominio.prenda.Categoria;
import quemepongo.dominio.prenda.Color;
import quemepongo.dominio.prenda.Material;

import java.util.List;

public class VistaEvento {

    private boolean isFechaInvalida;

    public VistaEvento(boolean isFechaInvalida){
        this.isFechaInvalida = isFechaInvalida;
    }

    public Localizacion[] getLugares() {
        return Localizacion.values();
    }

    public boolean getIsFechaInvalida() {
        return this.isFechaInvalida;
    }

}
