package com.dbses.exsql.service;

import com.dbses.exsql.bo.Table;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelService {

    public Map<String, List<Table>> readExcel(String filePath) throws IOException {
        Map<String, List<Table>> result = new HashMap<>();
        HSSFWorkbook workbook = getWorkBook(filePath).orElseThrow(() -> new RuntimeException("IO Error"));
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            List<Table> tables = new ArrayList<>();
            // 得到Excel工作表对象
            HSSFSheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            // 循环读取表格数据
            for (Row row : sheet) {
                // 首行（即表头）不读取
                if (row.getRowNum() == 0) {
                    continue;
                }
                Table table = new Table();
                //读取当前行中单元格数据，索引从0开始
                Optional.ofNullable(row.getCell(0)).ifPresent(cell -> table.setTableName(cell.getStringCellValue()));
                Optional.ofNullable(row.getCell(1)).ifPresent(cell -> table.setFieldName(cell.getStringCellValue()));
                Optional.ofNullable(row.getCell(2)).ifPresent(cell -> table.setChineseName(cell.getStringCellValue()));
                Optional.ofNullable(row.getCell(3)).ifPresent(cell -> table.setDataType(cell.getStringCellValue()));
                Optional.ofNullable(row.getCell(4)).ifPresent(cell -> table.setAutoIncrease("是".equals(cell.getStringCellValue())));
                Optional.ofNullable(row.getCell(5)).ifPresent(cell -> table.setRemark(cell.getStringCellValue()));
                if (StringUtils.isNotEmpty(table.getFieldName())) {
                    tables.add(table);
                }
            }
            result.put(sheetName, tables);
        }
        // 关闭流
        workbook.close();
        return result;
    }

    private Optional<HSSFWorkbook> getWorkBook(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(filePath);
            return Optional.of(new HSSFWorkbook(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
