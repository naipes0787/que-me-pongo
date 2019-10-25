package quemepongo.servicio.clima;

import quemepongo.servicio.clima.cliente.ClienteAccuWeather;
import quemepongo.servicio.clima.cliente.ClienteOpenWeather;

public class SelectorDeProveedorDeClima {

    private static SelectorDeProveedorDeClima selector;

    private ApiDeClima proveedorDeClima;
    
    private SelectorDeProveedorDeClima() {
    	// Por default se utiliza AccuWeather
    	//this.proveedorDeClima = new ClienteAccuWeather();
        this.proveedorDeClima = new ClienteOpenWeather();
    }

    public static SelectorDeProveedorDeClima getInstancia() {
        if (selector == null) {
            selector = new SelectorDeProveedorDeClima();
        }
        return selector;
    }

    public ApiDeClima getProovedorDeClima() {
        return proveedorDeClima;
    }

    public void setProovedorDeClima(ApiDeClima proovedorDeClima) {
        this.proveedorDeClima = proovedorDeClima;
    }

}
