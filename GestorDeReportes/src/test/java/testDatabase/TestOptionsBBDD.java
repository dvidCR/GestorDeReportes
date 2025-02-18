package testDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import database.OptionsBBDD;

public class TestOptionsBBDD {
	
	private OptionsBBDD options = new OptionsBBDD();
	
	@Test
	public void getTableNames() {
		List<String> tableNames = new ArrayList<>();
		
		tableNames.add("Productos");
		tableNames.add("Empleados");
		tableNames.add("Ventas");
		
		assertEquals(tableNames, options.getTableNames());
	}
	
	@Test
	public void columnName() {
		assertEquals(5, options.countColumns("Productos"));
		assertEquals(4, options.countColumns("Empleados"));
		assertEquals(6, options.countColumns("Ventas"));
	}
	
	@Test
	public void getColumnName() {
		assertEquals("id_producto", options.getColumnName("Productos").get(0));
		assertEquals("precio", options.getColumnName("Productos").get(3));
		assertEquals("fecha_contratacion", options.getColumnName("Empleados").get(3));
		assertEquals("cargo", options.getColumnName("Empleados").get(2));
		assertEquals("id_producto", options.getColumnName("Ventas").get(2));
		assertEquals("fecha_venta", options.getColumnName("Ventas").get(4));
	}
	
}
