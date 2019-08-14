package quemepongo.job;

import quemepongo.model.evento.Evento;
import quemepongo.model.usuario.Usuario;

class Sugeridor extends ProcesadorDeEventosProximos {

    public Sugeridor() {
        super(5);
    }

    @Override
    protected void procesarEvento(Evento evento, Usuario usuario) {
        usuario.sugerencias(evento); // TODO por ahora no se hace nada con las sugerencias
        usuario.getNotificador().notificarSugerenciasListas();
    }
}
