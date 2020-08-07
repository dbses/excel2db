package com.dbses.exsql;

import com.dbses.exsql.service.SqlService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ExSqlApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        SqlService sqlService = context.getBean(SqlService.class);
        String sql = sqlService.getSql("D:/文档/See_人工抽检/v2.3.0/See 人工抽检数据库表设计.xls");
        System.out.println(sql);
    }

}
