package com.attendance.system.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;

import com.attendance.system.config.jwt.JwtAuthenticationEntryPoint;
import com.attendance.system.config.jwt.JwtAuthenticationFilter;
import com.attendance.system.service.impl.UserDetailsServiceImpl;

import jakarta.servlet.DispatcherType;

// Indica que esta clase es una configuración de seguridad
@Configuration
@EnableWebSecurity // Habilita la seguridad web de Spring
@EnableMethodSecurity // Habilita la seguridad a nivel de métodos
public class WebSecurityConfig {

    // Inyección de dependencias de componentes necesarios para JWT y manejo de errores
    @Autowired
    private JwtAuthenticationEntryPoint point; // Punto de entrada para manejar errores de autenticación JWT

    @Autowired
    private JwtAuthenticationFilter filter; // Filtro para gestionar las solicitudes JWT

    // Configuración para manejar eventos de sesión, como sesiones expiradas
    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    // Configuración del proveedor de autenticación basado en DAO (Database Access Object)
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService()); // Define el servicio para cargar los detalles del usuario
        authProvider.setPasswordEncoder(passwordEncoder()); // Establece el codificador de contraseñas (BCrypt)
        return authProvider;
    }

    // Manejo de errores en el inicio de sesión (fallos de autenticación)
    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler(); // Clase personalizada para manejar fallos
    }

    // Bean para la gestión de autenticaciones
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    // Registro de sesiones para gestionar múltiples sesiones de usuarios
    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    // Servicio para cargar los detalles del usuario (implementación personalizada)
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(); // Implementación del servicio de detalles de usuario
    }

    // Codificador de contraseñas BCrypt, usado para codificar y verificar contraseñas de usuarios
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración de seguridad para las APIs que comienzan con /api/**
    @Bean
    @Order(1) // Se ejecuta primero por ser de orden 1
    SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**") // Filtra solo las URLs que comienzan con /api/**
                .csrf(csrf -> csrf.disable()) // Desactiva la protección CSRF para APIs (generalmente manejado por JWT)
                .cors(cors -> cors // Configura CORS para permitir solicitudes desde cualquier origen
                        .configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Arrays.asList("*")); // Permite todas las solicitudes de cualquier origen
                            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Métodos permitidos
                            config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Headers permitidos
                            return config;
                        }))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll() // Permite el acceso libre a las rutas de autenticación
                        .anyRequest().authenticated()) // El resto de las rutas de la API requiere autenticación
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(point)) // Usa el manejador de errores JWT cuando hay problemas de autenticación
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // No guarda sesiones de usuario (stateless)
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); // Añade el filtro JWT antes del de autenticación
        return http.build();
    }

    // Configuración de seguridad para el resto de la aplicación
    @Bean
    @Order(2) // Se ejecuta después del filtro de las APIs
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        // Permite ciertas rutas que son necesarias para el funcionamiento de la página sin autenticación
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll() // Permite reenvíos y errores
                        .requestMatchers("/components/**", "/css/**", "/img/**", "/js/**", "/vendor/**", "/login", "/login.jsp", "error-404.jsp", "error.jsp", "webmanifest.json", "favicon.ico", "/service-worker.js")
                        .permitAll() // Permite acceso público a recursos estáticos y páginas de login/error
                        .requestMatchers("/api/**").denyAll() // Bloquea el acceso a las APIs desde esta configuración
                        .anyRequest()
                        .authenticated()) // Resto de las rutas requiere autenticación
                .sessionManagement((session) -> session
                        .invalidSessionUrl("/login") // Redirige a la página de login cuando la sesión es inválida
                        .sessionFixation().changeSessionId() // Cambia el ID de sesión para evitar ataques de fijación de sesión
                        .maximumSessions(1) // Limita a un solo inicio de sesión por usuario
                        .expiredUrl("/login")) // Redirige a login si la sesión expira
                .formLogin((form) -> form
                        .loginPage("/login") // Establece la página de inicio de sesión personalizada
                        .defaultSuccessUrl("/home") // Redirige al home después de un inicio de sesión exitoso
                        .failureHandler(authenticationFailureHandler()) // Usa un manejador personalizado para errores de inicio de sesión
                        .permitAll())
                .logout((logout) -> logout
                        .logoutUrl("/logout") // URL para cerrar sesión
                        .logoutSuccessUrl("/login") // Redirige a login después de cerrar sesión
                        .deleteCookies("JSESSIONID") // Borra las cookies de sesión
                        .invalidateHttpSession(true) // Invalida la sesión actual
                        .clearAuthentication(true)); // Limpia la autenticación
        return http.build();
    }
}
