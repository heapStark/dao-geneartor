package com.mi.heap.dao;

public class PojoField {
    private String className;
    private String fieldName;
    private String comment;
    private String columnFieldName;

    private PojoField(String columnType, String fieldName, String comment) {

        this.className = ColumnTypeEnum.getByColumnType(columnType).getJavaType();
        if (comment.contains("bool")) {
            this.className = ColumnTypeEnum.BOOL.getJavaType();
        }
        if (!fieldName.contains("_")) {
            this.fieldName = fieldName;
        } else {
            this.fieldName = CamelUtils.toCamelString(fieldName);
        }

    }

    public PojoField(ColumnBean columnBean) {
        this(columnBean.getColumnType(), columnBean.getColumnName(), columnBean.getColumnComment());
        this.comment = columnBean.getColumnComment();
        this.columnFieldName = columnBean.getColumnName();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getColumnFieldName() {
        return columnFieldName;
    }

    public void setColumnFieldName(String columnFieldName) {
        this.columnFieldName = columnFieldName;
    }
}
