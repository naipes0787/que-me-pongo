package quemepongo.dominio.calificacion;

import quemepongo.dominio.Entidad;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.util.Map;

@Entity
public class Calificacion extends Entidad {
    @Enumerated
    private Puntuacion calificacionGlobal;
    @Enumerated
    private Puntuacion calificacionManos;
    @Enumerated
    private Puntuacion calificacionCuello;
    @Enumerated
    private Puntuacion calificacionCabeza;

    public Calificacion(Map<TipoCalificacion, Puntuacion> puntuaciones){
        this.calificacionGlobal = puntuaciones.get(TipoCalificacion.GLOBAL);
        this.calificacionManos = puntuaciones.get(TipoCalificacion.MANOS);
        this.calificacionCuello = puntuaciones.get(TipoCalificacion.CUELLO);
        this.calificacionCabeza = puntuaciones.get(TipoCalificacion.CABEZA);
    }

    public Calificacion() {
    }

    public Puntuacion getCalificacionGlobal() {
        return calificacionGlobal;
    }

    public Puntuacion getCalificacionManos() {
        return calificacionManos;
    }

    public Puntuacion getCalificacionCuello() {
        return calificacionCuello;
    }

    public Puntuacion getCalificacionCabeza() {
        return calificacionCabeza;
    }
}
