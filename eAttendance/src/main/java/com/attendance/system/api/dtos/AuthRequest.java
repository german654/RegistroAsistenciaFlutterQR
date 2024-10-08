package com.attendance.system.api.dtos;

// Importaciones de Lombok para la generación automática de código.
import lombok.AllArgsConstructor; // Para generar un constructor que acepta todos los campos.
import lombok.Builder; // Para implementar el patrón de diseño Builder.
import lombok.Data; // Para generar métodos getters, setters, toString, equals y hashCode.
import lombok.NoArgsConstructor; // Para generar un constructor sin parámetros.

@Data // Anotación que genera automáticamente métodos getters, setters, toString, equals y hashCode.
@Builder // Permite utilizar el patrón Builder para crear instancias de esta clase de manera legible y sencilla.
@NoArgsConstructor // Genera un constructor sin parámetros.
@AllArgsConstructor // Genera un constructor que acepta todos los campos como parámetros.
public class AuthRequest {

	// Campo que representa el nombre de usuario del usuario que intenta autenticarse.
	private String username;

	// Campo que representa la contraseña del usuario que intenta autenticarse.
	private String password;
}
