package com.attendance.system.api.v1.auth;

import org.springframework.http.HttpStatus; // Importación para manejar estados HTTP.
import org.springframework.http.ResponseEntity; // Para construir respuestas HTTP.
import org.springframework.security.authentication.AuthenticationManager; // Interfaz para manejar la autenticación.
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Token que contiene las credenciales del usuario.
import org.springframework.security.core.Authentication; // Representa el resultado de una autenticación.
import org.springframework.security.core.context.SecurityContextHolder; // Para manejar el contexto de seguridad.
import org.springframework.security.core.userdetails.UserDetails; // Interfaz que representa los detalles del usuario.
import org.springframework.security.core.userdetails.UserDetailsService; // Interfaz para cargar detalles del usuario.
import org.springframework.web.bind.annotation.GetMapping; // Para manejar solicitudes GET.
import org.springframework.web.bind.annotation.PostMapping; // Para manejar solicitudes POST.
import org.springframework.web.bind.annotation.RequestBody; // Para extraer el cuerpo de la solicitud.
import org.springframework.web.bind.annotation.RequestMapping; // Para mapear rutas.
import org.springframework.web.bind.annotation.RestController; // Indica que esta clase es un controlador REST.

import com.attendance.system.api.dtos.AuthRequest; // DTO para la solicitud de autenticación.
import com.attendance.system.api.dtos.AuthResponseFaculty; // DTO para la respuesta de autenticación de facultad.
import com.attendance.system.api.dtos.AuthResponseStudent; // DTO para la respuesta de autenticación de estudiantes.
import com.attendance.system.api.exceptions.ApiErrorResponse; // Clase para encapsular respuestas de error.
import com.attendance.system.api.exceptions.ApiException; // Excepción personalizada para manejar errores en la API.
import com.attendance.system.config.jwt.JwtHelper; // Clase de ayuda para manejar tokens JWT.
import com.attendance.system.enums.Role; // Enum que define los roles de los usuarios.
import com.attendance.system.model.SiteUser; // Clase que representa un usuario del sistema.
import com.attendance.system.model.Student; // Clase que representa un estudiante.
import com.attendance.system.service.StudentService; // Servicio para manejar operaciones relacionadas con estudiantes.
import com.attendance.system.service.UserService; // Servicio para manejar operaciones relacionadas con usuarios.
import com.attendance.system.service.impl.UserDetailsServiceImpl; // Implementación del servicio de detalles del usuario.

@RestController // Indica que esta clase es un controlador REST y manejará las solicitudes HTTP.
@RequestMapping("/api/v1/auth") // Define la ruta base para las operaciones de autenticación.
public class ApiAuthController {

    // Servicios utilizados para la autenticación y gestión de usuarios.
    private final UserService userService;
    private final StudentService studentService;
    private final AuthenticationManager authenticationManager; // Gestor de autenticación para autenticar usuarios.
    private final UserDetailsService userDetailsService; // Servicio para cargar detalles del usuario.
    private final JwtHelper jwtHelper; // Clase para manejar la generación y validación de tokens JWT.

    // Constructor que inicializa los servicios requeridos.
    public ApiAuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService,
                             JwtHelper jwtHelper, UserService userService, StudentService studentService) {
        this.authenticationManager = authenticationManager; // Inicializa el gestor de autenticación.
        this.userDetailsService = userDetailsService; // Inicializa el servicio de detalles del usuario.
        this.jwtHelper = jwtHelper; // Inicializa el helper para JWT.
        this.userService = userService; // Inicializa el servicio de usuarios.
        this.studentService = studentService; // Inicializa el servicio de estudiantes.
    }

    // Maneja las solicitudes POST para la autenticación de usuarios.
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        // Intenta autenticar al usuario con las credenciales proporcionadas.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        // Establece el contexto de seguridad con la autenticación resultante.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Carga los detalles del usuario autenticado.
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        // Genera un token JWT para el usuario autenticado.
        String token = jwtHelper.generateToken(userDetails);

        // Obtiene el usuario del sistema basado en el nombre de usuario.
        SiteUser user = userService.getUser(userDetails.getUsername()).getBody();

        // Maneja el caso en que no se encuentra el usuario.
        if (user == null) {
            throw new ApiException(new ApiErrorResponse("Unknown username", HttpStatus.INTERNAL_SERVER_ERROR));
        }

        // Maneja el caso en que el usuario es un estudiante.
        if (user.getRole().equals(Role.STUDENT)) {
            Student student = studentService.getStudent(user).getBody(); // Obtiene los detalles del estudiante.
            if (student == null) {
                throw new ApiException(new ApiErrorResponse("Unknown username", HttpStatus.INTERNAL_SERVER_ERROR));
            }
            // Crea la respuesta de autenticación para estudiantes.
            AuthResponseStudent response = AuthResponseStudent.builder()
                    .email(user.getEmail())
                    .username(user.getUserName())
                    .enrollment(user.getEnrollment())
                    .token(token)
                    .expiry(jwtHelper.getExpirationDateFromToken(token))
                    .batch(student.getStudentBatch())
                    .course(student.getStudentCourse())
                    .division(student.getStudentDivision())
                    .studentId(student.getStudentId())
                    .build();
            // Retorna la respuesta con los detalles del estudiante.
            return ResponseEntity.ok(response);
        }

        // Crea la respuesta de autenticación para facultades.
        AuthResponseFaculty response = AuthResponseFaculty.builder()
                .email(user.getEmail())
                .username(user.getUserName())
                .facultyId(user.getUserId())
                .enrollment(user.getEnrollment())
                .token(token)
                .expiry(jwtHelper.getExpirationDateFromToken(token))
                .build();
        // Retorna la respuesta con los detalles de la facultad.
        return ResponseEntity.ok(response);
    }

    // Endpoint para registrar un nuevo usuario.
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SiteUser newUser) {
        // Registra el nuevo usuario en el sistema llamando al servicio de usuarios.
        try {
            SiteUser createdUser = userService.addUser(newUser).getBody();
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            throw new ApiException(new ApiErrorResponse("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    // Maneja las solicitudes GET para la autenticación.
    @GetMapping("/login")
    public ResponseEntity<String> authenticateUser() {
        // Retorna un mensaje indicando que las solicitudes GET no son soportadas para autenticación.
        return new ResponseEntity<>("Login Using Get Request Is Not Supported", HttpStatus.METHOD_NOT_ALLOWED);
    }
}
