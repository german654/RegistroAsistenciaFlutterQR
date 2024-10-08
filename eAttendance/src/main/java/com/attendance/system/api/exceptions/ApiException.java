package com.attendance.system.api.exceptions;

import lombok.Getter; // Para generar automáticamente métodos getter.
import java.io.Serial; // Importación para manejar la serialización.

@Getter // Anotación que genera métodos getter automáticamente para todos los campos.
public class ApiException extends RuntimeException {
    /**
     * Serial ID para la clase ApiException, utilizado en la serialización.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    // Campo que representa la respuesta de error asociada con esta excepción.
    private final ApiErrorResponse errorResponse;

    // Constructor que acepta una respuesta de error como parámetro.
    public ApiException(ApiErrorResponse errorResponse) {
        this.errorResponse = errorResponse; // Inicializa el campo errorResponse.
    }
}
