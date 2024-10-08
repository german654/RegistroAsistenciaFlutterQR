package com.attendance.system.api.dtos;

// Importaciones necesarias
import java.util.Date; // Para manejar fechas y horas.

import lombok.AllArgsConstructor; // Para generar un constructor que acepta todos los campos.
import lombok.Builder; // Para implementar el patrón de diseño Builder.
import lombok.Data; // Para generar automáticamente métodos getters, setters, toString, equals y hashCode.

@Data // Anotación que genera automáticamente métodos getters, setters, toString, equals y hashCode.
@AllArgsConstructor // Genera un constructor que acepta todos los campos como parámetros.
@Builder // Permite utilizar el patrón Builder para crear instancias de esta clase de manera legible y sencilla.
public class AuthResponseFaculty {

	// Campo que representa el token de autenticación generado para el usuario.
	private final String token;

	// Campo que representa la fecha y hora de expiración del token.
	private final Date expiry;

	// Campo que representa el nombre de usuario del docente.
	private final String username;

	// Campo que representa el correo electrónico del docente.
	private final String email;

	// Campo que representa la matrícula del docente (opcional, dependiendo de la lógica del sistema).
	private final String enrollment;

	// Campo que representa el ID único del docente en el sistema.
	private final Long facultyId;

}
