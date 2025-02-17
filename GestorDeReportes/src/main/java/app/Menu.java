package app;

import javax.swing.*;

import database.CreateBBDD;
import database.OptionsBBDD;

import java.awt.*;
import java.io.File;

/**
 * Clase menu para seleccionar una accion a la base de datos
 * 
 * @author David
 */
public class Menu extends JFrame {
	
	/**
	 * Constructor.
	 * Inicializa la ventana.
	 */
    public Menu() {
        setTitle("Gestor De Reportes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Gestor De Reportes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10));
        
        JButton viewContentButton = new JButton("Ver contenido");
        JButton addContentButton = new JButton("Añadir contenido");
        JButton updateContentButton = new JButton("Actualizar contenido");
        JButton deleteContentButton = new JButton("Borrar contenido");
        JButton createReportButton = new JButton("Generar Reporte");
        JButton exitButton = new JButton("Salir");
        
        viewContentButton.addActionListener(e -> new ViewContent());
        addContentButton.addActionListener(e -> new AddContent());
        updateContentButton.addActionListener(e -> new UpdateContent());
        deleteContentButton.addActionListener(e -> new DeleteContent());
        createReportButton.addActionListener(e -> new CreateReport());
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(viewContentButton);
        buttonPanel.add(addContentButton);
        buttonPanel.add(updateContentButton);
        buttonPanel.add(deleteContentButton);
        buttonPanel.add(createReportButton);
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.CENTER);
        
        setVisible(true);
        
        start();
    }
    
    /**
     * Crea la base de datos si no existe y le genera las tablas.
     */
    public void start() {
    	if (!new File("gestor.db").exists()) {
    		OptionsBBDD options = new OptionsBBDD();
			options.getConnection();
			
			new CreateBBDD().crearTabla();
    	}
    }
}