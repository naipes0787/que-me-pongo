package quemepongo.model.usuario;

import quemepongo.model.calificacion.Calificacion;

public class Sensibilidad {
    private double sensibilidadClima = 1;
    private double sensibilidadManos;
    private double sensibilidadCuello;
    private double sensibilidadCabeza;

    public void modificarSensibilidad(Calificacion calificacion){
        sensibilidadClima += calificacion.getCalificacionManos().varianzaSensibilidad;
        sensibilidadManos += calificacion.getCalificacionManos().varianzaSensibilidad;
        sensibilidadCuello += calificacion.getCalificacionCuello().varianzaSensibilidad;
        sensibilidadCabeza += calificacion.getCalificacionCabeza().varianzaSensibilidad;
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
