package quemepongo.server.controlador.sugerencias;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.sugerencia.Atuendo;

public class VistaSugerencia {

    private int numero;
    private Atuendo sugerencia;
    private Evento evento;
    private boolean permitirSiguiente;

    public VistaSugerencia(int numero, Atuendo sugerencia, Evento evento, boolean permitirSiguiente) {
        this.numero = numero;
        this.sugerencia = sugerencia;
        this.evento = evento;
        this.permitirSiguiente = permitirSiguiente;
    }

    public int getNumero() {
        return numero;
    }

    public Atuendo getSugerencia() {
        return sugerencia;
    }

    public Evento getEvento() {
        return evento;
    }

    public boolean getPermitirSiguiente() {
        return permitirSiguiente;
    }
}
