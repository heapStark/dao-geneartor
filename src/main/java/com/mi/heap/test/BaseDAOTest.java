package com.mi.heap.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class BaseDAOTest {
    public static void createBaseDAOTest(String testPath) throws Exception {
        File dir = new File(testPath + "/dao");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(testPath + "/dao/" + "BaseDAOTest" + ".java");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("package dao;\n" +
                "\n" +
                "import org.apache.commons.dbcp.BasicDataSource;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.core.io.DefaultResourceLoader;\n" +
                "\n" +
                "import java.io.IOException;\n" +
                "import java.sql.Connection;\n" +
                "import java.sql.SQLException;\n" +
                "import java.sql.Statement;\n" +
                "\n" +
                "public abstract class BaseDAOTest {\n" +
                "\n" +
                "    @Autowired\n" +
                "    public BasicDataSource dataSource;\n" +
                "\n" +
                "    Connection conn;\n" +
                "\n" +
                "    public void setUp() throws Exception {\n" +
                "        try {\n" +
                "            conn = dataSource.getConnection();\n" +
                "            Statement st = conn.createStatement();\n" +
                "            st.execute(\"drop all objects;\");\n" +
                "            st.execute(\"runscript from '\" + new DefaultResourceLoader()\n" +
                "                    .getResource(\"createTables.sql\").getURL().toString() + \"'\");\n" +
                "            st.close();\n" +
                "        } catch (SQLException e) {\n" +
                "            e.printStackTrace();\n" +
                "        } catch (IOException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void tearDown() throws Exception {\n" +
                "        if (conn != null) {\n" +
                "            conn.close();\n" +
                "            conn = null;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}");
        bufferedWriter.flush();
    }
}
