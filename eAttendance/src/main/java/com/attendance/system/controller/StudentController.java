package com.attendance.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.system.enums.Role;
import com.attendance.system.model.Batch;
import com.attendance.system.model.Course;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.SiteUser;
import com.attendance.system.model.Student;
import com.attendance.system.service.StudentService;

import jakarta.websocket.server.PathParam;

/**
 * Controlador para manejar las operaciones relacionadas con los estudiantes.
 * Este controlador se encarga de recibir las peticiones HTTP y delegar la lógica de negocio a
 * la capa de servicios correspondiente (StudentService).
 */
@RestController // Indica que esta clase es un controlador REST
@RequestMapping("student") // Mapea las solicitudes a la URL base "student"
public class StudentController {

	// Inyección de dependencia para el servicio de estudiantes
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * Método para mostrar la vista de estudiantes.
	 * @return ModelAndView que contiene la vista de estudiantes y la lista de estudiantes.
	 */
	@RequestMapping
	public ModelAndView student() {
		// Devuelve la vista "student" con un objeto que contiene todos los estudiantes
		return new ModelAndView("student").addObject("studentWrapper", studentService.getAll().getBody());
	}

	/**
	 * Método para agregar un nuevo estudiante.
	 * @param username Nombre de usuario del estudiante.
	 * @param email Correo electrónico del estudiante.
	 * @param enrollment Matrícula del estudiante.
	 * @param studentDivision División del estudiante.
	 * @param password Contraseña del estudiante.
	 * @param course Curso del estudiante.
	 * @param batch Lote del estudiante.
	 * @return ResponseEntity que indica el resultado de la operación.
	 */
	@PostMapping("add")
	public ResponseEntity<String> addStudent(@RequestParam("userName") String username,
											 @RequestParam("email") String email,
											 @RequestParam("enrollment") String enrollment,
											 @RequestParam("studentDivision") String studentDivision,
											 @PathParam("password") String password,
											 @RequestParam("studentCourse") Course course,
											 @RequestParam("studentBatch") Batch batch) {

		// Crea un objeto SiteUser con los detalles del estudiante
		SiteUser user = SiteUser.builder()
				.userName(username)
				.email(email)
				.password(password)
				.enrollment(enrollment)
				.role(Role.STUDENT)
				.build();

		// Crea un objeto Student con los detalles específicos del estudiante
		Student student = Student.builder()
				.studentBatch(batch)
				.studentDivision(studentDivision)
				.studentCourse(course)
				.build();

		// Llama al servicio para agregar el estudiante y devuelve la respuesta
		return studentService.addStudent(student, user);
	}

	/**
	 * Método para obtener todas las divisiones disponibles.
	 * @return ResponseEntity que contiene una lista de divisiones.
	 */
	@GetMapping("getDivisions")
	public ResponseEntity<List<String>> getDivisions() {
		// Llama al servicio para obtener divisiones y devuelve la respuesta
		return studentService.getDivisons();
	}

	/**
	 * Método para verificar si una sesión está activa para un estudiante dado.
	 * @param sid Identificador del estudiante.
	 * @return ResponseEntity que contiene un objeto Mapping con la información de sesión.
	 */
	@GetMapping("isSession/{sid}")
	public ResponseEntity<Mapping> isSession(@PathVariable @NonNull Long sid) {
		// Llama al servicio para verificar la sesión del estudiante y devuelve la respuesta
		return studentService.isSession(sid);
	}

	/**
	 * Método para obtener los detalles de un estudiante específico.
	 * @param sid Identificador del estudiante.
	 * @return ResponseEntity que contiene el objeto Student con los detalles del estudiante.
	 */
	@GetMapping("getStudent/{sid}")
	public ResponseEntity<Student> getStudent(@PathVariable @NonNull Long sid) {
		// Llama al servicio para obtener el estudiante y devuelve la respuesta
		return studentService.getStudent(sid);
	}

	/**
	 * Método para actualizar los detalles de un estudiante existente.
	 * @param updStuId Identificador del estudiante a actualizar.
	 * @param updUserId Identificador del usuario asociado al estudiante.
	 * @param updStuEnroll Nueva matrícula del estudiante.
	 * @param updStuName Nuevo nombre de usuario del estudiante.
	 * @param updStuEmail Nuevo correo electrónico del estudiante (opcional).
	 * @param updStuDivision Nueva división del estudiante.
	 * @param updStuCourse Nuevo curso del estudiante.
	 * @param updStuBatch Nuevo lote del estudiante.
	 * @return ResponseEntity que indica el resultado de la operación de actualización.
	 */
	@PutMapping("updateStudent")
	public ResponseEntity<String> updateStudentById(@RequestParam Long updStuId,
													@RequestParam Long updUserId,
													@RequestParam String updStuEnroll,
													@RequestParam String updStuName,
													@RequestParam(required = false) String updStuEmail,
													@RequestParam String updStuDivision,
													@RequestParam Course updStuCourse,
													@RequestParam Batch updStuBatch) {

		// Crea un objeto SiteUser con los nuevos detalles del estudiante
		SiteUser user = SiteUser.builder()
				.userId(updUserId)
				.userName(updStuName)
				.email(updStuEmail)
				.enrollment(updStuEnroll)
				.build();

		// Crea un objeto Student con los nuevos detalles del estudiante
		Student student = Student.builder()
				.studentId(updStuId)
				.studentDivision(updStuDivision)
				.studentCourse(updStuCourse)
				.studentBatch(updStuBatch)
				.user(user) // Asocia el objeto SiteUser al estudiante
				.build();

		// Llama al servicio para actualizar el estudiante
		studentService.updateStudent(student);
		return ResponseEntity.ok("Student Updated Successfully");
	}

	/**
	 * Método para eliminar un estudiante por su identificador.
	 * @param sid Identificador del estudiante a eliminar.
	 * @return ResponseEntity que indica el resultado de la operación de eliminación.
	 */
	@SuppressWarnings("null")
	@DeleteMapping("delete/{sid}")
	public ResponseEntity<Integer> deleteStudent(@PathVariable Long sid) {
		// Llama al servicio para eliminar el estudiante y devuelve el resultado
		if (Boolean.TRUE.equals(studentService.deleteStudent(sid).getBody())) {
			return ResponseEntity.ok(1); // Devuelve 1 si la eliminación fue exitosa
		}
		return ResponseEntity.ok(0); // Devuelve 0 si la eliminación falló
	}
}
