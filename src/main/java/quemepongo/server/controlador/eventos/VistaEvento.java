package quemepongo.server.controlador.eventos;

import quemepongo.dominio.evento.Localizacion;

public class VistaEvento {

    private boolean isFechaInvalida;

    public VistaEvento(boolean isFechaInvalida){
        this.isFechaInvalida = isFechaInvalida;
    }

    public Localizacion[] getLugares() {
        return Localizacion.values();
    }

    public boolean getIsFechaInvalida() {
        return this.isFechaInvalida;
    }

}
