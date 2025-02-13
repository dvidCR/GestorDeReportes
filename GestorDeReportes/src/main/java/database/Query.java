package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employes;
import model.Products;
import model.Sales;

public class Query {
	
	private OptionsBBDD options;
	
	// Método para obtener los productos
    public List<Products> getProducts() {
        List<Products> list = new ArrayList<Products>();
        try (Connection conection = options.getConnection();
    		Statement stmt = conection.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM Productos")) {

            while (rs.next()) {
            	Products products = new Products(rs.getString("nombre"), rs.getString("categoria"), rs.getFloat("precio"), rs.getInt("stock"));
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
            	Employes employes = new Employes(rs.getString("nombre"), rs.getString("cargo"), rs.getString("fecha_contratacion"));
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
            	Sales sales = new Sales(rs.getInt("id_empleado"), rs.getInt("id_producto"), rs.getInt("cantidad"), rs.getString("fehca_venta"), rs.getFloat("total_venta"));
                list.add(sales);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
}
