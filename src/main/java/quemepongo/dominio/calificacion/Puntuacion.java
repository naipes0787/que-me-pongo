package quemepongo.dominio.calificacion;

public enum Puntuacion {
    CALUROSO("Sentí calor", -0.1),
    AGRADABLE("Estuvo bien", 0),
    CONGELADO("Sentí frío", 0.1);

    private String nombreAMostrar;
    private double varianzaSensibilidad;

    Puntuacion(String nombreAMostrar, double varianzaSensibilidad){
        this.nombreAMostrar = nombreAMostrar;
        this.varianzaSensibilidad = varianzaSensibilidad;
    }

    public String getDescripcion() {
        return nombreAMostrar;
    }

    public double getVarianzaSensibilidad() {
        return varianzaSensibilidad;
    }
}
