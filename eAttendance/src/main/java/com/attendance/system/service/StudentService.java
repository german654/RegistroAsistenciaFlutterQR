package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import com.attendance.system.model.Mapping;
import com.attendance.system.model.SiteUser;
import com.attendance.system.model.Student;
import com.attendance.system.model.StudentWrapper;

/**
 * Interfaz para definir los métodos relacionados con las operaciones de estudiantes.
 * Esta interfaz se implementa en la capa de servicios y proporciona
 * los métodos que se pueden utilizar para manejar los datos y la lógica de negocio de los estudiantes.
 */
public interface StudentService {

	/**
	 * Obtiene todos los estudiantes envueltos en un objeto StudentWrapper.
	 * @return ResponseEntity que contiene un objeto StudentWrapper con todos los estudiantes.
	 */
	ResponseEntity<StudentWrapper> getAll();

	/**
	 * Obtiene una lista de todos los estudiantes.
	 * @return ResponseEntity que contiene una lista de objetos Student.
	 */
	ResponseEntity<List<Student>> getAllStudents();

	/**
	 * Agrega un nuevo estudiante y su información asociada.
	 * @param student Objeto Student que representa al estudiante a agregar.
	 * @param user Objeto SiteUser que representa al usuario asociado al estudiante.
	 * @return ResponseEntity que indica el resultado de la operación de adición.
	 */
	ResponseEntity<String> addStudent(Student student, SiteUser user);

	/**
	 * Obtiene todas las divisiones disponibles para los estudiantes.
	 * @return ResponseEntity que contiene una lista de cadenas que representan las divisiones.
	 */
	ResponseEntity<List<String>> getDivisons();

	/**
	 * Actualiza los detalles de un estudiante existente.
	 * @param user Objeto Student que contiene los nuevos detalles del estudiante.
	 * @return ResponseEntity que indica el resultado de la operación de actualización.
	 */
	ResponseEntity<Student> updateStudent(Student user);

	/**
	 * Verifica si hay una sesión activa para un estudiante dado.
	 * @param sid Identificador del estudiante.
	 * @return ResponseEntity que contiene un objeto Mapping con la información de la sesión del estudiante.
	 */
	ResponseEntity<Mapping> isSession(@NonNull Long sid);

	/**
	 * Obtiene los detalles de un estudiante específico por su identificador.
	 * @param sid Identificador del estudiante.
	 * @return ResponseEntity que contiene un objeto Student con los detalles del estudiante.
	 */
	ResponseEntity<Student> getStudent(@NonNull Long sid);

	/**
	 * Obtiene los detalles de un estudiante basado en su objeto SiteUser.
	 * @param user Objeto SiteUser asociado al estudiante.
	 * @return ResponseEntity que contiene un objeto Student con los detalles del estudiante.
	 */
	ResponseEntity<Student> getStudent(SiteUser user);

	/**
	 * Elimina un estudiante por su identificador.
	 * @param sid Identificador del estudiante a eliminar.
	 * @return ResponseEntity que indica si la eliminación fue exitosa o no.
	 */
	ResponseEntity<Boolean> deleteStudent(Long sid);
}
