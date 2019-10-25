package quemepongo.job;

import quemepongo.servicio.clima.ApiDeClima;
import quemepongo.servicio.clima.SelectorDeProveedorDeClima;
import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.notificador.Notificador;
import quemepongo.dominio.usuario.Usuario;

import java.util.List;

class ComprobadorDeAlertas extends ProcesadorDeEventosProximos {

    private ApiDeClima proovedorDeClima = SelectorDeProveedorDeClima.getInstancia().getProovedorDeClima();

    public ComprobadorDeAlertas() {
        super(10);
    }

    @Override
    protected void procesarEvento(Evento evento, Usuario usuario) {
        List<FactorClimatico> factoresClimaticos = proovedorDeClima.obtenerAlertasActuales(evento.getLugar());

        if (factoresClimaticos.stream().anyMatch(fc -> !evento.getAtuendo().esAptoPara(fc))) {  // TODO REVISAR
            usuario.getNotificador()
                    .ifPresent(Notificador::notificarAlertaMeteorologica);
        }
    }

}
