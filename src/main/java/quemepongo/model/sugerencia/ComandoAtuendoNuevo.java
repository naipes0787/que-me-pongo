package quemepongo.model.sugerencia;

/** Comando utilizado por default en los nuevos atuendos */
public class ComandoAtuendoNuevo extends ComandoAtuendo {

    public ComandoAtuendoNuevo(Atuendo atuendo){
        this.atuendo = atuendo;
    }

    @Override
    public void ejecutar(){
        this.atuendo.setUltimoComando(this);
    }

    @Override
    public void deshacer(){

    }

}
