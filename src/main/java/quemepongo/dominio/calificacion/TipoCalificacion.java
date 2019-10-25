package quemepongo.dominio.calificacion;

import java.util.Arrays;
import java.util.List;

public enum TipoCalificacion {
    GLOBAL("Global"),
    MANOS("Manos"),
    CUELLO("Cuello"),
    CABEZA("Cabeza");

    private String nombreAMostrar;
    private List<Puntuacion> puntuacionesPosibles = Arrays.asList(Puntuacion.values());

    TipoCalificacion(String nombreAMostrar) {
        this.nombreAMostrar = nombreAMostrar;
    }

    public String getDescripcion() {
        return this.nombreAMostrar;
    }

    public List<Puntuacion> getPuntuacionesPosibles() {
        return puntuacionesPosibles;
    }

    public String getCodigo() {
        return name();
    }

    public static List<TipoCalificacion> getTodos() {
        return Arrays.asList(values());
    }
}
