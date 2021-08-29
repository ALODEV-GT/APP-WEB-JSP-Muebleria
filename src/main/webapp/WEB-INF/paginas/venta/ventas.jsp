<jsp:include page="/WEB-INF/paginas/comunes/encabezado.jsp"/>
<jsp:include page="/WEB-INF/paginas/venta/menu.jsp"/>
<h1>ERES UN VENDEDOR</h1>

<form id="form" action="${pageContext.request.contextPath}/ServletControlador?accionVentas=obtenerDatos" method="POST">
    <div>
        <label>Nit</label>
        <input name="nit"  type="text" value="${cliente.nit}" required/>
        <input type="submit" id="obtenerDatos" value="Obtener datos" />
    </div>
    <div>
        <label>Nombre</label>
        <input type="text" name="nombreCliente" value="${cliente.nombre}" />
    </div>
    <div>
        <label>Direccion</label>
        <input type="text" name="direccion"  value="${cliente.direccion}"/>
    </div>
    <div>
        <label>Municipio</label>
        <input type="text" name="municipio" value="${cliente.municipo}" />
    </div>
    <div>
        <label>Departamento</label>
        <input type="text" name="departamento" value="${cliente.departamento}" />
    </div>
    <div>
        <label>Id Muebles a comprar</label>
        <input type="text" name="mueblesAComprar" placeholder="5, 3, 8, 16" />
    </div>
    <input type="submit" id="realizarCompra" value="Realizar compra"/>
</form>

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