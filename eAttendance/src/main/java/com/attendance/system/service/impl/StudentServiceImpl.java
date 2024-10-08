package com.attendance.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.StudentDao;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.SiteUser;
import com.attendance.system.model.Student;
import com.attendance.system.model.StudentWrapper;
import com.attendance.system.service.BatchService;
import com.attendance.system.service.CourseService;
import com.attendance.system.service.MappingService;
import com.attendance.system.service.SessionService;
import com.attendance.system.service.StudentService;
import com.attendance.system.service.UserService;

/**
 * Implementación del servicio StudentService que maneja la lógica de negocio
 * relacionada con las operaciones sobre estudiantes, como agregar, actualizar,
 * obtener y eliminar estudiantes, así como verificar sesiones.
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;  // Repositorio para las operaciones de acceso a datos de estudiantes

	@Autowired
	private CourseService courseService; // Servicio para manejar cursos

	@Autowired
	private MappingService mappingService; // Servicio para manejar asignaciones

	@Autowired
	private BatchService batchService; // Servicio para manejar grupos

	@Autowired
	private SessionService sessionService; // Servicio para manejar sesiones

	@Autowired
	private UserService userService; // Servicio para manejar usuarios

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<StudentWrapper> getAll() {
		try {
			// Crea un envoltorio de estudiantes que incluye todos los estudiantes, grupos y cursos
			StudentWrapper studentWrapper = new StudentWrapper(getAllStudents().getBody(),
					batchService.getAllBatches().getBody(), courseService.getAllCources().getBody());
			return new ResponseEntity<>(studentWrapper, HttpStatus.OK);
		} catch (Exception e) {
			// Manejo de excepciones para devolver un 404 si no se encuentra información
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<Student>> getAllStudents() {
		try {
			// Obtiene todos los estudiantes del repositorio
			return new ResponseEntity<>(studentDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// Devuelve una lista vacía en caso de error
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> addStudent(Student student, SiteUser user) {
		try {
			// Agrega un nuevo usuario y asigna ese usuario al estudiante
			student.setUser(userService.addUser(user).getBody());
			studentDao.save(student); // Guarda el estudiante en la base de datos
			return new ResponseEntity<>("<p class='text-success'>Student Added SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			// Devuelve un mensaje de error en caso de falla
			return new ResponseEntity<>("<p class='text-danger'>" + e.getMessage() + "</p>",
                    HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<String>> getDivisons() {
		try {
			// Obtiene las divisiones disponibles para los estudiantes
			return new ResponseEntity<>(studentDao.getDivisons(), HttpStatus.OK);
		} catch (Exception e) {
			// Devuelve una lista vacía si no se encuentra información
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Mapping> isSession(@NonNull Long sid) {
		try {
			// Busca al estudiante por su ID
			Student student = studentDao.findById(sid).get();
			String course_id = student.getStudentCourse().getCourseId().toString();
			String division = student.getStudentDivision();

			// Obtiene las asignaciones correspondientes al curso del estudiante
			List<Mapping> mappings = mappingService.getMappingsFor(courseService.getCourse(Integer.parseInt(course_id)).getBody()).getBody();

			// Itera sobre las asignaciones para verificar si hay sesiones activas
            assert mappings != null;
            for (Mapping mapping : mappings) {
				String facultyId = mapping.getFaculty().getUserId().toString();
				String sem_id = mapping.getSemester().getSemesterId().toString();
				String subject_id = mapping.getSubject().getSubjectId().toString();
				String sessionId = course_id + "-" + subject_id + "-" + sem_id + "-" + division + "-" + facultyId;

				// Verifica si hay una sesión activa
				if (sessionService.isSessionAvailable(course_id, subject_id, sem_id, division, facultyId)) {
					return new ResponseEntity<>(mapping, HttpStatus.OK);
				}
			}
			return null; // No hay sesión activa
		} catch (Exception e) {
			// Manejo de excepciones para devolver un 200 en caso de error
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Student> getStudent(@NonNull Long sid) {
		try {
			// Obtiene un estudiante específico por su ID
			return new ResponseEntity<>(studentDao.findById(sid).get(), HttpStatus.OK);
		} catch (Exception e) {
			// Devuelve un 404 si no se encuentra el estudiante
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Student> getStudent(SiteUser user) {
		// Obtiene un estudiante asociado a un usuario específico
		return ResponseEntity.ok(studentDao.findByUser(user));
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Student> updateStudent(Student student) {
		// Actualiza la información del usuario asociado al estudiante
		userService.updateUser(student.getUser());

		// Busca al estudiante existente
		Student oldStudent = studentDao.findById(student.getStudentId()).get();

		// Actualiza la división, el grupo y el curso si están presentes
		if (Objects.nonNull(student.getStudentDivision()) && !"".equalsIgnoreCase(student.getStudentDivision())) {
			oldStudent.setStudentDivision(student.getStudentDivision());
		}

		if (Objects.nonNull(student.getStudentBatch())) {
			oldStudent.setStudentBatch(student.getStudentBatch());
		}

		if (Objects.nonNull(student.getStudentCourse())) {
			oldStudent.setStudentCourse(student.getStudentCourse());
		}

		// Guarda el estudiante actualizado y lo devuelve
		return ResponseEntity.ok(studentDao.save(oldStudent));
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Boolean> deleteStudent(Long sid) {
		// Busca al estudiante por su ID
		Student student = studentDao.findById(sid).get();
		// Elimina el usuario asociado y luego el estudiante
		if (userService.deleteUser(student.getUser()).getBody()) {
			studentDao.delete(student);
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
}
