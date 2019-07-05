package quemepongo.model.notificador;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;
import quemepongo.model.FactorClimatico;
import quemepongo.model.evento.Evento;
import quemepongo.model.usuario.RepositorioUsuario;
import quemepongo.model.usuario.Usuario;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

/**
 * Comienza a comprobar alertas cuando se llama disparar() en DisparadorComprobadorAlertas
 */
public class ComprobadorDeAlertas implements Job {

    private Duration tiempoDeAnticipacion = Duration.of(1, ChronoUnit.HOURS);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("Comprobando alertas...");

        Set<Usuario> usuarios = RepositorioUsuario.getInstancia().getUsuarios();

        usuarios.forEach(u ->
                u.eventosProximos(tiempoDeAnticipacion).forEach(e -> {
                    if (e.tieneSugerenciaAceptada()) {
                        comprobrobarAlertasMeteorologicas(e, u);
                    } else {
                        sugerir(e, u);
                    }
                })
        );
        System.out.println("Comprobación finalizada...");
    }

    private void sugerir(Evento evento, Usuario usuario) {
        usuario.sugerencias(evento); // TODO por ahora no se hace nada con las sugerencias
        usuario.getNotificador().notificarSugerenciasListas();
    }

    private void comprobrobarAlertasMeteorologicas(Evento evento, Usuario usuario) {
        ApiDeClima proovedorDeClima = SelectorDeProveedorDeClima.getInstancia().getProovedorDeClima();
        List<FactorClimatico> factoresClimaticos = proovedorDeClima.obtenerAlertasActuales(evento.getLugar());

        if (factoresClimaticos.stream().anyMatch(fc -> !evento.getSugerenciaAceptada().esAptoPara(fc))) {
            usuario.getNotificador().notificarAlertaMeteorologica();
        }
    }
}
