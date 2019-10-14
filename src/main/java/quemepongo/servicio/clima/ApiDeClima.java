package quemepongo.servicio.clima;

import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.Temperatura;
import quemepongo.dominio.evento.Localizacion;

import java.util.List;

public interface ApiDeClima {

    Temperatura obtenerTemperaturaActual(Localizacion localizacion);

    List<FactorClimatico> obtenerAlertasActuales(Localizacion localizacion);
}
