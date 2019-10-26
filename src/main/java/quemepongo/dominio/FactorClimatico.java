package quemepongo.dominio;

import java.util.Arrays;
import java.util.List;

public enum FactorClimatico {
    LLUVIA("Lluvia"),
    VIENTO("Viento"),
    SOL("Sol");

    private String nombreAMostrar;

    FactorClimatico(String nombreAMostrar) {
        this.nombreAMostrar = nombreAMostrar;
    }

    public String getNombre() {
        return nombreAMostrar;
    }

    public static List<FactorClimatico> getTodos() {
        return Arrays.asList(values());
    }
}
