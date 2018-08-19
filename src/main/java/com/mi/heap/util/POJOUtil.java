package com.mi.heap.util;


import com.mi.heap.dao.CamelUtils;
import com.mi.heap.dao.PojoField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by WZL on 2018/3/25.
 */
public class POJOUtil {

    public static void startCreatePOJO(String path, String pojoName, List<PojoField> pojoFieldList) throws Exception {


        BufferedWriter bufferedWriter = createBufferedWriter(path, pojoName);
        String packageName = path.split("java")[1].substring(1).replaceAll("/", ".");
        FileUtils.writeLine(bufferedWriter, "package " + packageName + ".pojo" + ";");
        bufferedWriter.newLine();

        writeImports(bufferedWriter, pojoFieldList);

        FileUtils.writeLine(bufferedWriter, "public class " + pojoName + " {");

        writeFields(bufferedWriter, pojoFieldList);

        if (Configure.SETTERGETTER){
            writeGetterANDSetter(bufferedWriter, pojoFieldList);
        }
        if (Configure.TOSTRING){
            writeToString(bufferedWriter,pojoFieldList,pojoName);
        }



        bufferedWriter.write("}");
        bufferedWriter.flush();
        bufferedWriter.close();

    }

    private static BufferedWriter createBufferedWriter(String path, String pojoName) throws Exception {
        File dir = new File(path + "/pojo");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(path + "/pojo/" + pojoName + ".java");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        return bufferedWriter;
    }


    private static void writeImports(BufferedWriter bufferedWriter, List<PojoField> pojoFieldList) throws Exception {

        for (PojoField pojoField : pojoFieldList) {
            if (pojoField.getClassName().equals("Date")) {
                FileUtils.writeLine(bufferedWriter, "import java.util.Date;");
                break;
            }

        }
        bufferedWriter.newLine();

    }

    private static void writeFields(BufferedWriter bufferedWriter, List<PojoField> pojoFieldList) throws Exception {

        for (PojoField pojoField : pojoFieldList) {
            if (Configure.COMMENT) {
                FileUtils.writeLine(bufferedWriter, "    /**\n     * " + pojoField.getComment() + "\n     */");
            }

            FileUtils.writeSpaceLine(bufferedWriter, "    private", pojoField.getClassName(), pojoField.getFieldName() + ";");
        }

        bufferedWriter.newLine();

    }

    private static void writeGetterANDSetter(BufferedWriter bufferedWriter, List<PojoField> pojoFieldList) throws Exception {

        for (PojoField pojoField : pojoFieldList) {
            FileUtils.writeLine(bufferedWriter, "    public " + pojoField.getClassName() + " get" + CamelUtils.toFirstUperString(pojoField.getFieldName()) + "() {");
            FileUtils.writeLine(bufferedWriter, "        return " + pojoField.getFieldName() + ";");
            FileUtils.writeLine(bufferedWriter, "    }");
            bufferedWriter.newLine();

            FileUtils.writeLine(bufferedWriter, "    public void set" +
                    CamelUtils.toFirstUperString(pojoField.getFieldName()) + "(" +
                    pojoField.getClassName() + " " + pojoField.getFieldName() + ") {\n" +
                    "        this." + pojoField.getFieldName() + " = " + pojoField.getFieldName() + ";\n" + "    }\n");
        }
    }

    private static void writeToString(BufferedWriter bufferedWriter, List<PojoField> pojoFieldList, String pojoName) throws Exception {
        final StringBuilder sb = new StringBuilder();
        sb.append("    @Override\n    public String toString() {\n");
        sb.append("        final ");
        sb.append(Configure.BUILDER.getSimpleName()).append(" sb = new ").append(Configure.BUILDER.getSimpleName()).append("(\"").append(pojoName).append("{\");\n");


        String firstName = pojoFieldList.get(0).getFieldName();
        sb.append("        sb.append(\"");
        sb.append(firstName);
        sb.append("=\").append(");
        sb.append(firstName).append(");\n");
        //sb.append(").append('\\'');\n");

        for (int i=1;i<pojoFieldList.size();i++) {

            String name = pojoFieldList.get(i).getFieldName();
            sb.append("        sb.append(\"");
            sb.append(name);
            sb.append("=\").append(");
            sb.append(name);
            sb.append(").append('\\'');\n");
        }


        sb.append("        sb.append('}');\n").append("        return sb.toString();\n").append("    }\n");

        FileUtils.writeLine(bufferedWriter,sb.toString());
    }

}
