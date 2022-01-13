package fon.bg.ac.rs.fpis.trunks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Jwt {

    @JsonProperty("token")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Jwt{" +
                "value='" + value + '\'' +
                '}';
    }
}
