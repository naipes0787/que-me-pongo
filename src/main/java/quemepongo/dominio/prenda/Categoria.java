package quemepongo.dominio.prenda;

import java.util.Arrays;
import java.util.List;

public enum Categoria {
    CALZADO("Calzado"),
    PRENDA_INFERIOR("Parte inferior"),
    PRENDA_SUPERIOR("Parte superior"),
    ACCESORIO_MANOS("Accesorio"),
    ACCESORIO_CUELLO("Accesorio"),
    ACCESORIO_CABEZA("Accesorio"),
    ACCESORIO_CONTRALLUVIA("Accesorio");

    private String nombreAMostrar;

    Categoria(String nombreAMostrar) {
        this.nombreAMostrar = nombreAMostrar;
    }

    public String getNombre() {
        return nombreAMostrar;
    }

    public static List<Categoria> getTodos() {
        return Arrays.asList(values());
    }
}
