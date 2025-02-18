package testDatabase;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import database.OptionsBBDD;
import database.Query;
import model.Employes;
import model.Products;
import model.Sales;

public class TestQuery {

	@Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;
    
    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private OptionsBBDD mockOptionsBBDD; // Mock de OptionsBBDD

    private Query query;

    @Before
    public void Mock() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Crear una instancia de Query y asignarle el mock de OptionsBBDD
        query = new Query();
        query.options = mockOptionsBBDD; // Inyectar el mock

        // Configurar el mock de OptionsBBDD para devolver la conexión mockeada
        when(mockOptionsBBDD.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testGetProducts() throws Exception {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id_producto")).thenReturn(1);
        when(mockResultSet.getString("nombre")).thenReturn("Producto 1");
        when(mockResultSet.getString("categoria")).thenReturn("Categoria 1");
        when(mockResultSet.getFloat("precio")).thenReturn(10.0f);
        when(mockResultSet.getInt("stock")).thenReturn(5);

        List<Products> products = query.getProducts();

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Producto 1", products.get(0).getNombre());
    }

    @Test
    public void testGetEmployes() throws Exception {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id_empleado")).thenReturn(1);
        when(mockResultSet.getString("nombre")).thenReturn("Empleado 1");
        when(mockResultSet.getString("cargo")).thenReturn("empleado");
        when(mockResultSet.getString("fecha_contratacion")).thenReturn("2023-01-01");

        List<Employes> employes = query.getEmployes();

        assertNotNull(employes);
        assertEquals(1, employes.size());
        assertEquals("Empleado 1", employes.get(0).getNombre());
    }

    @Test
    public void testGetSales() throws Exception {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id_venta")).thenReturn(1);
        when(mockResultSet.getInt("id_empleado")).thenReturn(1);
        when(mockResultSet.getInt("id_producto")).thenReturn(1);
        when(mockResultSet.getInt("cantidad")).thenReturn(2);
        when(mockResultSet.getString("fecha_venta")).thenReturn("2023-01-01");
        when(mockResultSet.getFloat("total_venta")).thenReturn(20.0f);

        List<Sales> sales = query.getSales();

        assertNotNull(sales);
        assertEquals(1, sales.size());
        assertEquals(1, sales.get(0).getId_venta());
    }

    @Test
    public void testSetProducts() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        query.setProducts("Producto 1", "Categoria 1", 10.0f, 5);

        verify(mockPreparedStatement).setString(1, "Producto 1");
        verify(mockPreparedStatement).setString(2, "Categoria 1");
        verify(mockPreparedStatement).setFloat(3, 10.0f);
        verify(mockPreparedStatement).setInt(4, 5);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testUpdateProduct() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        query.updateProduct(1, "Producto 1", "Categoria 1", 10.0f, 5);

        verify(mockPreparedStatement).setString(1, "Producto 1");
        verify(mockPreparedStatement).setString(2, "Categoria 1");
        verify(mockPreparedStatement).setFloat(3, 10.0f);
        verify(mockPreparedStatement).setInt(4, 5);
        verify(mockPreparedStatement).setInt(5, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteProduct() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        query.deleteProduct(1);

        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeUpdate();
    }
}