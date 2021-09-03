<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
        <title>Fabrica</title>
        <Style>
            .contenedor {
                background-color: rgb(34, 34, 34);
                color: #fff;
                border-top: 1px solid #fff;
                padding: 12px;
            }
            .contenedor>div {
                margin-left: 7px;
            }
        </Style>
    </head>
    <body>
        <div class="row my-0">
            <div class="col">
                <div class="contenedor d-flex">
                    <div class=" p-2 bg-dark text-white me-auto">
                        <h1>
                            MI MUEBLERIA
                        </h1>
                    </div>
                    <div class="align-self-center" style="font-size: 25px">
                        ${nombreUsuario}
                    </div>
                    <div style="margin-left: 30px;"> </div>
                </div>
            </div>
        </div>
