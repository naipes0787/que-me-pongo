package quemepongo.job;

import org.quartz.JobExecutionContext;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioUsuario;

import java.util.List;

public abstract class ProcesadorDeEventosProximos extends JobBase {

    ProcesadorDeEventosProximos(int frencuenciaEnSegundos) {
        super(frencuenciaEnSegundos);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        List<Usuario> usuarios = RepositorioUsuario.instancia().getUsuarios();

        usuarios.forEach(usuario ->
                usuario.getEventos().stream().filter(Evento::estaProximo).forEach(evento ->
                        procesarEvento(evento, usuario)
                )
        );
    }

    protected abstract void procesarEvento(Evento evento, Usuario usuario);
}
