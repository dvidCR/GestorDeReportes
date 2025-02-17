package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import pdfGenerator.PDFGenerator;
import excelGenerator.ExcelGenerator;

public class SelectPath extends JFrame {
    private JTextField fileNameField;
    private JTextField filePathField;
    private JButton browseButton;
    private JButton saveButton;
    private String reportType; // "PDF", "Excel" o "Both"

    public SelectPath(String reportType) {
        this.reportType = reportType;

        setTitle("Seleccionar ubicación de guardado");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Nombre del archivo:"));
        fileNameField = new JTextField();
        add(fileNameField);

        add(new JLabel("Ubicación:"));
        filePathField = new JTextField();
        filePathField.setEditable(false);
        add(filePathField);

        browseButton = new JButton("Seleccionar Ubicación");
        browseButton.addActionListener(this::chooseDirectory);
        add(browseButton);

        saveButton = new JButton("Guardar");
        saveButton.addActionListener(this::generateReport);
        add(saveButton);

        setVisible(true);
    }

    private void chooseDirectory(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDir = fileChooser.getSelectedFile();
            filePathField.setText(selectedDir.getAbsolutePath());
        }
    }

    private void generateReport(ActionEvent e) {
        String fileName = fileNameField.getText().trim();
        String filePath = filePathField.getText().trim();

        if (fileName.isEmpty() || filePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre y seleccione una ubicación", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!fileName.endsWith(".pdf") && reportType.equals("PDF")) {
            fileName += ".pdf";
        } else if (!fileName.endsWith(".xlsx") && reportType.equals("Excel")) {
            fileName += ".xlsx";
        }

        switch (reportType) {
            case "PDF":
                new PDFGenerator(filePath, fileName).execute();
                break;
            case "Excel":
                new ExcelGenerator(filePath, fileName).generateExcel();
                break;
            case "Both":
                new PDFGenerator(filePath, fileName + ".pdf").execute();
                new ExcelGenerator(filePath, fileName + ".xlsx").generateExcel();
                break;
        }

        JOptionPane.showMessageDialog(this, "Archivo guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}