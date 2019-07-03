package quemepongo.api.clientes;

import quemepongo.model.Alerta;
import quemepongo.model.Temperatura;
import quemepongo.model.evento.Localizacion;

import java.util.List;

public interface ApiDeClima {

    Temperatura obtenerTemperaturaActual(Localizacion localizacion);

    List<Alerta> obtenerAlertasActuales(Localizacion localizacion);
}
