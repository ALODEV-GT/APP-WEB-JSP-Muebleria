<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>

<div class="container col-md-4 my-5" style="border: 1px solid #000; border-radius: 15px; text-align: center">
    <br>
    <h1 class="mb-3 contenedor" style="text-align: center">COMPRA</h1>
    <form id="form" action="${pageContext.request.contextPath}/ServletControlador?accionVentas=obtenerDatos" method="POST">
        <div>
            <label>Nit</label>
            <input style="width : 250px" name="nit"  type="text" value="${cliente.nit}" required/>
            <input type="submit" id="obtenerDatos" value="Obtener datos" />
        </div>
        <div class="my-3">
            <label>Nombre</label>
            <input style="width : 338px" type="text" name="nombreCliente" value="${cliente.nombre}" />
        </div>
        <div class="my-3">
            <label>Direccion</label>
            <input style="width : 329px" type="text" name="direccion"  value="${cliente.direccion}"/>
        </div>
        <div class="my-3">
            <label>Municipio</label>
            <input style="width : 327px" type="text" name="municipio" value="${cliente.municipo}" />
        </div>
        <div class="my-3">
            <label>Departamento</label>
            <input style="width : 291px" type="text" name="departamento" value="${cliente.departamento}" />
        </div>
        <div class="my-3">
            <label>Id Muebles a comprar</label>
            <input style="width : 244px" type="text" name="mueblesAComprar" placeholder="5, 3, 8, 16" />
        </div>

        <div class="d-flex justify-content-center my-4">
            <input class="btn btn-outline-success" type="submit"  id="realizarCompra" value="Realizar compra "/>
        </div>
    </form>
</div>

<script>
    var formulario = document.getElementById("form");

    document.getElementById("obtenerDatos").addEventListener("click", (e) => {
        e.preventDefault();

        formulario.setAttribute("action", "${pageContext.request.contextPath}/ServletControlador?accionVentas=obtenerDatos&vendedor=${nombreUsuario}");
                formulario.submit();
            });

            document.getElementById("realizarCompra").addEventListener("click", (e) => {
                e.preventDefault();

                formulario.setAttribute("action", "${pageContext.request.contextPath}/ServletControlador?accionVentas=realizarCompra&vendedor=${nombreUsuario}");
                        formulario.submit();
                    });
</script>  
<jsp:include page="/WEB-INF/paginas/comunes/pieDePagina.jsp"/>