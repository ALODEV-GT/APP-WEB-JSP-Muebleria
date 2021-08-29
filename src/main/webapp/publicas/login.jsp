<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/e6771115e8.js" crossorigin="anonymous"></script>
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

        <jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
