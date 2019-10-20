package quemepongo.config;

import org.junit.Before;
import quemepongo.dominio.prenda.*;

public class SugerenciasTestConfig extends TestBase {

    protected Prenda Musculosa;
    protected Prenda Camisa;
    protected Prenda Campera;
    protected Prenda Mongomery;
    protected Prenda Jean;
    protected Prenda Joggin;
    protected Prenda Botas;
    protected Prenda Borcegos;

    @Before
    public void inicializarPrendasExtra() {
        TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(20));
        TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(20));
        TipoPrenda CAMPERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(90));
        TipoPrenda MONGOMERY = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(90));
        TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(30));
        TipoPrenda JOGGIN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(30));
        TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(25));
        TipoPrenda BORCEGOS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(25));

        Musculosa = crearPrenda(MUSCULOSA);
        Camisa = crearPrenda(CAMISA);
        Campera = crearPrenda(CAMPERA);
        Mongomery = crearPrenda(MONGOMERY);
        Jean = crearPrenda(JEAN);
        Joggin = crearPrenda(JOGGIN);
        Botas = crearPrenda(BOTAS);
        Borcegos = crearPrenda(BORCEGOS);
    }

}
