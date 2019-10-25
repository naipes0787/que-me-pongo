package quemepongo.dominio.usuario;

import quemepongo.dominio.calificacion.Calificacion;

public class Sensibilidad {
    private double sensibilidadClima = 1;
    private double sensibilidadManos;
    private double sensibilidadCuello;
    private double sensibilidadCabeza;

    public void modificarSensibilidad(Calificacion calificacion) {
        sensibilidadClima += calificacion.getCalificacionGlobal().getVarianzaSensibilidad();
        sensibilidadManos += calificacion.getCalificacionManos().getVarianzaSensibilidad();
        sensibilidadCuello += calificacion.getCalificacionCuello().getVarianzaSensibilidad();
        sensibilidadCabeza += calificacion.getCalificacionCabeza().getVarianzaSensibilidad();
    }

    public double getSensibilidadClima() {
        return sensibilidadClima;
    }

    public double getSensibilidadManos() {
        return sensibilidadManos;
    }

    public double getSensibilidadCuello() {
        return sensibilidadCuello;
    }

    public double getSensibilidadCabeza() {
        return sensibilidadCabeza;
    }
}
