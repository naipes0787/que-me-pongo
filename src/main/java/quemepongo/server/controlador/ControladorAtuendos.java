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
        if (this.sinAtuendos()) {
            this.setAtuendos(req);
        }
        return new ModelAndView(this.darAtuendo(req), "atuendoSugerido.hbs");
    }

    private boolean verificarSiYaAceptoAtuendo(Request req) {
        return obtenerEvento(req).tieneSugerenciaAceptada();
    }

    public Void aceptarAtuendo(Request req, Response res){
        Evento evento = obtenerEvento(req);
        evento.setSugerenciaAceptada(atuendos.get(atuendoAMostrar));
        res.redirect(RutasConstantes.EVENTO_URL);
        return null;
    }

    private Boolean sinAtuendos(){
        return false //Aca me falta recuperar atributo atuendos y ver si existe / tiene algo
    }

    private void setAtuendos(Request req) {
        Usuario user = obtenerUsuario(req);
        Evento evento = obtenerEvento(req);
        req.session().attribute("atuendos", new ArrayList<Atuendo>(user.sugerencias(evento)));
    }

    private Usuario obtenerUsuario(Request req){
        String username = req.session().attribute("user");
        return RepositorioUsuario.instancia().getUsuarioByUsername(username);
    }

    private Evento obtenerEvento(Request req){
        Long id = Long.valueOf(req.params("id"));
        return RepositorioEvento.instancia().get(id);
    }

    private Atuendo darAtuendo(Request req){
        String mover = req.queryParams("mover");
//Aca voy a tener que mover para atras o adelante un atributo que me vaya manteniendo el puntero del array.
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

//y aca obtener el atuendo que corresponda según la posición.
        return atuendos.get(atuendoAMostrar);
    }



}
