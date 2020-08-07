package com.dbses.exsql.config;

import com.dbses.exsql.service.ExcelService;
import com.dbses.exsql.service.SqlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExSqlConfiguration {

    @Bean
    public ExcelService excelService() {
        return new ExcelService();
    }

    @Bean
    public SqlService sqlService() {
        return new SqlService();
    }

}
