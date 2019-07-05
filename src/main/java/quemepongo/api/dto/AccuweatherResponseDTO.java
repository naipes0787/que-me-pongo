package quemepongo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccuweatherResponseDTO {

    private String LocalObservationDateTime;
    private TemperaturaAccuWeather Temperature;
    private Boolean HasPrecipitation;
    private Integer UVIndex;
    private VientoAccuWeather Wind;

    public VientoAccuWeather getWind() {
        return Wind;
    }

    public void setWind(VientoAccuWeather wind) {
        Wind = wind;
    }

    public Boolean getHasPrecipitation() {
        return HasPrecipitation;
    }

    public void setHasPrecipitation(Boolean hasPrecipitation) {
        HasPrecipitation = hasPrecipitation;
    }

    public Integer getUVIndex() {
        return UVIndex;
    }

    @JsonProperty("UVIndex")
    public void setUVIndex(Integer UVIndex) {
        this.UVIndex = UVIndex;
    }

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

