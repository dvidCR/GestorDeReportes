package database;

import java.sql.*;

public class CreateBBDD {
    	
	private OptionsBBDD options = new OptionsBBDD();
    private Statement stmt = null;

    public void crearTabla() {
        try {
            Connection conection = options.getConnection();

            stmt = conection.createStatement();
            
            // Crear la tabla Productos
            String tbProductos = "CREATE TABLE IF NOT EXISTS Productos (" +
            "id_producto INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT NOT NULL," +
            "categoria TEXT NOT NULL," +
            "precio REAL NOT NULL," +
            "stock INTEGER NOT NULL);";
            
            // Crear la tabla Empleados
            String tbEmpleados = "CREATE TABLE IF NOT EXISTS Empleados (" +
            "id_empleado INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT NOT NULL," +
            "cargo TEXT NOT NULL," +
            "fecha_contratacion TEXT NOT NULL);";
            
            // Crear la tabla Ventas
            String tbVentas = "CREATE TABLE IF NOT EXISTS Ventas (" +
            "id_venta INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_empleado INTEGER NOT NULL," +
            "id_producto INTEGER NOT NULL," +
            "cantidad INTEGER NOT NULL," +
            "fecha_venta TEXT NOT NULL," +
            "total_venta REAL NOT NULL," +
            "FOREIGN KEY (id_empleado) REFERENCES Empleados(id_empleado)," +
            "FOREIGN KEY (id_producto) REFERENCES Productos(id_producto));";

            stmt.executeUpdate(tbProductos);
            stmt.executeUpdate(tbEmpleados);
            stmt.executeUpdate(tbVentas);
            
            insertValues(conection);
            
            conection.commit();
            conection.close();
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void insertValues(Connection conection) {
        try {
            stmt = conection.createStatement();

            // Insertar datos en Productos
            String[] productos = {
                "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES ('Laptop', 'Electrónica', 1200.99, 10)",
                "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES ('Smartphone', 'Electrónica', 699.99, 15)",
                "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES ('Auriculares', 'Accesorios', 99.99, 30)",
                "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES ('Teclado', 'Periféricos', 49.99, 20)",
                "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES ('Mouse', 'Periféricos', 29.99, 25)",
                "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES ('Monitor', 'Electrónica', 249.99, 8)",
                "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES ('Impresora', 'Oficina', 150.00, 5)",
                "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES ('Cámara', 'Fotografía', 499.99, 7)",
                "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES ('Micrófono', 'Accesorios', 75.99, 12)",
                "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES ('Tablet', 'Electrónica', 329.99, 10)"
            };
            
            // Insertar datos en Empleados
            String[] empleados = {
                "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES ('Juan Pérez', 'Gerente', '2020-01-15')",
                "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES ('María López', 'Vendedor', '2019-06-23')",
                "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES ('Carlos Sánchez', 'Técnico', '2021-02-11')",
                "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES ('Ana González', 'Administrador', '2018-08-30')",
                "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES ('Pedro Rodríguez', 'Vendedor', '2017-09-19')",
                "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES ('Laura Martínez', 'Técnico', '2022-04-05')",
                "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES ('Diego Fernández', 'Gerente', '2016-12-01')",
                "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES ('Sofía Herrera', 'Vendedor', '2023-07-14')",
                "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES ('Antonio Ruiz', 'Administrador', '2015-10-22')",
                "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES ('Elena Castro', 'Técnico', '2020-03-29')"
            };
            
            // Insertar datos en Ventas
            String[] ventas = {
                "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (1, 1, 2, '2024-02-01', 2401.98)",
                "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (2, 2, 3, '2024-02-02', 2099.97)",
                "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (3, 5, 5, '2024-02-03', 149.95)",
                "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (4, 4, 1, '2024-02-04', 49.99)",
                "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (5, 3, 2, '2024-02-05', 199.98)",
                "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (6, 7, 1, '2024-02-06', 150.00)",
                "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (7, 8, 1, '2024-02-07', 499.99)",
                "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (8, 9, 3, '2024-02-08', 227.97)",
                "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (9, 6, 2, '2024-02-09', 499.98)",
                "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (10, 10, 1, '2024-02-10', 329.99)"
            };

            for (String sql : productos) stmt.executeUpdate(sql);
            for (String sql : empleados) stmt.executeUpdate(sql);
            for (String sql : ventas) stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
