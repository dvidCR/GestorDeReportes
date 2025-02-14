package app;

import javax.swing.*;
import java.awt.*;

public class CreateReport extends JFrame {
    public CreateReport() {
        setTitle("Generar Reporte");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JButton pdfButton = new JButton("Generar PDF");
        JButton excelButton = new JButton("Generar Excel");
        JButton bothButton = new JButton("Generar Ambos");

        pdfButton.addActionListener(e -> new SelectPath("PDF"));
        excelButton.addActionListener(e -> new SelectPath("Excel"));
        bothButton.addActionListener(e -> new SelectPath("Both"));

        buttonPanel.add(pdfButton);
        buttonPanel.add(excelButton);

        JPanel centerPanel = new JPanel();
        centerPanel.add(bothButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}