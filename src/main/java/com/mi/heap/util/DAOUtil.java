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
public class DAOUtil {


    /**
     * 构造DAO
     *
     * @param path
     * @param tableName
     * @throws Exception
     */
    public static void startCreateDAO(String path, String tableName, String pojoName, List<PojoField> pojoFieldList) throws Exception {

        BufferedWriter bufferedWriter = createBufferedWriter(path, pojoName);
        String packageName = path.split("java")[1].substring(1).replaceAll("/", ".");

        FileUtils.writeSpaceLine(bufferedWriter, "package", packageName +".dao;");
        bufferedWriter.newLine();

        DAOUtil.writeImport(bufferedWriter, pojoName);

        FileUtils.writeSpaceLine(bufferedWriter,"public","interface" , CamelUtils.toFirstUperString(pojoName.substring(4)) + "DAO" ,"{");

        DAOUtil.writeTableName(bufferedWriter, tableName);

        DAOUtil.writeFields(bufferedWriter, pojoFieldList);

        DAOUtil.writeInsert(bufferedWriter, pojoFieldList, pojoName);

        bufferedWriter.write("}");
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static BufferedWriter createBufferedWriter(String path, String pojoName) throws Exception {
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
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        return bufferedWriter;
    }

    public static void writeImport(BufferedWriter bufferedWriter, String pojoName) throws IOException {
        FileUtils.writeLine(bufferedWriter, "import net.paoding.rose.jade.annotation.DAO;");
        FileUtils.writeLine(bufferedWriter, "import net.paoding.rose.jade.annotation.SQL;");
        FileUtils.writeLine(bufferedWriter, "import net.paoding.rose.jade.annotation.SQLParam;");
        bufferedWriter.newLine();
        FileUtils.writeLine(bufferedWriter, "import java.util.List;");
        bufferedWriter.newLine();

        FileUtils.writeLine(bufferedWriter, "@DAO");

    }


    public static void writeFields(BufferedWriter bufferedWriter, List<PojoField> pojoFieldList) throws IOException {
        StringBuilder fields = new StringBuilder("    String FIELDS = \"");
        for (PojoField pojoField : pojoFieldList) {
            if (!pojoField.getColumnFieldName().equals("id")) {
                fields.append(pojoField.getColumnFieldName()).append(",");
            }

        }
        FileUtils.writeLine(bufferedWriter, fields.toString().substring(0, fields.length() - 1) + "\";");
        bufferedWriter.newLine();
    }

    public static void writeTableName(BufferedWriter bufferedWriter, String tableName) throws IOException {

        bufferedWriter.newLine();
        FileUtils.writeLine(bufferedWriter, "    String TABLE = \"" + tableName + "\";");
        bufferedWriter.newLine();

    }


    public static void writeInsert(BufferedWriter bufferedWriter, List<PojoField> pojoFieldList, String pojoName) throws IOException {

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

}
