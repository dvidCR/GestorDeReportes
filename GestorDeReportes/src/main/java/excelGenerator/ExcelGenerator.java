package excelGenerator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import database.OptionsBBDD;
import database.Query;
import model.Employes;
import model.Products;
import model.Sales;

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
        
        int columnCount = options.countColumns(tableName);
        List<String> columnName = options.getColumnName(tableName);
            
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columnCount; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnName.get(i));
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }
        
        int rowNum = 1;
        
        switch (tableName) {
        	
        	case "Productos":
        		List<Products> product = query.getProducts();
        		
        		for(Products products : product) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(products.getId_producto());
                    row.createCell(1).setCellValue(products.getNombre());
                    row.createCell(2).setCellValue(products.getCategoria());
                    row.createCell(3).setCellValue(products.getPrecio());
                    row.createCell(4).setCellValue(products.getStock());
                }
        		
        		break;
        		
        	case "Empleados":
        		List<Employes> employe = query.getEmployes();
        		
        		for(Employes employes : employe) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(employes.getId_empleado());
                    row.createCell(1).setCellValue(employes.getNombre());
                    row.createCell(2).setCellValue(employes.getCargo());
                    row.createCell(3).setCellValue(employes.getFecha_contratacion());
                }
        		
        		break;
        		
        	case "Ventas":
        		List<Sales> sale = query.getSales();
        		
        		for(Sales sales : sale) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(sales.getId_venta());
                    row.createCell(1).setCellValue(sales.getId_empleado());
                    row.createCell(2).setCellValue(sales.getId_producto());
                    row.createCell(3).setCellValue(sales.getCantidad());
                    row.createCell(4).setCellValue(sales.getFecha_venta());
                    row.createCell(5).setCellValue(sales.getTotal_venta());
                }
        		
        		break;
        }

        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}