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
	
	
}
