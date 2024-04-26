package br.com.wefin.wefinapp.config;

public class ResourceNotFoundException extends RuntimeException {
    private String code;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
