package com.mi.heap.util;

import com.mi.heap.dao.CamelUtils;
import com.mi.heap.dao.PojoField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by WZL on 2018/3/25.
 */
public class DAO {

    private String pojoName;
    private BufferedWriter bufferedWriter;
    private List<PojoField> pojoFieldList;
    private String path;
    private String tableName;

    public DAO(String pojoName, String path, String tableName, List<PojoField> pojoFields) throws IOException {
        this.pojoName = pojoName;
        this.path =path;
        this.tableName = tableName;
        File file = new File(path + "/dao/" + pojoName.substring(4) + "DAO" + ".java");

        File dir = new File(path + "/dao");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        this.bufferedWriter = new BufferedWriter(fileWriter);
        this.pojoFieldList = pojoFields;
    }

    /**
     * 构造DAO
     *
     * @throws Exception
     */
    public void startCreateDAO() throws Exception {

        String packageName = path.split("java")[1].substring(1).replaceAll("/", ".");

        FileUtils.writeSpaceLine(bufferedWriter, "package", packageName + ".dao;");
        bufferedWriter.newLine();

        writeImport();

        FileUtils.writeSpaceLine(bufferedWriter, "public", "interface", CamelUtils.toFirstUperString(pojoName.substring(4)) + "DAO", "{");

        writeTableName();

        writeFields();
        writeInsert();
        bufferedWriter.newLine();
        writeSelectAll();
        bufferedWriter.newLine();
        updateByID();
        bufferedWriter.newLine();
        deleteByID();
        bufferedWriter.write("}");
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public void writeImport() throws IOException {
        bufferedWriter.write("import net.paoding.rose.jade.annotation.DAO;\n");
        bufferedWriter.write("import net.paoding.rose.jade.annotation.SQL;\n");
        bufferedWriter.write("import net.paoding.rose.jade.annotation.SQLParam;\n\n");
        bufferedWriter.write("import java.util.List;\n\n");

        bufferedWriter.write("@DAO");

    }

    public  void writeFields() throws IOException {
        StringBuilder fields = new StringBuilder("    String FIELDS = \"");
        for (PojoField pojoField : pojoFieldList) {
            if (!pojoField.getColumnFieldName().equals("id")) {
                fields.append(pojoField.getColumnFieldName()).append(",");
            }

        }
        FileUtils.writeLine(bufferedWriter, fields.toString().substring(0, fields.length() - 1) + "\";");
        bufferedWriter.newLine();
    }

    public  void writeTableName() throws IOException {

        bufferedWriter.newLine();
        FileUtils.writeLine(bufferedWriter, "    String TABLE = \"" + tableName + "\";");
        bufferedWriter.newLine();

    }

    public  void writeInsert() throws IOException {

        StringBuilder builder = new StringBuilder("    @SQL(\"INSERT INTO \" + TABLE + \" (\" + FIELDS + \") VALUES \" +\n            \"(");

        for (PojoField pojoField : pojoFieldList) {
            if (!pojoField.getFieldName().equals("id")) {
                builder.append(":p." + pojoField.getFieldName() + ",");
            }
        }
        builder.deleteCharAt(builder.length() - 1).append(")\")");

        FileUtils.writeLine(bufferedWriter, builder.toString());
        FileUtils.writeLine(bufferedWriter, "    int insert(@SQLParam(\"p\") " + pojoName + " " + pojoName.substring(0, 1).toLowerCase() + pojoName.substring(1) + ");");

    }

    public  void writeSelectAll() throws IOException {

        FileUtils.writeLine(bufferedWriter, "    @SQL(\"SELECT id,\" + FIELDS + \" FROM \" + TABLE)");
        FileUtils.writeLine(bufferedWriter, "    List<" + pojoName + "> selectAll();");

    }

    public  void updateByID() throws IOException {

        StringBuilder builder = new StringBuilder("    @SQL(\"UPDATE \" + TABLE + \" SET ");

        for (PojoField pojoField : pojoFieldList) {
            if (!pojoField.getFieldName().equals("id")) {
                builder.append(pojoField.getColumnFieldName() + "=");
                builder.append(":p." + pojoField.getFieldName() + ", ");
            }
        }
        builder.deleteCharAt(builder.length() - 1).deleteCharAt(builder.length() - 1).append(" WHERE id=:p.id\"").append(")");

        FileUtils.writeLine(bufferedWriter, builder.toString());
        FileUtils.writeLine(bufferedWriter, "    int updateById(@SQLParam(\"p\") " + pojoName + " " + pojoName.substring(0, 1).toLowerCase() + pojoName.substring(1) + ");\n");

    }

    public  void deleteByID() throws IOException {

        FileUtils.writeLine(bufferedWriter, "    @SQL(\"DELETE  FROM \" + TABLE +\" WHERE id=:p.id\")");
        FileUtils.writeLine(bufferedWriter, "    void deleteById(@SQLParam(\"p\") " + pojoName + " " + pojoName.substring(0, 1).toLowerCase() + pojoName.substring(1) + ");\n");

    }

}
