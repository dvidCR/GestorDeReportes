package app;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import database.OptionsBBDD;
import database.Query;

public class AddContent extends JFrame {

    private OptionsBBDD options = new OptionsBBDD();
    private Query query = new Query();
    private JComboBox<String> tableSelector;
    private JPanel fieldsPanel;
    private JButton addButton;
    private JTextField[] textFields;
    private List<String> columnNames;

    public AddContent() {
    	
        setTitle("Añadir contenido");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear etiqueta principal
        JLabel titleLabel = new JLabel("Añadir Contenido");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // ComboBox para seleccionar la tabla
        tableSelector = new JComboBox<>();
        for (String table : options.getTableNames()) {
            tableSelector.addItem(table);
        }

        tableSelector.addActionListener(e -> loadFields());

        // Panel para el selector de tabla
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Selecciona tabla:"));
        topPanel.add(tableSelector);
        add(topPanel, BorderLayout.NORTH);

        // Panel para los campos de entrada
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(0, 2, 5, 5)); // 2 columnas (etiqueta - campo)
        add(fieldsPanel, BorderLayout.CENTER);

        // Botón para añadir datos
        addButton = new JButton("Añadir");
        addButton.addActionListener(e -> insertData());

        add(addButton, BorderLayout.SOUTH);

        // Cargar los campos inicialmente
        if (tableSelector.getItemCount() > 0) {
            loadFields();
        }
        
        setVisible(true);
        
    }

    private void loadFields() {
        fieldsPanel.removeAll();
        
        String selectedTable = (String) tableSelector.getSelectedItem();
        if (selectedTable == null) {
            return; // Evita errores si no hay tablas disponibles
        }

        columnNames = options.getColumnName(selectedTable);
        if (columnNames.isEmpty()) {
            return; // Evita errores si la tabla no tiene columnas
        }

        textFields = new JTextField[columnNames.size() - 1]; // Omitimos el ID

        for (int i = 1; i < columnNames.size(); i++) { // Empezamos en 1 para omitir ID
            JLabel label = new JLabel(columnNames.get(i) + ":");
            JTextField textField = new JTextField(15);
            textFields[i - 1] = textField; // Guarda en el array
            fieldsPanel.add(label);
            fieldsPanel.add(textField);
        }

        fieldsPanel.revalidate();
        fieldsPanel.repaint();
    }

    private void insertData() {
    	try {
	        String selectedTable = (String) tableSelector.getSelectedItem();
	        String[] values = new String[columnNames.size() - 1];
	
	        for (int i = 1; i < columnNames.size(); i++) {
	            values[i - 1] = textFields[i - 1].getText();
	        }
	
	        switch (selectedTable) {
	            case "Productos":
	                query.setProducts(values[0], values[1], Float.parseFloat(values[2]), Integer.parseInt(values[3]));
	                break;
	            case "Empleados":
	                query.setEmployes(values[0], values[1], values[2]);
	                break;
	            case "Ventas":
	                query.setSales(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), values[3], Float.parseFloat(values[4]));
	                break;
	            default:
	                JOptionPane.showMessageDialog(this, "Tabla no reconocida", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	        }
	
	        JOptionPane.showMessageDialog(this, "Datos añadidos correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    	} catch (SQLException e) {
    		JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    }
}