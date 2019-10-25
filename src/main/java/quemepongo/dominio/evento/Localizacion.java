package quemepongo.dominio.evento;

public enum Localizacion {
    CABA("Ciudad Autónoma de Buenos Aires"),
    AVELLANEDA("Avellaneda"),
    BERNAL("Bernal"),
    SAN_ISIDRO("San Isidro"),
    QUILMES("Quilmes"),
    VILLA_LUGANO("Villa Lugano");

    private String nombre;

    Localizacion(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}