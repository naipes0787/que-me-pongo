package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorHome;

import static spark.Spark.get;

public class RutasHome extends Rutas {

    private ControladorHome controlador = new ControladorHome();

    @Override
    public void registrar() {

        get(RutasConstantes.HOME_URL,
                controlador::homepage,
                templateEngine);

    }
}
