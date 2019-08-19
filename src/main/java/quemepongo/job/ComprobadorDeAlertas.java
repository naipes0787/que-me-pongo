package quemepongo.job;

import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;
import quemepongo.model.FactorClimatico;
import quemepongo.model.evento.Evento;
import quemepongo.model.notificador.Notificador;
import quemepongo.model.usuario.Usuario;

import java.util.List;

class ComprobadorDeAlertas extends ProcesadorDeEventosProximos {

    private ApiDeClima proovedorDeClima = SelectorDeProveedorDeClima.getInstancia().getProovedorDeClima();

    public ComprobadorDeAlertas() {
        super(10);
    }

    @Override
    protected void procesarEvento(Evento evento, Usuario usuario) {
        List<FactorClimatico> factoresClimaticos = proovedorDeClima.obtenerAlertasActuales(evento.getLugar());

        if (factoresClimaticos.stream().anyMatch(fc -> !evento.getSugerenciaAceptada().esAptoPara(fc))) {  // TODO REVISAR
            usuario.getNotificador()
                    .ifPresent(Notificador::notificarAlertaMeteorologica);
        }
    }

}
