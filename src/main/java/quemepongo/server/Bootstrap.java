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

public class Bootstrap implements WithGlobalEntityManager, TransactionalOps {

    private RepositorioGuardarropa repositorioGuardarropa = RepositorioGuardarropa.instancia();
    private RepositorioUsuario repositorioUsuario = RepositorioUsuario.instancia();

    public static void main(String[] args) {
        new Bootstrap().ejecutar();
    }

    private void ejecutar() {
        withTransaction(() -> {
            Guardarropa guardarropa = cargarGuardarropa();
            cargarUsuarios(guardarropa);
//          cargarUsuarioConEvento(guardarropa);
        });
    }

    private Guardarropa cargarGuardarropa() {
        Guardarropa guardarropa = new Guardarropa("Guardarropa informal");
        guardarropa.agregarPrenda(new CreadorDePrenda()
                .setNombre("Campera de cuero")
                .setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(50)))
                .setMaterial(Material.CUERO)
                .setColorPrincipal(Color.MARRON)
//                .setUrlFoto("/home/utn/Desktop/test-foto.jpg")
                .build()
        );
        guardarropa.agregarPrenda(new CreadorDePrenda()
                .setNombre("Remera mangas cortas")
                .setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10)))
                .setMaterial(Material.ALGODON)
                .setColorPrincipal(Color.NEGRO)
                .build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
                .setNombre("Jean")
                .setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoInferior(10)))
                .setMaterial(Material.GABARDINA)
                .setColorPrincipal(Color.AZUL)
                .build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
                .setNombre("Zapatillas")
                .setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10)))
                .setMaterial(Material.LONA)
                .setColorPrincipal(Color.VERDE)
                .build());
        repositorioGuardarropa.guardar(guardarropa);
        return guardarropa;
    }

    private void cargarUsuarioConEvento(Guardarropa guardarropa) {
        Usuario usuario1 = new Usuario("usuario1", "pass");
        usuario1.agregarGuardarropa(guardarropa);
        Evento evento = new Evento("Recital", Localizacion.CABA, new EventoUnico(LocalDateTime.now().plusDays(20)), Anticipacion.UNA_HORA_ANTES);
        evento.setAtuendo(usuario1.sugerencias(evento).stream().findAny().orElse(null));
        usuario1.agregarEvento(evento);
        repositorioUsuario.guardar(usuario1);
    }

    private void cargarUsuarios(Guardarropa guardarropa) {
        Usuario usuario1 = new Usuario("usuario1", "pass");
        usuario1.agregarGuardarropa(guardarropa);
        Usuario usuario2 = new Usuario("usuario2", "pass");
        usuario2.agregarGuardarropa(guardarropa);
        repositorioUsuario.guardar(usuario1);
        repositorioUsuario.guardar(usuario2);
    }

}
