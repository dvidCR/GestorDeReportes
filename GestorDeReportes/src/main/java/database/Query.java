package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employes;
import model.Products;
import model.Sales;

public class Query {
	
	private OptionsBBDD options = new OptionsBBDD();

	
	// Método para obtener los productos
    public List<Products> getProducts() {
        List<Products> list = new ArrayList<Products>();
        try (Connection conection = options.getConnection();
    		Statement stmt = conection.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM Productos")) {

            while (rs.next()) {
            	Products products = new Products(rs.getInt("id_producto"), rs.getString("nombre"), rs.getString("categoria"), rs.getFloat("precio"), rs.getInt("stock"));
                list.add(products);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	// Método para obtener los empleados
    public List<Employes> getEmployes() {
        List<Employes> list = new ArrayList<Employes>();
        try (Connection conection = options.getConnection();
    		Statement stmt = conection.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM Empleados")) {

            while (rs.next()) {
            	Employes employes = new Employes(rs.getInt("id_empleado"), rs.getString("nombre"), rs.getString("cargo"), rs.getString("fecha_contratacion"));
                list.add(employes);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Método para obtener las ventas
    public List<Sales> getSales() {
        List<Sales> list = new ArrayList<Sales>();
        try (Connection conection = options.getConnection();
    		Statement stmt = conection.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM Ventas")) {

            while (rs.next()) {
            	Sales sales = new Sales(rs.getInt("id_venta"), rs.getInt("id_empleado"), rs.getInt("id_producto"), rs.getInt("cantidad"), rs.getString("fecha_venta"), rs.getFloat("total_venta"));
                list.add(sales);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
 // Método para insertar productos usando PreparedStatement
    public void setProducts(String nombre, String categoria, float precio, int stock) throws SQLException {
        String sql = "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES (" +
				nombre + ", " +
				categoria + ", " +
				precio + ", " +
				stock + ")";

        try (Connection conection = options.getConnection();
             PreparedStatement pstmt = conection.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, categoria);
            pstmt.setFloat(3, precio);
            pstmt.setInt(4, stock);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // rethrow the exception after logging it
        }
    }

    // Método para insertar empleados usando PreparedStatement
    public void setEmployes(String nombre, String cargo, String fecha_contratacion) throws SQLException {
        String sql = "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES (" +
				nombre + ", " +
				cargo + ", " +
				fecha_contratacion + ")";

        try (Connection conection = options.getConnection();
             PreparedStatement pstmt = conection.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, cargo);
            pstmt.setString(3, fecha_contratacion);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // rethrow the exception after logging it
        }
    }

    // Método para insertar ventas usando PreparedStatement
    public void setSales(int id_empleado, int id_producto, int cantidad, String fecha_venta, float total_venta) throws SQLException {
        String sql = "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (" +
				id_empleado + ", " +
				id_producto + ", " +
				cantidad + ", " +
				fecha_venta + ", " +
				total_venta + ")";

        try (Connection conection = options.getConnection();
             PreparedStatement pstmt = conection.prepareStatement(sql)) {

            pstmt.setInt(1, id_empleado);
            pstmt.setInt(2, id_producto);
            pstmt.setInt(3, cantidad);
            pstmt.setString(4, fecha_venta);
            pstmt.setFloat(5, total_venta);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // rethrow the exception after logging it
        }
    }
	
}
