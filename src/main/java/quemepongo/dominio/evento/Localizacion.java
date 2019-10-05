package quemepongo.dominio.evento;

public enum Localizacion {
    CABA("Ciudad Autónoma de Buenos Aires");

    private String nombre;

    Localizacion(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}