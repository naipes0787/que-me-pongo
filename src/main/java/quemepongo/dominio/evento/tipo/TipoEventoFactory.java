package quemepongo.dominio.evento.tipo;

import quemepongo.excepcion.TipoEventoInvalidoException;

/**
 * Factory para crear un TipoEvento a partir de un string con el tipo de evento correspondiente
 */
public final class TipoEventoFactory {

    public final static String UNICO = "UNICO";
    public final static String REPETITIVO = "REPETITIVO";

    public static TipoEvento getTipoEvento(String tipoEvento){
        if (TipoEventoFactory.REPETITIVO.equals(tipoEvento)) {
            return new EventoRepetitivo();
        } else {
            if (TipoEventoFactory.UNICO.equals(tipoEvento)) {
                return new EventoUnico();
            }
        }
        throw new TipoEventoInvalidoException();
    }

}
