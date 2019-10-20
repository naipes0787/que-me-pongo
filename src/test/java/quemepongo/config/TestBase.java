package quemepongo.config;

import com.google.common.collect.Sets;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.EventoUnico;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.*;
import quemepongo.dominio.sugerencia.Atuendo;
import quemepongo.dominio.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Arrays;

import static javafx.scene.paint.Color.BLACK;
import static quemepongo.dominio.prenda.Material.*;

public abstract class TestBase extends AbstractPersistenceTest implements WithGlobalEntityManager {

    protected double NIVEL_ABRIGO_DEFAULT = 50;
    protected TipoPrenda JEAN;
    protected TipoPrenda POLLERA;
    protected TipoPrenda REMERA;
    protected TipoPrenda MUSCULOSA;
    protected TipoPrenda CAMISA;
    protected TipoPrenda PILOTO;
    protected TipoPrenda ZAPATILLAS;
    protected TipoPrenda BOTAS;
    protected TipoPrenda BUFANDA;

    protected Prenda JeanDeOxfordNegro;
    protected Prenda PolleraDeAlgodonNegra;
    protected Prenda RemeraDeAlgodonNegra;
    protected Prenda MusculosaDeAlgodonNegra;
    protected Prenda CamisaDeAlgodonNegra;
    protected Prenda PilotoNegro;
    protected Prenda ZapatillasDeLonaNegras;
    protected Prenda BotasDeCueroNegras;
    protected Prenda BufandaDeLana;

    @Before
    public void inicializarPrendasBase() {
        JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(NIVEL_ABRIGO_DEFAULT));
        POLLERA = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(NIVEL_ABRIGO_DEFAULT));
        REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(NIVEL_ABRIGO_DEFAULT));
        MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(NIVEL_ABRIGO_DEFAULT));
        CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(NIVEL_ABRIGO_DEFAULT));
        PILOTO = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(NIVEL_ABRIGO_DEFAULT), Sets.newHashSet(FactorClimatico.LLUVIA));
        ZAPATILLAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(NIVEL_ABRIGO_DEFAULT));
        BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(NIVEL_ABRIGO_DEFAULT));
        BUFANDA = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorioCuello());

        JeanDeOxfordNegro = crearPrenda(JEAN, OXFORD, BLACK);
        PolleraDeAlgodonNegra = crearPrenda(POLLERA, ALGODON, BLACK);
        RemeraDeAlgodonNegra = crearPrenda(REMERA, ALGODON, BLACK);
        MusculosaDeAlgodonNegra = crearPrenda(MUSCULOSA, ALGODON, BLACK);
        CamisaDeAlgodonNegra = crearPrenda(CAMISA, ALGODON, BLACK);
        PilotoNegro = crearPrenda(PILOTO, PLASTICO, BLACK);
        ZapatillasDeLonaNegras = crearPrenda(ZAPATILLAS, LONA, BLACK);
        BotasDeCueroNegras = crearPrenda(BOTAS, CUERO, BLACK);
        BufandaDeLana = crearPrenda(BUFANDA, LANA, BLACK);
    }

    protected Prenda crearPrenda(TipoPrenda tipo, Material material, Color color) {
        return new CreadorDePrenda()
                .setTipoPrenda(tipo)
                .setMaterial(material)
                .setColorPrincipal(color)
                .build();
    }

    protected Prenda crearPrenda(TipoPrenda tipo) {
        return crearPrenda(tipo, tipo.materialesValidos.iterator().next(), BLACK);
    }

    protected Usuario usuarioBasico() {
        return new Usuario();
    }

    protected Usuario usuarioConGuardarropas(Guardarropa... guardarropas) {
        Usuario usuario = new Usuario();
        Arrays.asList(guardarropas).forEach(usuario::agregarGuardarropa);
        return usuario;
    }

    protected Guardarropa guardarropaCon(Prenda... prendas) {
        Guardarropa guardarropa = new Guardarropa();
        Arrays.asList(prendas).forEach(guardarropa::agregarPrenda);
        return guardarropa;
    }

    protected Evento eventoBasico() {
        return new Evento("unEvento", Localizacion.CABA, new EventoUnico(LocalDateTime.now()), Anticipacion.UNA_HORA_ANTES);
    }

    protected CombinacionPrenda combinacionDe(Prenda... prendas) {
        return new CombinacionPrenda(Sets.newHashSet(Arrays.asList(prendas)));
    }

    protected Atuendo atuendoBasico() {
        CombinacionPrenda inferior = combinacionDe(JeanDeOxfordNegro);
        CombinacionPrenda superior = combinacionDe(RemeraDeAlgodonNegra);
        CombinacionPrenda calzado = combinacionDe(BotasDeCueroNegras);
        return new Atuendo(superior, inferior, calzado);
    }

}