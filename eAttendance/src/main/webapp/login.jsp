<!DOCTYPE html>
<html lang="en">

<head>
    <title>eAttendance - Login</title>

    <!-- Importa otros archivos JSP que contienen los estilos y scripts necesarios para el head -->
    <jsp:include page="components/head-imports.jsp"/>
</head>

<body class="bg-gradient-primary">
<!-- Contenedor principal -->
<div class="container">

    <!-- Fila externa que justifica el contenido al centro -->
    <div class="row justify-content-center">

        <!-- Define el ancho de la columna para diferentes tamaños de pantalla -->
        <div class="col-xl-10 col-lg-12 col-md-9">

            <!-- Tarjeta que contiene el formulario de inicio de sesión, con sombra y margen en la parte superior -->
            <div class="card o-hidden border-0 shadow-lg my-5">
                <!-- Cuerpo de la tarjeta -->
                <div class="card-body p-0">
                    <!-- Fila anidada dentro del cuerpo de la tarjeta -->
                    <div class="row">
                        <!-- Imagen de fondo del lado izquierdo solo visible en pantallas grandes -->
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>

                        <!-- Columna derecha que contiene el formulario de inicio de sesión -->
                        <div class="col-lg-6">
                            <div class="p-5">
                                <!-- Encabezado del formulario, centrado -->
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                </div>

                                <!-- Formulario de inicio de sesión, con el método POST -->
                                <form class="user" method="post">
                                    <!-- Campo de entrada para el correo electrónico o nombre de usuario -->
                                    <div class="form-group">
                                        <input type="email" class="form-control form-control-user"
                                               id="username" name="username" aria-describedby="emailHelp"
                                               placeholder="Enter Email Address..." required>
                                    </div>

                                    <!-- Campo de entrada para la contraseña -->
                                    <div class="form-group">
                                        <input type="password" name="password" id="password"
                                               class="form-control form-control-user" placeholder="Password" required>
                                    </div>


                                    <!-- Sección para mostrar un mensaje de error si el nombre de usuario o la contraseña son incorrectos -->
                                    <div class="mt-2 mb-2">
                                        <%
                                            // Verifica si hay un error en la solicitud (parámetro "error")
                                            if (request.getParameter("error") != null) {
                                                // Muestra un mensaje de error en rojo si las credenciales no son válidas
                                                out.print("<p class='text-danger'>Invalid Username Or Password! Try Again</p>");
                                            }
                                        %>
                                    </div>

                                    <!-- Botón de envío para iniciar sesión -->
                                    <button type="submit" class="btn btn-primary btn-user btn-block">Login</button>

                                    <hr>
                                </form>

                                <!-- Enlaces adicionales para recuperar la contraseña o crear una nueva cuenta -->
                                <div class="text-center">
                                    <a class="small" href="subjects.jsp">Forgot Password?</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="subjects.jsp">Create an Account!</a>
                                </div>
                            </div>
                        </div>
                    </div> <!-- Fin de la fila dentro del cuerpo de la tarjeta -->
                </div> <!-- Fin del cuerpo de la tarjeta -->
            </div> <!-- Fin de la tarjeta -->

        </div> <!-- Fin de la columna de tamaño ajustable -->

    </div> <!-- Fin de la fila externa -->

</div> <!-- Fin del contenedor principal -->

<!-- Importa otros archivos JSP que contienen los scripts necesarios para la página -->
<jsp:include page="components/js-imports.jsp"/>

</body>
</html>
