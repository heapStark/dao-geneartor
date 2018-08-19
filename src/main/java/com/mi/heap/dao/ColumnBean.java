package com.mi.heap.dao;

/**
 * Created by WZL on 2018/3/25.
 */
public class ColumnBean {
    /**
     *列名
     */
    private String columnName;
    /**
     *数据类型
     */
    private String dataType;
    /**
     * 是否为键，PRI，UNI
     */
    private String columnKey;
    /**
     * 注释
     */
    private String columnComment;

    /**
     * 更加具体，显示位数
     */
    private String columnType;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ColumnBean{");
        sb.append("columnName='").append(columnName).append('\'');
        sb.append(", dataType='").append(dataType).append('\'');
        sb.append(", columnKey='").append(columnKey).append('\'');
        sb.append(", columnComment='").append(columnComment).append('\'');
        sb.append(", columnType='").append(columnType).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}
