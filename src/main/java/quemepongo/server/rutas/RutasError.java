package quemepongo.server.rutas;

import spark.ModelAndView;

import java.util.HashMap;

import static spark.Spark.get;

public class RutasError extends Rutas {

    @Override
    public void registrar() {
        get("/error", (req, res) -> new ModelAndView(new HashMap<String, Object>(), "error.hbs"), templateEngine);
    }
}
