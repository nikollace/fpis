package fon.bg.ac.rs.fpis.trunks.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponse {

    @JsonProperty("message")
    private String message;

    public GenericResponse(String message) {
        this.message = message;
    }
}
