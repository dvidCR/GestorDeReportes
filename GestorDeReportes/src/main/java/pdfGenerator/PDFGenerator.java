package pdfGenerator;

import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import database.OptionsBBDD;
import database.Query;
import model.Employes;
import model.Products;
import model.Sales;

public class PDFGenerator {
	
	private static OptionsBBDD options = new OptionsBBDD();
	private static Query query = new Query();
	
	private final String filePath;
    private String fileName;

    public PDFGenerator(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }
	
	public void execute() {		
        try {
            PdfWriter writer = new PdfWriter(filePath + "/" + fileName);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph(fileName));

            generateTables(document);
            
            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private static void generateTables(Document document) {
		List<String> nameTable = options.getTableNames();
		for(int i = 0; i < nameTable.size(); i++) {
			String actualName = nameTable.get(i);

			document.add(new Paragraph("\n" + actualName));
			int numColumns = options.countColumns(actualName);
			
			Table table = new Table(numColumns);
			generateCells(table, actualName);
			document.add(table);
		}
	}
	
	private static void generateCells(Table table, String actualName) {
	    List<String> columnName = options.getColumnName(actualName);
	    
	    for (Object name : columnName) {
	    	table.addHeaderCell(name.toString());
	    }

	    switch (actualName) {
	        case "Productos":
	            List<Products> product = query.getProducts();
	            
	            for(Products products : product) {
	            	table.addCell(String.valueOf(products.getId_producto()));
	            	table.addCell(products.getNombre());
	            	table.addCell(products.getCategoria());
	            	table.addCell(String.valueOf(products.getPrecio()));
	            	table.addCell(String.valueOf(products.getStock()));
	            }
	            
	            break;

	        case "Empleados":
	            List<Employes> employe = query.getEmployes();
	            
	            for(Employes employes : employe) {
	            	table.addCell(String.valueOf(employes.getId_empleado()));
	            	table.addCell(employes.getNombre());
	            	table.addCell(employes.getCargo());
	            	table.addCell(employes.getFecha_contratacion());
	            }
	            
	            break;

	        case "Ventas":
	            List<Sales> sale = query.getSales();
	            
	            for(Sales sales : sale) {
	            	table.addCell(String.valueOf(sales.getId_venta()));
	            	table.addCell(String.valueOf(sales.getId_empleado()));
	            	table.addCell(String.valueOf(sales.getId_producto()));
	            	table.addCell(String.valueOf(sales.getCantidad()));
	            	table.addCell(sales.getFecha_venta());
	            	table.addCell(String.valueOf(sales.getTotal_venta()));
	            }
	            break;
	    }
	    	    
	}
}