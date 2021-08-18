<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion</title>
    </head>
    <body>
        <h1>ERES UN ADMINISTRADOR</h1>

        <form name="formulario" method="POST" action="${pageContext.request.contextPath}/ServletCargaArchivo" enctype="multipart/form-data">
            <input type="file" name="archivoCargaDatos" accept=".txt">
            <input type="submit" value="Enviar" />
        </form>


    </body>
</html>
