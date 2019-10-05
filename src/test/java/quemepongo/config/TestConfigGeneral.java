package quemepongo.config;

import com.google.common.collect.Sets;
import javafx.scene.paint.Color;
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

public abstract class TestConfigGeneral {

    protected static final double NIVEL_ABRIGO_DEFAULT = 50;
    protected static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(NIVEL_ABRIGO_DEFAULT));
    protected static final TipoPrenda POLLERA = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(NIVEL_ABRIGO_DEFAULT));
    protected static final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(NIVEL_ABRIGO_DEFAULT));
    protected static final TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(NIVEL_ABRIGO_DEFAULT));
    protected static final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(NIVEL_ABRIGO_DEFAULT));
    protected static final TipoPrenda PILOTO = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(NIVEL_ABRIGO_DEFAULT), Sets.newHashSet(FactorClimatico.LLUVIA));
    protected static final TipoPrenda ZAPATILLAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(NIVEL_ABRIGO_DEFAULT));
    protected static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(NIVEL_ABRIGO_DEFAULT));
    protected static final TipoPrenda BUFANDA = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorioCuello());

    protected static final Prenda JeanDeOxfordNegro = crearPrenda(JEAN, OXFORD, BLACK);
    protected static final Prenda PolleraDeAlgodonNegra = crearPrenda(POLLERA, ALGODON, BLACK);
    protected static final Prenda RemeraDeAlgodonNegra = crearPrenda(REMERA, ALGODON, BLACK);
    protected static final Prenda MusculosaDeAlgodonNegra = crearPrenda(MUSCULOSA, ALGODON, BLACK);
    protected static final Prenda CamisaDeAlgodonNegra = crearPrenda(CAMISA, ALGODON, BLACK);
    protected static final Prenda PilotoNegro = crearPrenda(PILOTO, PLASTICO, BLACK);
    protected static final Prenda ZapatillasDeLonaNegras = crearPrenda(ZAPATILLAS, LONA, BLACK);
    protected static final Prenda BotasDeCueroNegras = crearPrenda(BOTAS, CUERO, BLACK);
    protected static final Prenda BufandaDeLana = crearPrenda(BUFANDA, LANA, BLACK);

    protected static Prenda crearPrenda(TipoPrenda tipo, Material material, Color color) {
        return new CreadorDePrenda()
                .setTipoPrenda(tipo)
                .setMaterial(material)
                .setColorPrincipal(color)
                .build();
    }

    protected static Prenda crearPrenda(TipoPrenda tipo) {
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