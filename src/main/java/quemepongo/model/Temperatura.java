package quemepongo.model;

public class Temperatura {

    private Double temperatura;

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Método para hacer una asociación entre temperatura y el nivel
     * de abrigo que se precisa para dicha temperatura. Básicamente, para
     * - Si la temperatura es bajo cero, el nivel de abrigo óptimo sería de 200
     * - Si la temperatura está entre 0°C y 35°C, el nivel de abrigo óptimo será de 200-5*temperatura
     * - Si la temperatura es mayor a 35°C, el nivel de abrigo óptimo será de 25
     * 
     * @return nivelDeAbrigo {@link Double}
     */
    public Double convertirANivelDeAbrigo(){
        if(getTemperatura() < 0)  {
        	return 200D;
        } else {
        	if (getTemperatura() < 35) {
        		return 200 - 5 * getTemperatura();
        	} else {
        		return 25D;
    		}
        }
        
    }
}

