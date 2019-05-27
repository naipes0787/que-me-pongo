package quemepongo.api.dto;

public class AccuweatherResponseDTO {

    private String LocalObservationDateTime;
    private Temperatura Temperature;

    public String getLocalObservationDateTime() {
        return LocalObservationDateTime;
    }

    public void setLocalObservationDateTime(String localObservationDateTime) {
        LocalObservationDateTime = localObservationDateTime;
    }

    public Temperatura getTemperature() {
        return Temperature;
    }

    public void setTemperature(Temperatura temperature) {
        Temperature = temperature;
    }

}

