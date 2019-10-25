package quemepongo.server.controlador;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.sugerencia.Atuendo;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioEvento;
import quemepongo.persistencia.RepositorioGuardarropa;
import quemepongo.persistencia.RepositorioUsuario;
import quemepongo.server.rutas.RutasConstantes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControladorAtuendos extends ControladorAutenticado{

    public ModelAndView obtenerAtuendos(Request req, Response res) {
        //Si ya está aceptado, no se le sugiere nada y vuelve a la pantalla de evento
        if (this.verificarSiYaAceptoAtuendo(req)){
            return new ModelAndView(this.obtenerEvento(req), "evento.hbs");
        }
        //Si aún no se ingresó en la session el listado de atuendos, se setea.
        if (this.sinAtuendos(req)) {
            this.setAtuendos(req);
        }
        //obtiene el correspondiente y lo muestra
        return new ModelAndView(this.darAtuendo(req), "atuendoSugerido.hbs");
    }

    public Void aceptarAtuendo(Request req, Response res){
        Evento evento = obtenerEvento(req);
        evento.setSugerenciaAceptada(darAtuendo(req));
        res.redirect(RutasConstantes.EVENTO_URL);
        return null;
    }
    private Atuendo darAtuendo(Request req){
        int atuendoAMostrar = getAtuendosAMostrar(req);

        //Ver como se puede hacer más lindo.
        //Los botones Siguiente y Anterior tienen que setear algo
        // que acá nos sirva para determinar si suma o resta
        String mover = req.queryParams("mover");
        if (mover != null) {
            switch (mover) {
                case "siguiente":
                    atuendoAMostrar++;
                    break;
                case "anterior":
                    atuendoAMostrar--;
                    break;
            }
        }
        setAtuendoAMostrar(req, atuendoAMostrar);
        return getAtuendos(req).get(atuendoAMostrar);
    }

    private boolean verificarSiYaAceptoAtuendo(Request req) {
        return obtenerEvento(req).tieneSugerenciaAceptada();
    }

    private Boolean sinAtuendos(Request req){
        return getAtuendos(req) == null;
    }

    private void setAtuendos(Request req) {
        Usuario user = obtenerUsuario(req);
        Evento evento = obtenerEvento(req);
        req.session().attribute(armarClaveAtuendos(req), new ArrayList<Atuendo>(user.sugerencias(evento)));
        setAtuendoAMostrar(req, 0);
    }

    private ArrayList<Atuendo> getAtuendos(Request req){
        return req.session().attribute(armarClaveAtuendos(req));
    }

    private String armarClaveAtuendos(Request req){
        return "atuendo" + obtenerEvento(req).getId();
    }
    private void setAtuendoAMostrar(Request req, int i){
        req.session().attribute(armarClaveAtuendoAMostrar(req), i);
    }

    private int getAtuendosAMostrar(Request req){
        return req.session().attribute(armarClaveAtuendoAMostrar(req));
    }

    private String armarClaveAtuendoAMostrar(Request req){
        return "atuendoAMostrar" + obtenerEvento(req).getId();
    }

    private Usuario obtenerUsuario(Request req){
        String username = req.session().attribute("user");
        return RepositorioUsuario.instancia().getUsuarioByUsername(username);
    }

    private Evento obtenerEvento(Request req){
        Long id = Long.valueOf(req.params("id"));
        return RepositorioEvento.instancia().get(id);
    }
}
