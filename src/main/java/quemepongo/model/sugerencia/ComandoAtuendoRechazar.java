package quemepongo.model.sugerencia;

public class ComandoAtuendoRechazar extends ComandoAtuendo {

    public ComandoAtuendoRechazar(Atuendo atuendo){
        this.atuendo = atuendo;
    }

    @Override
    public void ejecutar(){
        this.atuendo.setEstado(EstadoAtuendo.RECHAZADO);
    }

    @Override
    public void deshacer(){
        this.atuendo.setEstado(EstadoAtuendo.NUEVO);
    }

}
