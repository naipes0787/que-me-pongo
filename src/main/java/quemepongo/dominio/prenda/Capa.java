package quemepongo.dominio.prenda;

import java.util.Arrays;
import java.util.List;

public enum Capa {
    BASE("Base"),
    ABRIGO("Abrigo");

    private String nombreAMostrar;

    Capa(String nombreAMostrar) {
        this.nombreAMostrar = nombreAMostrar;
    }

    public String getNombre() {
        return nombreAMostrar;
    }

    public static List<Capa> getTodos() {
        return Arrays.asList(values());
    }
}
