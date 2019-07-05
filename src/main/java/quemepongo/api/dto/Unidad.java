package quemepongo.api.dto;

public enum Unidad {
    C("C"),F("F"),KMH("km/h");

    private String nombre;

    Unidad(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
