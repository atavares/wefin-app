package br.com.wefin.wefinapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorValidationDTO {

    private Integer code;
    private String field;
    private String message;
}
