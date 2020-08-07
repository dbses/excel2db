package com.dbses.exsql.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Table {

    private String tableName;

    private String fieldName;

    private String chineseName;

    private String dataType;

    private boolean isAutoIncrease;

    private String remark;

}
