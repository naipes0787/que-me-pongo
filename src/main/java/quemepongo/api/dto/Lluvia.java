package quemepongo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lluvia {

    @JsonProperty("1h")
    private Double unaHora;

    @JsonProperty("3h")
    private Double tresHoras;

    public Double getUnaHora() {
        return unaHora;
    }

    public void setUnaHora(Double unaHora) {
        this.unaHora = unaHora;
    }

    public Double getTresHoras() {
        return tresHoras;
    }

    public void setTresHoras(Double tresHoras) {
        this.tresHoras = tresHoras;
    }
}
