package br.com.wefin.wefinapp.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {

    @JsonProperty("message")
    private String message;

}

