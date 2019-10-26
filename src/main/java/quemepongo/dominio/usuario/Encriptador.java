package quemepongo.dominio.usuario;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Clase utilizada para encriptar Strings utilizando MD5
 */
public class Encriptador {

    public static String encriptar(String source) {
        try {
            MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Algoritmo de encriptación
            mdEnc.update(source.getBytes(), 0, source.length());
            String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
            return md5;
        } catch (Exception ex) {
            return null;
        }
    }

}
