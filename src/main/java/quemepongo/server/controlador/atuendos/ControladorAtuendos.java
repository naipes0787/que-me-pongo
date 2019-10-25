package quemepongo.server.controlador.atuendos;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Set;
import java.util.stream.Collectors;

public class ControladorAtuendos {

    public ModelAndView mostrarAtuendos(Request req, Response res) {
        String username = req.session().attribute("user");
        Usuario usuario = RepositorioUsuario.instancia().getUsuarioByUsername(username);
        return new ModelAndView(crearVista(usuario), "atuendos.hbs");
    }

    private Set<VistaAtuendo> crearVista(Usuario usuario) {
        return usuario.getEventos().stream()
                .filter(Evento::tieneAtuendo)
                .map(evento -> new VistaAtuendo(evento.getAtuendo(), evento))
                .collect(Collectors.toSet());
    }

}
