package com.attendance.system.api.exceptions;

import org.springframework.http.HttpStatus; // Importación para manejar estados HTTP.
import org.springframework.http.ResponseEntity; // Para construir respuestas HTTP.
import org.springframework.security.access.AccessDeniedException; // Excepción de acceso denegado.
import org.springframework.web.bind.annotation.ControllerAdvice; // Para manejar excepciones globalmente.
import org.springframework.web.bind.annotation.ExceptionHandler; // Para definir métodos que manejan excepciones.

@ControllerAdvice(basePackages = "com.attendance.system.api") // Indica que esta clase manejará excepciones en el paquete especificado.
public class ApiExceptionHandler {

    // Maneja excepciones de acceso denegado.
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        // Crea una respuesta de error con mensaje y estado HTTP UNAUTHORIZED (401).
        return new ResponseEntity<>(new ApiErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    // Maneja excepciones de tipo ApiException.
    @SuppressWarnings("null") // Suprime advertencias sobre posibles valores nulos.
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
        // Retorna la respuesta de error y su estado HTTP asociado.
        return new ResponseEntity<>(ex.getErrorResponse(), ex.getErrorResponse().getStatus());
    }

    // Maneja excepciones genéricas.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        // Retorna un mensaje de error genérico con estado HTTP INTERNAL_SERVER_ERROR (500).
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
