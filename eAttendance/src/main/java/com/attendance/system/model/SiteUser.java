package com.attendance.system.model;

import com.attendance.system.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa a un usuario en el sistema.
 * Esta clase es un modelo que se almacena en la base de datos.
 */
@Entity // Indica que esta clase es una entidad JPA
@Data // Genera automáticamente los métodos getters y setters, así como equals(), hashCode() y toString()
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor // Genera un constructor con todos los parámetros
@Builder // Permite la creación de instancias de esta clase utilizando el patrón Builder
public class SiteUser {

	@Id // Indica que este campo es la clave primaria de la entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que el valor de este campo es generado automáticamente por la base de datos
	private Long userId; // Identificador único del usuario

	private String userName; // Nombre de usuario del usuario

	private String enrollment; // Matrícula del usuario (puede ser relevante para estudiantes)

	private String email; // Dirección de correo electrónico del usuario

	private String password; // Contraseña del usuario, almacenada en forma encriptada

	private Boolean status; // Indica si la cuenta del usuario está activa (true) o inactiva (false)

	private Boolean isLoggedIn; // Indica si el usuario está actualmente conectado (true) o no (false)

	private Role role; // Rol del usuario, que determina sus permisos en el sistema (por ejemplo, estudiante o facultad)
}
