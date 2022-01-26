CREATE USER 'brayan'@'localhost' IDENTIFIED BY 'contra123';
GRANT ALL PRIVILEGES ON *.* TO 'brayan'@'localhost';

CREATE SCHEMA MI_MUEBLERIA;
USE MI_MUEBLERIA;

CREATE TABLE area (
    id_area INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT PK_area PRIMARY KEY(id_area)
);

CREATE TABLE usuario (
    nombre_usuario VARCHAR(30) NOT NULL, 
    passw VARCHAR(25) NOT NULL,
    id_area INT NOT NULL,
    deshabilitado TINYINT NOT NULL,
    CONSTRAINT PK_usuario PRIMARY KEY(nombre_usuario),
    CONSTRAINT FK_to_area FOREIGN KEY(id_area) REFERENCES area(id_area)
);

CREATE TABLE cliente (
    nit VARCHAR(15) NOT NULL,
    nombre VARCHAR(45) NOT NULL,
    direccion VARCHAR(45) NOT NULL,
    municipio VARCHAR(45),
    departamento VARCHAR(45),
    CONSTRAINT PK_cliente PRIMARY KEY(nit)
);

CREATE TABLE factura (
    num_factura INT NOT NULL AUTO_INCREMENT,
    nit_cliente VARCHAR(15),
    fecha DATE NOT NULL,
    vendedor VARCHAR(30),
    total DECIMAL(7,2),
    CONSTRAINT PK_factura PRIMARY KEY(num_factura),
    CONSTRAINT FK_to_cliente FOREIGN KEY(nit_cliente) REFERENCES cliente(nit),
    CONSTRAINT FK_to_usuario FOREIGN KEY(vendedor) REFERENCES usuario(nombre_usuario)
);

CREATE TABLE tipo_pieza (
    id_tipo_pieza INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    cantidad INT NOT NULL,
    eliminado TINYINT,
    CONSTRAINT PK_tipo_pieza PRIMARY KEY(id_tipo_pieza)
);

CREATE TABLE mueble(
    tipo_mueble VARCHAR(45) NOT NULL,
    precio DECIMAL(7,2) NOT NULL,
    CONSTRAINT PK_mueble PRIMARY KEY(tipo_mueble)
);


CREATE TABLE requerimiento (
    num_requisito INT NOT NULL AUTO_INCREMENT,
    tipo_mueble VARCHAR(45) NOT NULL,
    id_tipo_pieza INT NOT NULL,
    cantidad_necesaria INT NOT NULL,
    CONSTRAINT PK_requerimiento PRIMARY KEY(num_requisito, tipo_mueble),
    CONSTRAINT FK_to_muebles FOREIGN KEY(tipo_mueble) REFERENCES mueble(tipo_mueble),
    CONSTRAINT FK_to_tipo_piezas FOREIGN KEY(id_tipo_pieza) REFERENCES tipo_pieza(id_tipo_pieza)
);

CREATE TABLE pieza (
    id_pieza INT NOT NULL AUTO_INCREMENT,
    id_tipo_pieza INT NOT NULL,
    precio DECIMAL(7,2) NOT NULL,
    usado TINYINT,
    CONSTRAINT PK_pieza PRIMARY KEY(id_pieza),
    CONSTRAINT FK_to_tipo_pieza FOREIGN KEY(id_tipo_pieza) REFERENCES tipo_pieza(id_tipo_pieza)
);


CREATE TABLE ensamble(
    id_ensamble INT NOT NULL AUTO_INCREMENT,
    fecha_ensamble DATE NOT NULL,
    usuario_ensamblador VARCHAR(30) NOT NULL,
    tipo_mueble VARCHAR(45) NOT NULL,
    costo DECIMAL(7,2),
    vendido TINYINT,
    devuelto TINYINT,
    CONSTRAINT PK_ensamble PRIMARY KEY(id_ensamble),
    CONSTRAINT FK_to_mueble FOREIGN KEY(tipo_mueble) REFERENCES mueble(tipo_mueble),
    CONSTRAINT FK_to_ensamblador FOREIGN KEY(usuario_ensamblador) REFERENCES usuario(nombre_usuario)
);

CREATE TABLE devolucion (
    id_devolucion INT NOT NULL AUTO_INCREMENT,
    id_mueble_devuelto INT NOT NULL,
    num_factura INT NOT NULL,
    fecha DATE NOT NULL,
    perdida DECIMAL(7,2) NOT NULL,
    CONSTRAINT PK_devolucion PRIMARY KEY(id_devolucion),
    CONSTRAINT FK_to_facturas FOREIGN KEY(num_factura) REFERENCES factura(num_factura),
    CONSTRAINT FB_to_ensambles FOREIGN KEY(id_mueble_devuelto) REFERENCES ensamble(id_ensamble)
);

CREATE TABLE armado (
    id_pieza_usada INT NOT NULL,
    id_ensamble INT NOT NULL,
    CONSTRAINT PK_armado PRIMARY KEY(id_pieza_usada, id_ensamble),
    CONSTRAINT FK_to_pieza FOREIGN KEY(id_pieza_usada) REFERENCES pieza(id_pieza),
    CONSTRAINT FK_to_ensamble FOREIGN KEY(id_ensamble) REFERENCES ensamble(id_ensamble)
);

CREATE TABLE detalle (
    num_detalle INT NOT NULL,
    num_factura INT NOT NULL,
    id_ensamble INT NOT NULL,
    precio DECIMAL(7,2),
    CONSTRAINT PK_detalle PRIMARY KEY(num_detalle, num_factura),
    CONSTRAINT FK_to_factura FOREIGN KEY(num_factura) REFERENCES factura(num_factura),
    CONSTRAINT FK_to_ensambles FOREIGN KEY(id_ensamble) REFERENCES ensamble(id_ensamble)
);

INSERT INTO area(nombre) VALUES("Fabrica"), ("Punto de venta"), ("Financiero");
INSERT INTO usuario() VALUES("admin","admin",3,0);