package quemepongo.server.controlador.calificacion;

import quemepongo.dominio.calificacion.TipoCalificacion;
import quemepongo.dominio.sugerencia.Atuendo;

import java.util.List;

public class VistaCalificacion {
    private Atuendo atuendo;

    public VistaCalificacion(Atuendo atuendo) {
        this.atuendo = atuendo;
    }

    public List<TipoCalificacion> getTiposCalificacion() {
        return TipoCalificacion.getTodos();
    }

    public Atuendo getAtuendo() {
        return atuendo;
    }

}
