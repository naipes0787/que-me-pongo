package quemepongo.dominio.prenda;

import java.util.Arrays;
import java.util.List;

public enum Material {
    ALGODON("Algodón"),
    BRONCE("Bronce"),
    CUERO("Cuero"),
    GABARDINA("Gabardina"),
    LANA("Lana"),
    LINO("Lino"),
    LONA("Lona"),
    ORO("Oro"),
    OXFORD("Oxford"),
    PIQUE("Pique"),
    PLASTICO("Plástico"),
    PLATA("Plata"),
    POLIESTER("Poliester"),
    SEDA("Seda");

    private String nombreAMostrar;

    Material(String nombreAMostrar) {
        this.nombreAMostrar = nombreAMostrar;
    }

    public String getNombre() {
        return nombreAMostrar;
    }

    public static List<Material> getTodos() {
        return Arrays.asList(values());
    }
}