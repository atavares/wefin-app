package br.com.wefin.wefinapp.config;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorValidationDTO> handleValidationField(MethodArgumentNotValidException exception){

        return exception.getBindingResult().getFieldErrors().stream()
                                            .map(fieldError -> new ErrorValidationDTO(HttpStatus.BAD_REQUEST.value(),
                                                                                      fieldError.getField(),
                                                                                      fieldError.getDefaultMessage()))
                                            .collect(Collectors.toList());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e){
        return new ResponseEntity<>(ExceptionResponse.builder()
            .message(e.getMessage())
            .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdentificadorException.class)
    public ResponseEntity<Object> handleResourceNotFound(IdentificadorException e){
        return new ResponseEntity<>(ExceptionResponse.builder()
            .message(e.getMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }


}
