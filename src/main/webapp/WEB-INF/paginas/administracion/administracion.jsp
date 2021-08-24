<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion</title>
    </head>
    <body>
        <h1>HOLA ${nombreUsuario}  ERES UN ADMINISTRADOR</h1>
        <h2>Eres del area de ${area} </h2> 
        <form name="formulario" method="POST" action="${pageContext.request.contextPath}/ServletCargaArchivo" enctype="multipart/form-data">
            <input type="file" name="archivoCargaDatos" accept=".txt">
            <input type="submit" value="Enviar" />
        </form>

        <a href="${pageContext.request.contextPath}/ServletControlador?accion=logOut">Cerrar sesion</a>
    </body>
</html>
