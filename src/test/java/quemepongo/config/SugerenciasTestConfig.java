package quemepongo.config;

import quemepongo.model.prenda.*;

public class SugerenciasTestConfig extends TestConfig {

    private static final TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(20));
    private static final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(20));

    private static final TipoPrenda CAMPERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(90));
    private static final TipoPrenda MONGOMERY = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(90));

    private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(30));
    private static final TipoPrenda JOGGIN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(30));

    private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(25));
    private static final TipoPrenda BORCEGOS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(25));

    protected static Prenda Musculosa = crearPrenda(MUSCULOSA);
    protected static Prenda Camisa = crearPrenda(CAMISA);

    protected static Prenda Campera = crearPrenda(CAMPERA);
    protected static Prenda Mongomery = crearPrenda(MONGOMERY);

    protected static Prenda Jean = crearPrenda(JEAN);
    protected static Prenda Joggin = crearPrenda(JOGGIN);

    protected static Prenda Botas = crearPrenda(BOTAS);
    protected static Prenda Borcegos = crearPrenda(BORCEGOS);

}
