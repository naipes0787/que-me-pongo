package quemepongo.model.sugerencia;

public class ComandoAtuendoAceptar extends ComandoAtuendo {

    public ComandoAtuendoAceptar(Atuendo atuendo){
        this.atuendo = atuendo;
    }

    @Override
    public void ejecutar(){
        this.atuendo.setEstado(EstadoAtuendo.ACEPTADO);
    }

    @Override
    public void deshacer(){
        this.atuendo.setEstado(EstadoAtuendo.NUEVO);
    }

}
