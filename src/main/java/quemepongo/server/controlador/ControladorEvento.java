package quemepongo.server.controlador;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.TipoEvento;
import quemepongo.dominio.evento.tipo.TipoEventoFactory;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioEvento;
import quemepongo.persistencia.RepositorioUsuario;
import quemepongo.server.rutas.RutasConstantes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class ControladorEvento extends ControladorAutenticado{

    public ModelAndView getEventoPage(Request request, Response response) {
        this.autenticar(request, response);
        return new ModelAndView(new HashMap<>(), "eventos.hbs");
    }

    public Void guardarEvento(Request request, Response response) {
        this.autenticar(request, response);
        String titulo = request.queryParams("titulo");
        String tipoString = request.queryParams("tipo");
        String anticipacionString = request.queryParams("anticipacion");
        String lugarString = request.queryParams("lugar");
        Anticipacion anticipacion = new Anticipacion(ChronoUnit.DAYS, Integer.valueOf(anticipacionString));
        TipoEvento tipoEvento = TipoEventoFactory.getTipoEvento(tipoString);
        Evento evento = new Evento(titulo, Localizacion.valueOf(lugarString), tipoEvento, anticipacion);
        String username = request.session().attribute("user");
        Usuario usuario = RepositorioUsuario.instancia().getUsuarioByUsername(username);
        usuario.agregarEvento(evento);
        RepositorioEvento.instancia().guardar(evento);
        response.redirect(RutasConstantes.HOME_URL);
        return null;
    }

}
