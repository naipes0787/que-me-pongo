package quemepongo.config;

import org.junit.Before;
import quemepongo.model.prenda.*;
import quemepongo.model.sugerencia.Atuendo;

public abstract class AtuendoTestConfig extends TestConfig {

    private static final TipoPrenda INFERIOR = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15));
    private static final TipoPrenda SUPERIOR = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));
    private static final TipoPrenda CALZADO = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(7));

    protected Atuendo atuendo;

    @Before
    public void setup() {
        CombinacionPrenda inferior = combinacionDe(crearPrenda(INFERIOR));
        CombinacionPrenda superior = combinacionDe(crearPrenda(SUPERIOR));
        CombinacionPrenda calzado = combinacionDe(crearPrenda(CALZADO));
        atuendo = new Atuendo(superior, inferior, calzado);
    }

    protected Atuendo atuendoAptoParaLluvia() {
        CombinacionPrenda inferior = combinacionDe(JeanDeOxfordNegro);
        CombinacionPrenda superior = combinacionDe(RemeraDeAlgodonNegra, PilotoNegro);
        CombinacionPrenda calzado = combinacionDe(BotasDeCueroNegras);
        return new Atuendo(superior, inferior, calzado);
    }

}
