package quemepongo.job;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.notificador.Notificador;
import quemepongo.dominio.usuario.Usuario;

class Sugeridor extends ProcesadorDeEventosProximos {

    public Sugeridor() {
        super(5);
    }

    @Override
    protected void procesarEvento(Evento evento, Usuario usuario) {
        usuario.sugerencias(evento); // TODO por ahora no se hace nada con las sugerencias
        usuario.getNotificador()
                .ifPresent(Notificador::notificarSugerenciasListas);
    }
}
