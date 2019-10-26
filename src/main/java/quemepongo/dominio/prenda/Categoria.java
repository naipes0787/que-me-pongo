package quemepongo.dominio.prenda;

import java.util.Arrays;
import java.util.List;

public enum Categoria {
    CALZADO("Calzado"),
    PRENDA_INFERIOR("Parte inferior"),
    PRENDA_SUPERIOR("Parte superior"),
    ACCESORIO_MANOS("Accesorio de manos"),
    ACCESORIO_CUELLO("Accesorio de cuello"),
    ACCESORIO_CABEZA("Accesorio de cabeza"),
    ACCESORIO_CONTRALLUVIA("Accesorio contra lluvia");

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
