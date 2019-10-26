package quemepongo.dominio.usuario;

import quemepongo.dominio.Entidad;
import quemepongo.dominio.calificacion.Calificacion;

import javax.persistence.Entity;

@Entity
public class Sensibilidad extends Entidad {
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

    public void setSensibilidadClima(double sensibilidadClima) {
        this.sensibilidadClima = sensibilidadClima;
    }

    public void setSensibilidadManos(double sensibilidadManos) {
        this.sensibilidadManos = sensibilidadManos;
    }

    public void setSensibilidadCuello(double sensibilidadCuello) {
        this.sensibilidadCuello = sensibilidadCuello;
    }

    public void setSensibilidadCabeza(double sensibilidadCabeza) {
        this.sensibilidadCabeza = sensibilidadCabeza;
    }
}
