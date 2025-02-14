package excelGenerator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import database.OptionsBBDD;
import database.Query;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {
    private static OptionsBBDD options = new OptionsBBDD();
    private static Query query = new Query();

    private final String filePath;
    private final String fileName;

    public ExcelGenerator(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public void generateExcel() {
        List<String> tables = options.getTableNames();

        try (Workbook workbook = new XSSFWorkbook()) {
            for (String table : tables) {
                createSheetForTable(workbook, table);
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath + "/" + fileName)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSheetForTable(Workbook workbook, String tableName) {
        Sheet sheet = workbook.createSheet(tableName);
        
        List<?> content = null; 
        
        switch (tableName) {
        	
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
        
        int columnCount = options.countColumns(tableName);
        List<String> columnName = options.getColumnName(tableName);
            
        Row headerRow = sheet.createRow(0);
        for (int i = 1; i < columnCount; i++) {
            Cell cell = headerRow.createCell(i - 1);
            cell.setCellValue(columnName.get(i));
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }

        int rowNum = 1;
        for(Object c: content) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 1; i < columnCount; i++) {
                row.createCell(i - 1).setCellValue(String.valueOf(c));
            }
        }

        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}