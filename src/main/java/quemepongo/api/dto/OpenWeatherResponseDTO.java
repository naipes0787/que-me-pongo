package quemepongo.api.dto;

public class OpenWeatherResponseDTO {

    private TemperaturaOpenWeather main;
    private Long dt;
    private Viento wind;
    private Nubes clouds;
    private Lluvia rain;

    public Viento getWind() {
        return wind;
    }

    public void setWind(Viento wind) {
        this.wind = wind;
    }

    public Nubes getClouds() {
        return clouds;
    }

    public void setClouds(Nubes clouds) {
        this.clouds = clouds;
    }

    public Lluvia getRain() {
        return rain;
    }

    public void setRain(Lluvia rain) {
        this.rain = rain;
    }

    public TemperaturaOpenWeather getMain() {
        return main;
    }

    public void setMain(TemperaturaOpenWeather main) {
        this.main = main;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }
}
