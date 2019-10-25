package quemepongo.dominio.prenda;

import quemepongo.dominio.calificacion.TipoCalificacion;

import java.util.Arrays;
import java.util.List;

public enum Color {
    ROJO("Rojo"),
    AZUL("Azul"),
    AMARILLO("Amarillo"),
    VERDE("Verde"),
    NARANJA("Naranja"),
    VIOLETA("Violeta"),
    NEGRO("Negro"),
    BLANCO("Blanco"),
    MARRON("Marrón"),
    BEIGE("Beige");

    private String nombreAMostrar;

    Color(String nombreAMostrar) {
        this.nombreAMostrar = nombreAMostrar;
    }

    public String getNombre() {
        return nombreAMostrar;
    }

    public static List<Color> getTodos() {
        return Arrays.asList(values());
    }
}
