package app;

import javax.swing.SwingUtilities;

/**
 * Clase para ejecutar el programa.
 * 
 * @author David Casado
 * @version 1.0
 */

public class App {
    public static void main(String[] args) {	
		SwingUtilities.invokeLater(() -> new Menu());
    }
}