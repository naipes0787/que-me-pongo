package quemepongo.api.dto;

public class OpenWeatherResponseDTO {

    private TemperaturaOpenWeather main;
    private Long dt;

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
