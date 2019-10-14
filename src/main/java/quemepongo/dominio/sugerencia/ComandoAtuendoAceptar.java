package quemepongo.dominio.sugerencia;

public class ComandoAtuendoAceptar extends ComandoAtuendo {

    public ComandoAtuendoAceptar(Atuendo atuendo){
        this.atuendo = atuendo;
    }

    @Override
    public void ejecutar(){
        if(this.atuendo.getEstado().equals(EstadoAtuendo.NUEVO)) {
            this.atuendo.setEstado(EstadoAtuendo.ACEPTADO);
            this.atuendo.setUltimoComando(this);
        }
    }

    @Override
    public void deshacer(){
        this.atuendo.setEstado(EstadoAtuendo.NUEVO);
    }

}
