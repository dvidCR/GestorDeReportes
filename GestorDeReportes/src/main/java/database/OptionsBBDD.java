package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para la lógica o querys concretas.
 * 
 * @author David Casado
 */
public class OptionsBBDD {
	
	private Connection c = null;
	private Statement stmt = null;
	
	/**
	 * Inicializa la conexión a la base de datos.
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
        try {
            if (c == null || c.isClosed()) {
            	Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:gestor.db");
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
        
	    return c;
    }
	
	/**
	 * Pide el nombre de las tablas de la base de datos.
	 * 
	 * @return List<String>
	 */
	public List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        try {
            String query = "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%';";
            c = getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                tableNames.add(rs.getString("name"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableNames;
    }
	
	/**
	 * Cuenta el número de columnas que tiene una tabla en concreto.
	 * 
	 * @param tableName
	 * @return int
	 */
	public int countColumns(String tableName) {
        int columnCount = 0;
        try {
            String query = "PRAGMA table_info(" + tableName + ");";
            c = getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                columnCount++;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnCount;
    }
	
	/**
	 * Pide el nombre de las columnas de una tabla en específico.
	 * 
	 * @param tableName
	 * @return List<String>
	 */
	public List<String> getColumnName(String tableName) {
        List<String> columnNames = new ArrayList<>();
        try {
            String query = "PRAGMA table_info(" + tableName + ");";
            c = getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                columnNames.add(rs.getString("name"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnNames;
    }
	
	/**
	 * Cierra la conexión a la base de datos
	 */
	 public void cerrarBBDD() {
	        try {
	            if (c != null) {
	                c.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
}
