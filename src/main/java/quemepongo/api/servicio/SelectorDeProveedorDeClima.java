package quemepongo.api.servicio;

import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.clientes.ClienteAccuWeather;
import quemepongo.api.clientes.ClienteOpenWeather;

public class SelectorDeProveedorDeClima {

    private ApiDeClima proovedorActual;
    private ApiDeClima clienteAccuweather;
    private ApiDeClima clienteOpenWeather;

    //TODO obtener valores de un .property
    private String accuWeatherHost = "http://dataservice.accuweather.com";
    private String accuWeatherKey = "4zxMMc9pFj6f6pOdoQ2TirQCUwLTmG9S";
    private String openWeatherHost = "http://api.openweathermap.org/data/2.5";
    private String openWeatherKey = "d0f7630cb095c3adf9622fdb01cd87c9";

    SelectorDeProveedorDeClima(ProveedorDeClima proovedor){
        instanciarNuevoProveedor(proovedor);
    }

    public ApiDeClima obtenerProveedorActual(){
        return proovedorActual;
    }

    private void instanciarClienteAccuWeather(){
        if (clienteAccuweather != null )
            clienteAccuweather = new ClienteAccuWeather(accuWeatherHost, accuWeatherKey);
    }

    private void instanciarClienteOpenWeather(){
        if (clienteOpenWeather != null)
            clienteOpenWeather = new ClienteOpenWeather(openWeatherHost, openWeatherKey);
    }

    public void instanciarNuevoProveedor(ProveedorDeClima nuevoProveedor) {
        switch (nuevoProveedor) {
            case ACCUWEATHER:
                instanciarClienteAccuWeather();
                proovedorActual = clienteAccuweather;
                break;
            case OPENWEATHER:
                instanciarClienteOpenWeather();
                proovedorActual = clienteOpenWeather;
                break;
        }
    }

    public void usarProveedorCustom(ApiDeClima proveedorCustom){
        proovedorActual = proveedorCustom;
    }
}
