/**
 * Archivo: ApiStudentController.java
 * Fecha de creación: 08/10/2024
 * Propósito: Este controlador REST se encarga de manejar solicitudes relacionadas con la entidad Student (Estudiantes),
 *            proporcionando endpoints para recuperar información sobre estudiantes, como divisiones.
 * Autor: Eliazar Noa
 */

package com.attendance.system.api.v1.controller;

import java.util.List; // Importa la clase List para manejar listas.

import org.springframework.beans.factory.annotation.Autowired; // Permite la inyección automática de dependencias.
import org.springframework.http.ResponseEntity; // Utilizada para representar respuestas HTTP.
import org.springframework.web.bind.annotation.GetMapping; // Anotación para manejar solicitudes HTTP GET.
import org.springframework.web.bind.annotation.RequestMapping; // Anotación para definir rutas.
import org.springframework.web.bind.annotation.RestController; // Anotación para definir un controlador REST.

import com.attendance.system.service.StudentService; // Importa el servicio StudentService que maneja la lógica de negocio para los estudiantes.

@RestController // Indica que esta clase es un controlador REST que manejará las solicitudes HTTP.
@RequestMapping("/api/v1/student") // Define la ruta base para este controlador, todas las rutas serán relativas a '/api/v1/student'.
public class ApiStudentController {

	@Autowired // Indica que Spring inyectará automáticamente una instancia de StudentService en este atributo.
	private StudentService studentService; // Servicio para manejar la lógica de negocio relacionada con los estudiantes.

	/**
	 * Este método maneja solicitudes HTTP GET en la ruta '/getDivisions'.
	 * Se encarga de devolver una lista de divisiones de estudiantes.
	 *
	 * @return ResponseEntity que contiene una lista de divisiones de estudiantes, o un error si no es posible obtener las divisiones.
	 */
	@GetMapping("/getDivisions") // Mapea las solicitudes HTTP GET a este método en la ruta '/api/v1/student/getDivisions'.
	public ResponseEntity<List<String>> getDivisions(){
		// Llama al servicio de estudiantes para obtener una lista de divisiones y la retorna en una respuesta HTTP.
		return studentService.getDivisons();
	}
}
