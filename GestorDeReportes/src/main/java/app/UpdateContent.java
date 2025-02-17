package app;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.Query;
import model.Employes;
import model.Products;
import model.Sales;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class UpdateContent extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> tableSelector;
    private JTextField searchField;
    private Query query;
    private TableModelListener tableModelListener;
    private TableRowSorter<DefaultTableModel> sorter;

    public UpdateContent() {
        setTitle("Actualizar Contenido");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        query = new Query();

        JLabel titleLabel = new JLabel("Actualizar Contenido", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout());

        tableSelector = new JComboBox<>(new String[]{"Productos", "Empleados", "Ventas"});
        tableSelector.addActionListener(e -> loadTableData());

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadTableData());

        topPanel.add(tableSelector);
        topPanel.add(refreshButton);
        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // El ID no se puede editar
            }
        };
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Barra de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel searchLabel = new JLabel("Buscar:");
        searchField = new JTextField(20);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTable(searchField.getText());
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.SOUTH);

        loadTableData();
        setVisible(true);
    }

    private void loadTableData() {
        String selectedTable = (String) tableSelector.getSelectedItem();
        tableModel.setRowCount(0);

        // **Eliminar el listener antiguo antes de modificar el modelo**
        if (tableModelListener != null) {
            tableModel.removeTableModelListener(tableModelListener);
        }

        try {
            if ("Productos".equals(selectedTable)) {
                tableModel.setColumnIdentifiers(new String[]{"ID", "Nombre", "Categoría", "Precio", "Stock"});
                List<Products> products = query.getProducts();

                for (Products product : products) {
                    tableModel.addRow(new Object[]{product.getId_producto(), product.getNombre(), product.getCategoria(), product.getPrecio(), product.getStock()});
                }
            } else if ("Empleados".equals(selectedTable)) {
                tableModel.setColumnIdentifiers(new String[]{"ID", "Nombre", "Cargo", "Fecha de Contratación"});
                List<Employes> employees = query.getEmployes();

                for (Employes employee : employees) {
                    tableModel.addRow(new Object[]{employee.getId_empleado(), employee.getNombre(), employee.getCargo(), employee.getFecha_contratacion()});
                }
            } else if ("Ventas".equals(selectedTable)) {
                tableModel.setColumnIdentifiers(new String[]{"ID", "ID Empleado", "ID Producto", "Cantidad", "Fecha Venta", "Total Venta"});
                List<Sales> sales = query.getSales();

                for (Sales sale : sales) {
                    tableModel.addRow(new Object[]{sale.getId_venta(), sale.getId_empleado(), sale.getId_producto(), sale.getCantidad(), sale.getFecha_venta(), sale.getTotal_venta()});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // **Crear un nuevo listener y agregarlo después de cargar los datos**
        tableModelListener = e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                updateDatabase(e.getFirstRow(), e.getColumn());
            }
        };
        tableModel.addTableModelListener(tableModelListener);

        // **Inicializar el sorter para filtrar la tabla**
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
    }

    private void updateDatabase(int row, int column) {
        String selectedTable = (String) tableSelector.getSelectedItem();
        int id = (int) tableModel.getValueAt(row, 0);

        try {
            if ("Productos".equals(selectedTable)) {
                query.updateProduct(id,
                        (String) tableModel.getValueAt(row, 1),
                        (String) tableModel.getValueAt(row, 2),
                        Float.parseFloat(tableModel.getValueAt(row, 3).toString()),
                        Integer.parseInt(tableModel.getValueAt(row, 4).toString()));
            } else if ("Empleados".equals(selectedTable)) {
                query.updateEmploye(id,
                        (String) tableModel.getValueAt(row, 1),
                        (String) tableModel.getValueAt(row, 2),
                        (String) tableModel.getValueAt(row, 3));
            } else if ("Ventas".equals(selectedTable)) {
                query.updateSale(id,
                        Integer.parseInt(tableModel.getValueAt(row, 1).toString()),
                        Integer.parseInt(tableModel.getValueAt(row, 2).toString()),
                        Integer.parseInt(tableModel.getValueAt(row, 3).toString()),
                        (String) tableModel.getValueAt(row, 4),
                        Float.parseFloat(tableModel.getValueAt(row, 5).toString()));
            }
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterTable(String query) {
        if (sorter != null) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query)); // Filtro sin distinguir mayúsculas/minúsculas
        }
    }
}