package quemepongo.dominio.prenda;

import java.util.Arrays;
import java.util.List;

public enum Capa {
    BASE,
    ABRIGO;

    public static List<Capa> getTodos() {
        return Arrays.asList(values());
    }
}
