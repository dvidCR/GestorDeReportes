package database;

import java.sql.*;

public class CreateBBDD {
    
	private OptionsBBDD options;
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
            
            conection.commit();
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}