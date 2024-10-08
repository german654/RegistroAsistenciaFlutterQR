package com.attendance.system.api.exceptions;

import org.springframework.http.HttpStatus; // Importación para manejar estados HTTP.

import lombok.AllArgsConstructor; // Para generar un constructor que acepta todos los campos.
import lombok.Data; // Para generar automáticamente métodos getters, setters, toString, equals y hashCode.

@Data // Anotación que genera automáticamente métodos getters, setters, toString, equals y hashCode.
@AllArgsConstructor // Genera un constructor que acepta todos los campos como parámetros.
public class ApiErrorResponse {

	// Campo que contiene un mensaje descriptivo sobre el error.
	private String message;

	// Campo que representa el estado HTTP asociado con el error.
	private HttpStatus status;
}
