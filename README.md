# MUEBLERIA

## Descripcion
Aplicacion creada para practicar la creacion de aplicaciones web usando JSP.

La aplicacion se baso en las necesidades que podrian tener una muebleria.

La aplicacion maneja tres areas: administrativa, fabricacion, ventas.
En el area administrativa es posible generar reporte de ventas, reporte de devoluciones, reporte de ganancias, reporte del vendedor con mas ventas, reporte del vendedor con mas ganancias, reporte del mueble mas vendido, reporte del mueble menos vendido, los reportes pueden ser exportados en un archivo csv.
En este area tambien es posible editar el precio de los muebles, agregar nuevos muebles, tambien permite llevar el control de los usuarios que pueden acceder al sistema segun el area que se le asigne. En el area administrativa se puede cargar informacion a la base de datos utilizando un archivo de texto con las instrucciones adecuadas.

En el area de fabricacion se pueden agregar las diferentes piezas para la construccion de un mueble, tambien cuenta con un historial de ensamblajes mostrando las piezas usadas y el costo de fabricacion, cuenta con un apartado que permite ensamblar muebles para luego ser mostrados en el area de ventas.

En el area de ventas permite la compra o devolucion de muebles.
Este area permite realizar consultas como las compras de un cliente, las devoluciones de un cliente, encontrar una factura, reporte de ventas del dia.

### Se uso:
- JSP
- Servlets
- Patrones de diseno
- Bootstrap

## Vista previa
### Login
<p align="center">
  <kbd>
    <img src="https://i.ibb.co/7r2jB6N/muebleria-login.png" alt="muebleria-login" border="0">
  </kbd>
</p>

### Area administrativa
<p align="center">
    <kbd>
        <img src="https://i.ibb.co/k5vxDtF/admin-1.png" alt="admin-1" border="0">
    </kbd><br><br>
    <kbd>
        <img src="https://i.ibb.co/BNhv8Jr/admin-2.png" alt="admin-2" border="0">
    </kbd><br><br>
    <kbd>
        <img src="https://i.ibb.co/4gKXNRK/admin-3.png" alt="admin-3" border="0">
    </kbd>
</p>

### Area de fabricacion
<p align="center">
    <kbd>
        <img src="https://i.ibb.co/DDVKW0f/fabrica-1.png" alt="fabrica-1" border="0">
    </kbd><br><br>
    <kbd>
        <img src="https://i.ibb.co/JvsYCxQ/fabrica-2.png" alt="fabrica-2" border="0">
    </kbd><br><br>
    <kbd>
        <img src="https://i.ibb.co/kQY0fMz/fabrica-3.png" alt="fabrica-3" border="0">
    </kbd><br><br>
    <kbd>
        <img src="https://i.ibb.co/jJL1sNT/fabrica-4.png" alt="fabrica-4" border="0">
    </kbd>
</p>

### Area de venta
<p align="center">
    <kbd>
        <img src="https://i.ibb.co/ry1bNjs/ventas-1.png" alt="ventas-1" border="0">
    </kbd><br><br>
    <kbd>
        <img src="https://i.ibb.co/D4hFq03/ventas-2.png" alt="ventas-2" border="0">
    </kbd><br><br>
    <kbd>
        <img src="https://i.ibb.co/7v4vhty/ventas-3.png" alt="ventas-3" border="0">
    </kbd><br><br>
</p>

## Importante
Cargar el archivo mapeoFisico.sql en mysql.
Ejecutar
