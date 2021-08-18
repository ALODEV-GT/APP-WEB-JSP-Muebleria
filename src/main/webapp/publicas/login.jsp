<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h2> LOGIN</h2>

        <form  action="${pageContext.request.contextPath}/ServletControlador?accion=validarUsuario" method="POST" class="was-validated">
            <label for="usuario">Nombre usuario </label>
            <input type="text" name="nombreUsuario" required />
            <br>
            <label for="password">Contrasena</label>
            <input type="password" name="password" required />
            <br>
            <input type="submit" value="Iniciar"/>
        </form>
        ${mensaje}
    </body>
</html>
