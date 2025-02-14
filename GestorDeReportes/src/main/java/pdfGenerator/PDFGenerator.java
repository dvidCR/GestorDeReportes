package pdfGenerator;

import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import database.OptionsBBDD;
import database.Query;

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
	    List<?> content = null;
	    List<String> columnName = options.getColumnName(actualName);

	    switch (actualName) {
	        case "Productos":
	            content = query.getProducts();
	            break;

	        case "Empleados":
	            content = query.getEmployes();
	            break;

	        case "Ventas":
	            content = query.getSales();
	            break;
	    }
	    
	    for (Object name : columnName) {
	    	table.addHeaderCell(String.valueOf(name));
	    }
	    
	    for (Object c : content) {
	        table.addCell(String.valueOf(c));
	    }
	}
}