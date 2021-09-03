<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="estilos/signin.css" rel="stylesheet">
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/e6771115e8.js" crossorigin="anonymous"></script>

    
    <body class="text-center" style="background-color: black">
        <main class="form-signin" style="border: 2px solid #fff; border-radius: 15px ">
            <form  action="${pageContext.request.contextPath}/ServletControlador?accion=validarUsuario" method="POST" class="was-validated">
                <br>
                <svg  xmlns="http://www.w3.org/2000/svg" width="150" height="150" fill="#fff" class="bi bi-person-circle" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                </svg>
                <h1 class="h3 mb-3 fw-normal" style="color: #fff">Inicio de sesion</h1>
                <br>
                <div class="form-floating">
                    <input type="text" name="nombreUsuario"  class="form-control" id="floatingInput" placeholder="usuario123">
                    <label for="floatingInput">Nombre de usuario</label>
                </div>
                <br>
                <div class="form-floating">
                    <input type="password" name="password"  class="form-control" id="floatingPassword" placeholder="********">
                    <label for="floatingPassword">Contrasena</label>
                </div>
                <br>
                <button class="w-100 btn btn-lg btn-warning" type="submit">Ingresar</button>
            </form>
        </main>
    </body>


    <jsp:include page="/WEB-INF/paginas/comunes/errores.jsp"/>
