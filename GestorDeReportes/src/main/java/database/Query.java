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

/**
 * Clase CRUD
 * 
 * @author David Casado
 */
public class Query {
	
	public OptionsBBDD options = new OptionsBBDD();

	
	/**
	 * Método para obtener los productos.
	 * 
	 * @return List<Products>
	 */
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
	
	/**
	 * Método para obtener los empleados.
	 * 
	 * @return List<Employes>
	 */
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
    
    /**
     * Método para obtener las ventas.
     * 
     * @return List<Sales>
     */
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
    
    /**
     * Método para insertar los productos.
     * 
     * @param nombre
     * @param categoria
     * @param precio
     * @param stock
     * @throws SQLException
     */
    public void setProducts(String nombre, String categoria, float precio, int stock) throws SQLException {
        String sql = "INSERT INTO Productos (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";

        try (Connection conection = options.getConnection();
             PreparedStatement pstmt = conection.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, categoria);
            pstmt.setFloat(3, precio);
            pstmt.setInt(4, stock);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Método para insertar los empleados
     * 
     * @param nombre
     * @param cargo
     * @param fecha_contratacion
     * @throws SQLException
     */
    public void setEmployes(String nombre, String cargo, String fecha_contratacion) throws SQLException {
        String sql = "INSERT INTO Empleados (nombre, cargo, fecha_contratacion) VALUES (?, ?, ?)";

        try (Connection conection = options.getConnection();
             PreparedStatement pstmt = conection.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, cargo);
            pstmt.setString(3, fecha_contratacion);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Método para insertar las ventas
     * 
     * @param id_empleado
     * @param id_producto
     * @param cantidad
     * @param fecha_venta
     * @param total_venta
     * @throws SQLException
     */
    public void setSales(int id_empleado, int id_producto, int cantidad, String fecha_venta, float total_venta) throws SQLException {
        String sql = "INSERT INTO Ventas (id_empleado, id_producto, cantidad, fecha_venta, total_venta) VALUES (?, ?, ?, ?, ?)";

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
            throw e;
        }
    }
    
    /**
     * Método para actualizar un producto
     * 
     * @param id
     * @param nombre
     * @param categoria
     * @param precio
     * @param stock
     * @throws SQLException
     */
    public void updateProduct(int id, String nombre, String categoria, float precio, int stock) throws SQLException {
        String sql = "UPDATE Productos SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id_producto = ?";
        try (Connection connection = options.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, categoria);
            pstmt.setFloat(3, precio);
            pstmt.setInt(4, stock);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Método para actualizar a un empleado
     * 
     * @param id
     * @param nombre
     * @param cargo
     * @param fecha_contratacion
     * @throws SQLException
     */
    public void updateEmploye(int id, String nombre, String cargo, String fecha_contratacion) throws SQLException {
        String sql = "UPDATE Empleados SET nombre = ?, cargo = ?, fecha_contratacion = ? WHERE id_empleado = ?";
        try (Connection connection = options.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, cargo);
            pstmt.setString(3, fecha_contratacion);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Método para actualizar una venta
     * 
     * @param id
     * @param id_empleado
     * @param id_producto
     * @param cantidad
     * @param fecha_venta
     * @param total_venta
     * @throws SQLException
     */
    public void updateSale(int id, int id_empleado, int id_producto, int cantidad, String fecha_venta, float total_venta) throws SQLException {
        String sql = "UPDATE Ventas SET id_empleado = ?, id_producto = ?, cantidad = ?, fecha_venta = ?, total_venta = ? WHERE id_venta = ?";
        try (Connection connection = options.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id_empleado);
            pstmt.setInt(2, id_producto);
            pstmt.setInt(3, cantidad);
            pstmt.setString(4, fecha_venta);
            pstmt.setFloat(5, total_venta);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Método para eliminar un producto
     * 
     * @param id
     * @throws SQLException
     */
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Productos WHERE id_producto = ?";
        try (Connection connection = options.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Método para eliminar a un empleado
     * 
     * @param id
     * @throws SQLException
     */
    public void deleteEmploye(int id) throws SQLException {
        String sql = "DELETE FROM Empleados WHERE id_empleado = ?";
        try (Connection connection = options.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Método para eliminar una venta
     * 
     * @param id
     * @throws SQLException
     */
    public void deleteSale(int id) throws SQLException {
        String sql = "DELETE FROM Ventas WHERE id_venta = ?";
        try (Connection connection = options.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
	
}
