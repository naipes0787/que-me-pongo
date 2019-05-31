package quemepongo.model;

import java.util.Set;

/**
 * Interfaz que provee el contrato para las clases que se encarguen de la
 * superposición de prendas
 */
public interface CombinadorSuperposicion {
	
    Set<CombinacionPrenda> combinar();
    
}
