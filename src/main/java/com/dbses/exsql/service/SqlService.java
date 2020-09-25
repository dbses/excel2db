package com.dbses.exsql.service;

import com.dbses.exsql.bo.Table;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SqlService {

    private static final String CREATE_TABLE = "CREATE TABLE";
    private static final String BRACKET_LETF = "(";
    private static final String BRACKET_RIGHT = ")";
    private static final String COMMENT = "COMMENT";
    private static final String AUTO_INCREMENT = "AUTO INCREMENT";
    private static final String EMPTY = " ";
    private static final String QUOTES = "'";
    private static final String COMMA = ",";
    private static final String ENGINE = "ENGINE=InnoDB";
    private static final String SEMICOLON = ";";
    private static final String BREAK_LINE = "\r\n";

    @Autowired
    private ExcelService excelService;

    public String getSql(String filePath) throws IOException {
        Map<String, List<Table>> tablesMap = excelService.readExcel(filePath);
        StringBuilder sql = new StringBuilder();
        for (Map.Entry<String, List<Table>> entry : tablesMap.entrySet()) {
            String tableName = entry.getKey();
            List<Table> tables = entry.getValue();
            sql.append("-- 创建").append(tableName.toUpperCase()).append(BREAK_LINE);
            sql.append("DROP TABLE IF EXISTS ").append(tableName).append(";").append(BREAK_LINE);
            sql.append(CREATE_TABLE).append(EMPTY).append(tableName).append(EMPTY).append(BRACKET_LETF).append(BREAK_LINE);
            for (int i = 0; i < tables.size(); i++) {
                Table table = tables.get(i);
                sql.append(table.getFieldName()).append(EMPTY)
                        .append(table.getDataType()).append(EMPTY);
                if (table.isAutoIncrease()) {
                    sql.append(AUTO_INCREMENT).append(EMPTY);
                }
                sql.append(COMMENT).append(EMPTY).append(QUOTES).append(table.getChineseName()).append(QUOTES);
                if (i != tables.size() - 1) {
                    sql.append(COMMA);
                }
                sql.append(BREAK_LINE);
            }
            sql.append(BRACKET_RIGHT).append(ENGINE).append(SEMICOLON).append(BREAK_LINE);
        }
        return sql.toString();
    }


}
