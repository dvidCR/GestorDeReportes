package app;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import database.OptionsBBDD;
import database.Query;

/**
 * Clase para añadir contenido a una base de datos usando Java Swing.
 * 
 * @author David Casado
 */
public class AddContent extends JFrame {

    private OptionsBBDD options = new OptionsBBDD();
    private Query query = new Query();
    private JComboBox<String> tableSelector;
    private JPanel fieldsPanel;
    private JButton addButton;
    private JTextField[] textFields;
    private List<String> columnNames;
    
    /**
     * Constructor.
     * Inicializa la ventana.
     */
    public AddContent() {
        setTitle("Añadir contenido");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Añadir Contenido");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        tableSelector = new JComboBox<>();
        for (String table : options.getTableNames()) {
            tableSelector.addItem(table);
        }

        tableSelector.addActionListener(e -> loadFields());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Selecciona tabla:"));
        topPanel.add(tableSelector);
        add(topPanel, BorderLayout.NORTH);

        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(0, 2, 5, 5));
        add(fieldsPanel, BorderLayout.CENTER);

        addButton = new JButton("Añadir");
        addButton.addActionListener(e -> insertData());

        add(addButton, BorderLayout.SOUTH);

        if (tableSelector.getItemCount() > 0) {
            loadFields();
        }
        
        setVisible(true);
    }
    
    /**
     * Carga dinámicamente los datos de la tabla seleccionada.
     */
    private void loadFields() {
        fieldsPanel.removeAll();
        
        String selectedTable = (String) tableSelector.getSelectedItem();
        if (selectedTable == null) {
            return;
        }

        columnNames = options.getColumnName(selectedTable);
        if (columnNames.isEmpty()) {
            return;
        }

        textFields = new JTextField[columnNames.size() - 1];

        for (int i = 1; i < columnNames.size(); i++) {
            JLabel label = new JLabel(columnNames.get(i) + ":");
            JTextField textField = new JTextField(15);
            textFields[i - 1] = textField;
            fieldsPanel.add(label);
            fieldsPanel.add(textField);
        }

        fieldsPanel.revalidate();
        fieldsPanel.repaint();
    }
    
    /**
     * Inserta los datos en la tabla seleccionada.
     * 
     * @throws SQLException Si ocurre un error al insertar los datos.
     */
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