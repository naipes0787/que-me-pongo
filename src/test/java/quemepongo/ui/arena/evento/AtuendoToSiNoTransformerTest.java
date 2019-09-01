package quemepongo.ui.arena.evento;

import org.junit.Test;
import quemepongo.config.AtuendoTestConfig;

import static org.junit.Assert.assertEquals;

public class AtuendoToSiNoTransformerTest extends AtuendoTestConfig {

    @Test
    public void siElTransformerRecibeUnAtuendo_DevuelveSi(){
        AtuendoToSiNoTransformer transformer = new AtuendoToSiNoTransformer();
        assertEquals("Sí", transformer.transform(atuendo));
    }

    @Test
    public void siElTransformerNoRecibeUnAtuendo_DevuelveNo(){
        AtuendoToSiNoTransformer transformer = new AtuendoToSiNoTransformer();
        assertEquals("No", transformer.transform(null));
    }

}
