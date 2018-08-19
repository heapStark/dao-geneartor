package com.mi.heap;

import com.mi.heap.dao.PojoField;
import com.mi.heap.test.BaseDAOTest;
import com.mi.heap.test.POJOTest;
import com.mi.heap.util.Configure;
import com.mi.heap.util.DAO;
import com.mi.heap.util.POJO;
import com.mi.heap.util.TableUtils;

import java.util.List;

/**
 * Created by WZL on 2018/3/25.
 */
public class Starter {
    public static void main(String[] args) {
        try {
            start("/home/mi/source/dao-geneartor/src/main/java/result",
                    "/home/mi/source/dao-geneartor/src/main/java/result",
                    "item",
                    "POJOItem");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start(String path, String testPath, String tableName, String pojoName) throws Exception {
        List<PojoField> pojoFieldList = TableUtils.getPojoFieldList(tableName);
        Configure.openComment();

        BaseDAOTest.createBaseDAOTest(testPath);

        new POJO(path, pojoName, pojoFieldList).startCreatePOJO();
        new DAO(pojoName, path, tableName, pojoFieldList).startCreateDAO();
        new POJOTest(testPath, pojoName).createTest();
    }

}
