package quemepongo.dominio.sugerencia;

public abstract class ComandoAtuendo {

    protected Atuendo atuendo;

    public abstract void ejecutar();
    public abstract void deshacer();

}
