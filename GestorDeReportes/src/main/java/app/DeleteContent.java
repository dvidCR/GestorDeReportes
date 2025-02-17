package app;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import database.Query;
import model.Employes;
import model.Products;
import model.Sales;

/**
 * Clase para borrar un elemento de la base de datos.
 * 
 * @author David Casado
 */
public class DeleteContent extends JFrame {
    private JComboBox<String> tableSelector;
    private JTextField idField;
    private Query query;
    
    /**
     * Constructor.
     * Inicializa la ventana.
     */
    public DeleteContent() {
        setTitle("Eliminar Contenido");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        query = new Query();

        JLabel titleLabel = new JLabel("Borrar Contenido", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        JLabel tableLabel = new JLabel("Seleccionar tabla:");
        tableSelector = new JComboBox<>(new String[]{"Productos", "Empleados", "Ventas"});

        JLabel idLabel = new JLabel("Introduce el ID del elemento que quieras borrar:");
        idField = new JTextField();

        JButton deleteButton = new JButton("Borrar");
        deleteButton.addActionListener(e -> {
            try {
                confirmDelete();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        centerPanel.add(tableLabel);
        centerPanel.add(tableSelector);
        centerPanel.add(idLabel);
        centerPanel.add(idField);
        centerPanel.add(new JLabel());
        centerPanel.add(deleteButton);

        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    /**
     * Ventana de confirmación para eliminar el elemento.
     * 
     * @throws SQLException Si ocurre un error al eliminar el elemento.
     */
    private void confirmDelete() throws SQLException {
        String selectedTable = (String) tableSelector.getSelectedItem();
        String idText = idField.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            String recordData = getRecordData(selectedTable, id);

            if (recordData == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el ID en la tabla seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int response = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que quieres borrar este registro?\n\n" + recordData,
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (response == JOptionPane.YES_OPTION) {
                deleteRecord(selectedTable, id);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Muestra el elemento completo que quieres borrar.
     * 
     * @param table La tabla en la que buscar el elemento.
     * @param id El ID del elemento a buscar.
     * @return Una cadena con los detalles del elemento, o null si no se encuentra.
     */
    private String getRecordData(String table, int id) {
        if ("Productos".equals(table)) {
            List<Products> products = query.getProducts();
            for (Products product : products) {
                if (product.getId_producto() == id) {
                    return "ID: " + product.getId_producto() +
                           "\nNombre: " + product.getNombre() +
                           "\nCategoría: " + product.getCategoria() +
                           "\nPrecio: " + product.getPrecio() +
                           "\nStock: " + product.getStock();
                }
            }
        } else if ("Empleados".equals(table)) {
            List<Employes> employees = query.getEmployes();
            for (Employes employee : employees) {
                if (employee.getId_empleado() == id) {
                    return "ID: " + employee.getId_empleado() +
                           "\nNombre: " + employee.getNombre() +
                           "\nCargo: " + employee.getCargo() +
                           "\nFecha de Contratación: " + employee.getFecha_contratacion();
                }
            }
        } else if ("Ventas".equals(table)) {
            List<Sales> sales = query.getSales();
            for (Sales sale : sales) {
                if (sale.getId_venta() == id) {
                    return "ID Venta: " + sale.getId_venta() +
                           "\nID Empleado: " + sale.getId_empleado() +
                           "\nID Producto: " + sale.getId_producto() +
                           "\nCantidad: " + sale.getCantidad() +
                           "\nFecha Venta: " + sale.getFecha_venta() +
                           "\nTotal Venta: " + sale.getTotal_venta();
                }
            }
        }
        return null;
    }
    
    /**
     * Borra el elemento seleccionado.
     * 
     * @param table La tabla de la que eliminar el elemento.
     * @param id El ID del elemento a eliminar.
     * @throws SQLException Si ocurre un error al eliminar el elemento.
     */
    private void deleteRecord(String table, int id) throws SQLException {
        boolean success = false;

        if ("Productos".equals(table)) {
            query.deleteProduct(id);
            success = true;
        } else if ("Empleados".equals(table)) {
            query.deleteEmploye(id);
            success = true;
        } else if ("Ventas".equals(table)) {
            query.deleteSale(id);
            success = true;
        }

        if (success) {
            JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}