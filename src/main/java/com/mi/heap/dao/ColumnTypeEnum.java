package com.mi.heap.dao;


/**
 * Created by WZL on 2018/3/25.
 */
public enum ColumnTypeEnum {
    INT("int", "int"),
    BOOL("tinyint(1)", "boolean"),
    LONG("bigint", "long"),
    VARCHAR("varchar", "String"),
    DATETIME("datetime", "Date"),
    ERROR("error", "error");
    /**
     *
     */
    private String columnType;
    private String javaType;

    ColumnTypeEnum(String dataType, String javaType) {
        this.columnType = dataType;
        this.javaType = javaType;
    }


    public static ColumnTypeEnum getByColumnType(String columnType) {
        for (ColumnTypeEnum columnTypeEnum : ColumnTypeEnum.values()) {

            if (columnType.contains(columnTypeEnum.getColumnType())) {
                if (columnTypeEnum == INT) {
                    for (ColumnTypeEnum columnTypeEnumINT : ColumnTypeEnum.values()) {
                        if (columnTypeEnumINT.getColumnType().equals(columnType)) {
                            return columnTypeEnumINT;
                        } else {
                            return INT;
                        }

                    }


                } else {
                    return columnTypeEnum;
                }
            }

        }

        return ERROR;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
