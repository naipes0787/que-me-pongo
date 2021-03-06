package quemepongo.job;

import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.notificador.Notificador;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.servicio.clima.ApiDeClima;
import quemepongo.servicio.clima.SelectorDeProveedorDeClima;

import java.util.List;

public class ComprobadorDeAlertas extends ProcesadorDeEventosProximos {

    private ApiDeClima proovedorDeClima = SelectorDeProveedorDeClima.getInstancia().getProovedorDeClima();

    public ComprobadorDeAlertas() {
        super(10);
    }

    @Override
    protected void procesarEvento(Evento evento, Usuario usuario) {
        List<FactorClimatico> factoresClimaticos = proovedorDeClima.obtenerAlertasActuales(evento.getLugar());

        if (factoresClimaticos.stream().anyMatch(fc -> !evento.getSugerenciaAceptada().esAptoPara(fc))) {
            usuario.getNotificador()
                    .ifPresent(Notificador::notificarAlertaMeteorologica);
        }
    }

}
