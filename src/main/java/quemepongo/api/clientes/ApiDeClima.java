package quemepongo.api.clientes;

import quemepongo.model.FactorClimatico;
import quemepongo.model.Temperatura;
import quemepongo.model.evento.Localizacion;

import java.util.List;

public interface ApiDeClima {

    Temperatura obtenerTemperaturaActual(Localizacion localizacion);

    List<FactorClimatico> obtenerAlertasActuales(Localizacion localizacion);
}
