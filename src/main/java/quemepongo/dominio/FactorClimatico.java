package quemepongo.dominio;

import java.util.Arrays;
import java.util.List;

public enum FactorClimatico {
    LLUVIA, VIENTO, SOL;

    public static List<FactorClimatico> getTodos() {
        return Arrays.asList(values());
    }
}
