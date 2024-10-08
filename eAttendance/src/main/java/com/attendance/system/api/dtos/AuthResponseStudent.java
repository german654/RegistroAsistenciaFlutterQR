package com.attendance.system.api.dtos;

// Importaciones necesarias
import java.util.Date; // Para manejar fechas y horas.
import com.attendance.system.model.Batch; // Importación de la clase Batch que representa un grupo de estudiantes.
import com.attendance.system.model.Course; // Importación de la clase Course que representa un curso.

import lombok.AllArgsConstructor; // Para generar un constructor que acepta todos los campos.
import lombok.Builder; // Para implementar el patrón de diseño Builder.
import lombok.Data; // Para generar automáticamente métodos getters, setters, toString, equals y hashCode.

@Data // Anotación que genera automáticamente métodos getters, setters, toString, equals y hashCode.
@AllArgsConstructor // Genera un constructor que acepta todos los campos como parámetros.
@Builder // Permite utilizar el patrón Builder para crear instancias de esta clase de manera legible y sencilla.
public class AuthResponseStudent {

	// Campo que representa el token de autenticación generado para el estudiante.
	private final String token;

	// Campo que representa la fecha y hora de expiración del token.
	private final Date expiry;

	// Campo que representa el nombre de usuario del estudiante.
	private final String username;

	// Campo que representa el correo electrónico del estudiante.
	private final String email;

	// Campo que representa la matrícula del estudiante.
	private final String enrollment;

	// Campo que representa la división del estudiante dentro de su curso (ej. "A", "B", etc.).
	private final String division;

	// Campo que representa el curso al que está inscrito el estudiante.
	private final Course course; // Utiliza la clase Course para encapsular detalles del curso.

	// Campo que representa el grupo o "batch" al que pertenece el estudiante.
	private final Batch batch; // Utiliza la clase Batch para encapsular detalles del grupo.

	// Campo que representa el ID único del estudiante en el sistema.
	private final Long studentId;

}
