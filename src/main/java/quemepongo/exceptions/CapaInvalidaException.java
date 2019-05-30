package quemepongo.exceptions;

import quemepongo.model.Capa;

public class CapaInvalidaException extends RuntimeException{
    public CapaInvalidaException(Capa capa){
        super("La capa " + capa.toString() + "no es válido para el Fabricador de Tipo");
    }
}



