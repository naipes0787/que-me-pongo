package quemepongo.job;

import org.quartz.JobExecutionContext;
import quemepongo.model.evento.Evento;
import quemepongo.model.usuario.RepositorioUsuario;
import quemepongo.model.usuario.Usuario;

import java.util.Set;

public abstract class ProcesadorDeEventosProximos extends JobBase {

    ProcesadorDeEventosProximos(int frencuenciaEnSegundos) {
        super(frencuenciaEnSegundos);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Set<Usuario> usuarios = RepositorioUsuario.getInstancia().getUsuarios();

        usuarios.forEach(usuario ->
                usuario.getEventos().stream().filter(Evento::estaProximo).forEach(evento ->
                        procesarEvento(evento, usuario)
                )
        );
    }

    protected abstract void procesarEvento(Evento evento, Usuario usuario);
}
