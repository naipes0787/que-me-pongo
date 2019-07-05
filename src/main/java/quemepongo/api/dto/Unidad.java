package quemepongo.api.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Unidad {
    C("C"),F("F"),KMH("km/h"), MIH("mi/h");

    private String nombre;

    Unidad(String nombre){
        this.nombre = nombre;
    }

    @JsonValue
    public String getNombre() {
        return nombre;
    }
}
