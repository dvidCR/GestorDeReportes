package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.CreateBBDD;
import database.Query;
import model.Employes;
import model.Products;
import model.Sales;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ViewContent extends JFrame {
    private JTextField filterField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> tableSelector;
    private Query query;
    
    public ViewContent() {
        setTitle("Contenido BBDD");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        query = new Query();
        
        JLabel titleLabel = new JLabel("Contenido BBDD", SwingConstants.CENTER);
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

        filterField = new JTextField(20);
        filterField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTable(filterField.getText());
            }
        });
        
        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filtrar:"));
        filterPanel.add(filterField);
        add(filterPanel, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadTableData();
        setVisible(true);
    }

    private void loadTableData() {
        String selectedTable = (String) tableSelector.getSelectedItem();
        tableModel.setRowCount(0);
        
        try {
            if ("Productos".equals(selectedTable)) {
                tableModel.setColumnIdentifiers(new String[]{"Nombre", "Categoría", "Precio", "Stock"});
                List<Products> products = query.getProducts();
                if (products.isEmpty()) throw new Exception();
                for (Products product : products) {
                    tableModel.addRow(new Object[]{product.getNombre(), product.getCategoria(), product.getPrecio(), product.getStock()});
                }
            } else if ("Empleados".equals(selectedTable)) {
                tableModel.setColumnIdentifiers(new String[]{"Nombre", "Cargo", "Fecha de Contratación"});
                List<Employes> employees = query.getEmployes();
                if (employees.isEmpty()) throw new Exception();
                for (Employes employee : employees) {
                    tableModel.addRow(new Object[]{employee.getNombre(), employee.getCargo(), employee.getFecha_contratacion()});
                }
            } else if ("Ventas".equals(selectedTable)) {
                tableModel.setColumnIdentifiers(new String[]{"ID Empleado", "ID Producto", "Cantidad", "Fecha Venta", "Total Venta"});
                List<Sales> sales = query.getSales();
                if (sales.isEmpty()) throw new Exception();
                for (Sales sale : sales) {
                    tableModel.addRow(new Object[]{sale.getId_empleado(), sale.getId_producto(), sale.getCantidad(), sale.getFecha_venta(), sale.getTotal_venta()});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No hay datos en la tabla. Creando base de datos...", "Aviso", JOptionPane.WARNING_MESSAGE);
            new CreateBBDD().crearTabla();
            loadTableData();
        }
    }

    private void filterTable(String query) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(query, 0));
    }
}