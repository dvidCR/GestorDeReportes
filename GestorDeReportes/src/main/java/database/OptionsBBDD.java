package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OptionsBBDD {
	
	private Connection c = null;
	
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
