package quemepongo.config;

import org.junit.Before;
import quemepongo.servicio.ClienteTest;
import quemepongo.servicio.clima.ApiDeClima;
import quemepongo.servicio.clima.SelectorDeProveedorDeClima;
import quemepongo.dominio.Temperatura;
import quemepongo.dominio.guardarropa.Guardarropa;

public abstract class GuardarropaTestConfig extends TestConfigGeneral {

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
