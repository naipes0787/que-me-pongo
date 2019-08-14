package quemepongo.config;

import org.junit.Before;
import quemepongo.api.ClienteTest;
import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;
import quemepongo.model.Temperatura;
import quemepongo.model.guardarropa.Guardarropa;

public abstract class GuardarropaTestConfig extends TestConfig {

    protected double nivelAbrigo;

    @Before
    public void preparar(){
        nivelAbrigo = new Temperatura(20.0).convertirANivelDeAbrigo();
        ApiDeClima proovedorDeClima = new ClienteTest();
        SelectorDeProveedorDeClima.getInstancia().setProovedorDeClima(proovedorDeClima);
    }

    protected Guardarropa guardarropaSinPrendasSuperiores() {
        return guardarropaCon(JeanDeOxfordNegro, PolleraDeAlgodonNegra, ZapatillasDeLonaNegras, BotasDeCueroNegras);
    }

    protected Guardarropa guardarropaSinPrendasInferiores() {
        return guardarropaCon(RemeraDeAlgodonNegra, CamisaDeAlgodonNegra, ZapatillasDeLonaNegras, BotasDeCueroNegras);
    }

    protected Guardarropa guardarropaSinCalzados() {
        return guardarropaCon(RemeraDeAlgodonNegra, CamisaDeAlgodonNegra, JeanDeOxfordNegro, PolleraDeAlgodonNegra);
    }
}
