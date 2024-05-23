package com.corellana.bookStore.Validator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidatorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>>handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();

        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.append(error.getDefaultMessage()).append(". ");
        });

        response.put("success", false);
        response.put("error", 400);
        response.put("message", errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

/*     // Manejo de excepciones para parámetro faltante
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", 400);
        response.put("message", "Parámetro faltante: " + ex.getParameterName());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        //return new ResponseEntity<>("Parámetro faltante: " + ex.getParameterName(), HttpStatus.BAD_REQUEST);
    } */
}
