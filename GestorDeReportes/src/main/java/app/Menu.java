package app;

import javax.swing.*;
import java.awt.*;

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
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        
        JButton viewContentButton = new JButton("Ver contenido");
        JButton createReportButton = new JButton("Generar Reporte");
        JButton exitButton = new JButton("Salir");
        
        viewContentButton.addActionListener(e -> new ViewContent());
        createReportButton.addActionListener(e -> new CreateReport());
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(viewContentButton);
        buttonPanel.add(createReportButton);
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
}