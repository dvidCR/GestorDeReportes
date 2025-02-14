package app;

import javax.swing.*;

import database.CreateBBDD;
import database.OptionsBBDD;

import java.awt.*;
import java.io.File;

public class Menu extends JFrame {
	
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
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        
        JButton viewContentButton = new JButton("Ver contenido");
        JButton addContentButton = new JButton("Añadir contenido");
        JButton createReportButton = new JButton("Generar Reporte");
        JButton exitButton = new JButton("Salir");
        
        viewContentButton.addActionListener(e -> new ViewContent());
        addContentButton.addActionListener(e -> new AddContent());
        createReportButton.addActionListener(e -> new CreateReport());
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(viewContentButton);
        buttonPanel.add(addContentButton);
        buttonPanel.add(createReportButton);
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.CENTER);
        
        setVisible(true);
        
        start();
    }
    
    public void start() {
    	if (!new File("gestor.db").exists()) {
    		OptionsBBDD options = new OptionsBBDD();
			options.getConnection();
			
			new CreateBBDD().crearTabla();
    	}
    }
}