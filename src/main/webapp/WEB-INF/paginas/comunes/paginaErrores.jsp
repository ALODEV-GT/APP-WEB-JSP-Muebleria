<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERRORES</title>
        <Style>
            .contenedor {
                background-color: rgb(34, 34, 34);
                color: #fff;
                border-top: 1px solid #fff;
                padding: 40px;
            }
            .contenedor>div {
                margin-left: 7px;
            }
        </Style>
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/e6771115e8.js" crossorigin="anonymous"></script>
    <body style="background-color: rgb(34,34,34)">

        <div class="row my-0">
            <div class="col">
                <div class="contenedor d-flex">
                </div>
            </div>
        </div>

        <div class="container col-md-3" style="border: 2px solid #e63946 ; border-radius: 15px;">
            <br><br>
            <div style="text-align: center; color: #e63946;">
                <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-exclamation-triangle-fill" viewBox="0 0 16 16">
                <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </svg>
            </div>
            <br>
            <h1 style="color: #EBEBEB; text-align: center"> ${mensaje}</h1>
            <br><br><br><br><br><br><br><br>
        </div>

    </body>
</html>
