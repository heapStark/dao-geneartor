package com.mi.heap.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class POJOTest {
    private String pojoName;
    private String lowPojoName;
    private BufferedWriter bufferedWriter;

    public POJOTest(String testPath, String pojoName) throws IOException {
        this.pojoName = pojoName.substring(4);
        this.lowPojoName = this.pojoName.replaceFirst(this.pojoName.substring(0, 1), this.pojoName.substring(0, 1).toLowerCase());
        File dir = new File(testPath + "/dao");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(testPath + "/dao/" + this.pojoName + "DAOTest" + ".java");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        this.bufferedWriter = new BufferedWriter(fileWriter);
    }

    public void createTest() throws IOException {
        writeHead();
        writeClassName();
        writeDAO();
        writeGetPOJO();
        writeInsert();
        writeSelect();
        writeUpdate();
        writeDelete();
        writeEnd();
        bufferedWriter.flush();
    }

    public void writeHead() throws IOException {
        bufferedWriter.write("package dao;\n" +
                "\n" +
                "import org.junit.After;\n" +
                "import org.junit.Assert;\n" +
                "import org.junit.Before;\n" +
                "import org.junit.Test;\n" +
                "import org.junit.runner.RunWith;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.test.context.ContextConfiguration;\n" +
                "import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "@RunWith(SpringJUnit4ClassRunner.class)\n" +
                "@ContextConfiguration(locations = \"classpath:applicationContext.xml\")" +
                "\n");
    }

    public void writeClassName() throws IOException {
        bufferedWriter.write("public class " + pojoName + "DAOTest extends BaseDAOTest {\n" +
                "    @Autowired" +
                "\n");
    }

    public void writeDAO() throws IOException {
        bufferedWriter.write("    private " + pojoName + "DAO " + lowPojoName + "DAO;" + "\n");
        bufferedWriter.write("\n" +
                "    @Override\n" +
                "    @Before\n" +
                "    public void setUp() throws Exception {\n" +
                "        super.setUp();\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    @After\n" +
                "    public void tearDown() throws Exception {\n" +
                "        super.tearDown();\n" +
                "    }\n");
    }

    public void writeGetPOJO() throws IOException {
        bufferedWriter.write("\n" +
                "    private POJO" + pojoName + " getPOJO() {\n" +
                "\n" +
                "        POJO" + pojoName + " pojo = new POJO" + pojoName + "();\n" +
                "        return pojo;\n" +
                "\n" +
                "    }\n" +
                "\n");
    }

    public void writeInsert() throws IOException {
        bufferedWriter.write("    @Test\n" +
                "    public void insertTest() {\n" +
                "        int result = " + lowPojoName + "DAO.insert(getPOJO());\n" +
                "        Assert.assertEquals(1, result);\n" +
                "    }"+"\n\n");
    }

    public void writeSelect() throws IOException {
        bufferedWriter.write("    @Test\n" +
                "    public void selectAllTest() {\n" +
                "        insertTest();\n" +
                "        List result = " + lowPojoName + "DAO.selectAll();\n" +
                "        Assert.assertEquals(1, result.size());\n" +
                "    }");
    }

    public void writeUpdate() throws IOException {
        bufferedWriter.write("\n\n" +
                "    @Test\n" +
                "    public void updateTest() {\n" +
                "        insertTest();\n" +
                "        List<POJO"+pojoName+"> result = "+lowPojoName+"DAO.selectAll();\n" +
                "        int i = "+lowPojoName+"DAO.updateById(result.get(0));\n" +
                "        Assert.assertEquals(1, i);\n" +
                "    }");
    }

    public void writeDelete() throws IOException {
        bufferedWriter.write("\n    @Test\n" +
                "    public void deleteTest() {\n" +
                "        insertTest();\n" +
                "        List<POJO"+pojoName+"> result = "+lowPojoName+"DAO.selectAll();\n" +
                "        Assert.assertEquals(1, result.size());\n" +
                "        "+lowPojoName+"DAO.deleteById(result.get(0));\n" +
                "        Assert.assertEquals(0, "+lowPojoName+"DAO.selectAll().size());\n" +
                "    }\n" +
                "\n");
    }

    public void writeEnd() throws IOException {
        bufferedWriter.write("\n" +
                "}");
    }

}
