package quemepongo.server;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.EventoUnico;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.*;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioGuardarropa;
import quemepongo.persistencia.RepositorioUsuario;

import java.time.LocalDateTime;
import java.util.Arrays;

public class DataInicial implements WithGlobalEntityManager, TransactionalOps {

    private RepositorioGuardarropa repositorioGuardarropa = RepositorioGuardarropa.instancia();
    private RepositorioUsuario repositorioUsuario = RepositorioUsuario.instancia();

    public void cargar() {
        withTransaction(() -> {
            if (repositorioUsuario.getUsuarios().isEmpty()) {
                cargarUsuarios();
                cargarUsuarioConEvento();
            }
        });
    }

    private void cargarUsuarios() {
        repositorioUsuario.guardar(crearUsuario("usuario1", guardarropaInformal1(), guardarropaFormal1()));
        repositorioUsuario.guardar(crearUsuario("usuario2", guardarropaInformal2(), guardarropaFormal2()));
    }

    private void cargarUsuarioConEvento() {
        Usuario usuario = crearUsuario("usuario3", guardarropaInformal3());
        Evento evento = new Evento("Recital", Localizacion.CABA, new EventoUnico(LocalDateTime.now().plusDays(20)), Anticipacion.UNA_HORA_ANTES);
        evento.setSugerenciaAceptada(usuario.sugerencias(evento).stream().findAny().orElse(null));
        usuario.agregarEvento(evento);
        repositorioUsuario.guardar(usuario);
    }

    private Usuario crearUsuario(String nombre, Guardarropa... guardarropas) {
        Usuario usuario = new Usuario(nombre, "pass");
        Arrays.asList(guardarropas).forEach(usuario::agregarGuardarropa);
        return usuario;
    }

    private Guardarropa guardarropaInformal1() {
        return crearGuardarropa("Guardarropa Informal",
                crearPrenda("Buzo", ABRIGO, Material.ALGODON, Color.ROJO),
                crearPrenda("Jean", INFERIOR, Material.GABARDINA, Color.AZUL),
                crearPrenda("Musculosa", BASE, Material.ALGODON, Color.BLANCO),
                crearPrenda("Pantalón", INFERIOR, Material.ALGODON, Color.NEGRO),
                crearPrenda("Ojotas", CALZADO, Material.CUERO, Color.NEGRO)
        );
    }


    private Guardarropa guardarropaInformal2() {
        return crearGuardarropa("Guardarropa Informal",
                crearPrenda("Campera de cuero", ABRIGO, Material.CUERO, Color.MARRON),
                crearPrenda("Remera mangas cortas", BASE, Material.ALGODON, Color.NEGRO),
                crearPrenda("Jean", INFERIOR, Material.GABARDINA, Color.AZUL),
                crearPrenda("Zapatillas", CALZADO, Material.LONA, Color.VERDE),
                crearPrenda("Botas", CALZADO, Material.CUERO, Color.NEGRO)
        );
    }

    private Guardarropa guardarropaInformal3() {
        return crearGuardarropa("Guardarropa Informal",
                crearPrenda("Campera", ABRIGO, Material.CUERO, Color.MARRON),
                crearPrenda("Polera", BASE, Material.ALGODON, Color.NEGRO),
                crearPrenda("Jean", INFERIOR, Material.GABARDINA, Color.AZUL),
                crearPrenda("Zapatillas", CALZADO, Material.LONA, Color.VERDE)
        );
    }

    private Guardarropa guardarropaFormal1() {
        return crearGuardarropa("Guardarropa Formal",
                crearPrenda("Sweater", ABRIGO, Material.LANA, Color.NEGRO),
                crearPrenda("Camisa", BASE, Material.LINO, Color.BLANCO),
                crearPrenda("Pantalón", INFERIOR, Material.GABARDINA, Color.AZUL),
                crearPrenda("Zapatos", CALZADO, Material.CUERO, Color.NEGRO)
        );
    }


    private Guardarropa guardarropaFormal2() {
        return crearGuardarropa("Guardarropa Formal",
                crearPrenda("Sweater", ABRIGO, Material.LANA, Color.BEIGE),
                crearPrenda("Blusa", BASE, Material.SEDA, Color.BLANCO),
                crearPrenda("Pantalón", INFERIOR, Material.GABARDINA, Color.NEGRO),
                crearPrenda("Botas", CALZADO, Material.CUERO, Color.NEGRO)
        );
    }

    private Guardarropa crearGuardarropa(String nombre, Prenda... prendas) {
        Guardarropa guardarropa = new Guardarropa(nombre);
        Arrays.asList(prendas).forEach(guardarropa::agregarPrenda);
        repositorioGuardarropa.guardar(guardarropa);
        return guardarropa;
    }

    private Prenda crearPrenda(String nombre, TipoPrenda tipo, Material material, Color color) {
        return new CreadorDePrenda()
                .setNombre(nombre)
                .setTipoPrenda(tipo)
                .setMaterial(material)
                .setColorPrincipal(color)
                .build();
    }

    private static final TipoPrenda ABRIGO = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(50));
    private static final TipoPrenda BASE = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));
    private static final TipoPrenda INFERIOR = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(10));
    private static final TipoPrenda CALZADO = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));
}
