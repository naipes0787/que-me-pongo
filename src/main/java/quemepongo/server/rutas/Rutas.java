package quemepongo.server.rutas;

import spark.template.handlebars.HandlebarsTemplateEngine;

public abstract class Rutas {

    protected HandlebarsTemplateEngine templateEngine = new HandlebarsTemplateEngine();

    public abstract void registrar();

}