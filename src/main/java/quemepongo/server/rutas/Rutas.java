package quemepongo.server.rutas;

import spark.template.handlebars.HandlebarsTemplateEngine;

// TODO ver si hay un mejor nombre
public abstract class Rutas {

    protected HandlebarsTemplateEngine templateEngine = new HandlebarsTemplateEngine();

    public abstract void registrar();

}