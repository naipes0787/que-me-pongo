package quemepongo.servicio.clima.dto;

public class AccuweatherResponseDTO {

    private String LocalObservationDateTime;
    private TemperaturaAccuWeather Temperature;

    public String getLocalObservationDateTime() {
        return LocalObservationDateTime;
    }

    public void setLocalObservationDateTime(String localObservationDateTime) {
        LocalObservationDateTime = localObservationDateTime;
    }

    public TemperaturaAccuWeather getTemperature() {
        return Temperature;
    }

    public void setTemperature(TemperaturaAccuWeather temperature) {
        Temperature = temperature;
    }

}

