package quemepongo.dominio.calificacion;

public enum OpcionesCalificacion {
    CALUROSO(-0.1),
    AGRADABLE(0),
    CONGELADO(0.1);

    public final double varianzaSensibilidad;

    OpcionesCalificacion(double varianzaSensibilidad){
        this.varianzaSensibilidad = varianzaSensibilidad;
    }
}
