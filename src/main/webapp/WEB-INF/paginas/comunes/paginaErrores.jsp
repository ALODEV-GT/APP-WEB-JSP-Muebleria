<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERRORES</title>
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/e6771115e8.js" crossorigin="anonymous"></script>
    <body>

        <div style="display: flex; justify-content: center; align-items: center;">
            <div class="card text-white bg-danger mb-3" style="width: 25rem; height: 30rem; text-align: center; font-size: 40px;" >
                <div class="card-header">HA OCURRIDO UN ERROR</div>
                <div class="card-body">
                    <h5 class="card-title">Error</h5>
                    <p class="card-text">${mensaje}</p>
                </div>
            </div>
        </div>

    </body>
</html>
