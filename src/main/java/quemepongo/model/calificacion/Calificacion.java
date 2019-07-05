package quemepongo.model.calificacion;

public class Calificacion {
    private OpcionesCalificacion calificacionGlobal;
    private OpcionesCalificacion calificacionManos;
    private OpcionesCalificacion calificacionCuello;
    private OpcionesCalificacion calificacionCabeza;

    public Calificacion(OpcionesCalificacion calificacionGlobal, OpcionesCalificacion calificacionManos,
                        OpcionesCalificacion calificacionCuello, OpcionesCalificacion calificacionCabeza){
        this.calificacionGlobal = calificacionGlobal;
        this.calificacionManos = calificacionManos;
        this.calificacionCuello = calificacionCuello;
        this.calificacionCabeza = calificacionCabeza;
    }

    public OpcionesCalificacion getCalificacionGlobal() {
        return calificacionGlobal;
    }

    public OpcionesCalificacion getCalificacionManos() {
        return calificacionManos;
    }

    public OpcionesCalificacion getCalificacionCuello() {
        return calificacionCuello;
    }

    public OpcionesCalificacion getCalificacionCabeza() {
        return calificacionCabeza;
    }
}
