package quemepongo.dominio.calificacion;

public enum Puntuacion {
    CALUROSO("Sentí calor", -0.1),
    AGRADABLE("Estuvo bien", 0),
    CONGELADO("Sentí frío", 0.1);

    String descripcion;
    double varianzaSensibilidad;

    Puntuacion(String descripcion, double varianzaSensibilidad){
        this.descripcion = descripcion;
        this.varianzaSensibilidad = varianzaSensibilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getVarianzaSensibilidad() {
        return varianzaSensibilidad;
    }
}
