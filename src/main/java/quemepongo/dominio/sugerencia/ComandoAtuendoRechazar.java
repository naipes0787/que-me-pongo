package quemepongo.dominio.sugerencia;

public class ComandoAtuendoRechazar extends ComandoAtuendo {

    public ComandoAtuendoRechazar(Atuendo atuendo){
        this.atuendo = atuendo;
    }

    @Override
    public void ejecutar(){
        if(this.atuendo.getEstado().equals(EstadoAtuendo.NUEVO)) {
            this.atuendo.setEstado(EstadoAtuendo.RECHAZADO);
            this.atuendo.setUltimoComando(this);
        }
    }

    @Override
    public void deshacer(){
        this.atuendo.setEstado(EstadoAtuendo.NUEVO);
    }

}
