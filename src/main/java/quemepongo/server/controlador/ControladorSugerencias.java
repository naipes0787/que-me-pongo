package quemepongo.server.controlador;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.sugerencia.Atuendo;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.excepcion.ControladorException;
import quemepongo.persistencia.RepositorioEvento;
import quemepongo.server.controlador.sugerencias.VistaSugerencia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;

import static quemepongo.server.rutas.RutasConstantes.EVENTO_URL;
import static quemepongo.util.RequestUtils.obtenerUsuario;
import static quemepongo.util.RequestUtils.parsearId;

public class ControladorSugerencias {

    public ModelAndView obtenerAtuendos(Request req, Response res) {
        Evento evento = this.obtenerEvento(req);
        //Si ya está aceptado, no se le sugiere nada y vuelve a la pantalla de evento
        if (this.verificarSiYaAceptoAtuendo(req)){
            return new ModelAndView(evento, "evento_particular.hbs");
        }
        //Si aún no se ingresó en la session el listado de atuendos, se setea.
        if (this.sinAtuendos(req)) {
            this.setAtuendos(req);
        }

        //obtiene el correspondiente y lo muestra
        Atuendo atuendoAMostrar = darAtuendo(req);
        int indiceActual = getIndiceAtuendoAMostrar(req);
        return new ModelAndView(
                new VistaSugerencia(
                        indiceActual,
                        atuendoAMostrar,
                        evento,
                        indiceActual < getAtuendos(req).size() - 1), "sugerencias.hbs");
    }

    public Void aceptarAtuendo(Request req, Response res){
        Evento evento = obtenerEvento(req);
        Atuendo atuendo = getAtuendoActual(req);
        atuendo.aceptar();
        evento.setSugerenciaAceptada(atuendo);
        res.redirect(EVENTO_URL.replace(":id", evento.getId().toString()));
        return null;
    }

    private Atuendo darAtuendo(Request req){
        int atuendoAMostrar = getIndiceAtuendoAMostrar(req);

        if (req.queryParams("siguiente") != null && atuendoAMostrar < getAtuendos(req).size() - 1) {
            atuendoAMostrar++;
        } else {
            atuendoAMostrar = 0;
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
        req.session().attribute(armarClaveAtuendos(req), new ArrayList<>(user.sugerencias(evento)));
        setAtuendoAMostrar(req, 0);
    }

    private Atuendo getAtuendoActual(Request req) {
        return getAtuendos(req).get(getIndiceAtuendoAMostrar(req));
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

    private int getIndiceAtuendoAMostrar(Request req){
        return req.session().attribute(armarClaveAtuendoAMostrar(req));
    }

    private String armarClaveAtuendoAMostrar(Request req){
        return "atuendoAMostrar" + obtenerEvento(req).getId();
    }

    private Evento obtenerEvento(Request req){
        return RepositorioEvento.instancia().buscarEvento(parsearId(req))
                .orElseThrow(() -> new ControladorException("Evento " + parsearId(req) + " no encontrado"));
    }
}
