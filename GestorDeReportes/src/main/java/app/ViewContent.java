package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.Query;
import model.Products;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class ViewContent extends JFrame {
    private JTextField filterField;
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewContent() {
        setTitle("Contenido BBDD");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Contenido BBDD", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        filterField = new JTextField();
        filterField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTable(filterField.getText());
            }
        });
        add(filterField, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel(new String[]{"Nombre", "Categoría", "Precio", "Stock"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadTableData();
        setVisible(true);
    }

    private void loadTableData() {
        Query query = new Query();
        List<Products> products = query.getProducts();
        
        for (Products product : products) {
            tableModel.addRow(new Object[]{product.getNombre(), product.getCategoria(), product.getPrecio(), product.getStock()});
        }
    }

    private void filterTable(String query) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(query, 0));
    }
}