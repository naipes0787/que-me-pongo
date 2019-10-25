package quemepongo.dominio.calificacion;

import java.util.Arrays;
import java.util.List;

public enum TipoCalificacion {
    GLOBAL("Global"),
    MANOS("Manos"),
    CUELLO("Cuello"),
    CABEZA("Cabeza");

    String descripcion;
    Puntuacion[] puntuacionesPosibles = Puntuacion.values();

    TipoCalificacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public List<Puntuacion> getPuntuacionesPosibles() {
        return Arrays.asList(puntuacionesPosibles);
    }

    public String getCodigo() {
        return name();
    }

    public static List<TipoCalificacion> getTodos() {
        return Arrays.asList(values());
    }
}
