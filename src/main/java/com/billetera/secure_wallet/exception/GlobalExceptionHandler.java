package com.billetera.secure_wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Manejador global para excepciones de negocio y validación.
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> manejarErroresDeNegocio(RuntimeException ex) {


        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", System.currentTimeMillis());
        respuesta.put("status", 400);
        respuesta.put("error", "Bad Request (Petición Incorrecta)");
        respuesta.put("message", ex.getMessage());


        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
}