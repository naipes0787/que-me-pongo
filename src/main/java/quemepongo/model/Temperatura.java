package quemepongo.model;

public class Temperatura {

    private Double temperatura;

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public double nivelDeAbrigo(){
        if(getTemperatura() < 0)  {return 200;}
        if (getTemperatura() < 35) {return 200 - 5 * getTemperatura();}
        else {return 25;}
    }
}

