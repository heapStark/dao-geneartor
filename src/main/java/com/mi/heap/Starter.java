package com.mi.heap;


import com.mi.heap.dao.PojoField;
import com.mi.heap.dao.TableUtils;
import com.mi.heap.test.TestUtil;
import com.mi.heap.util.Configure;
import com.mi.heap.util.DAOUtil;
import com.mi.heap.util.POJOUtil;

import java.util.List;


/**
 * Created by WZL on 2018/3/25.
 */
public class Starter {
    public static void main(String[] args) {
        try {
            start("/home/wzl/source/dao-generator/src/main/java/com/mi/heap","test","pojoTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start(String path, String tableName, String pojoName) throws Exception {
        List<PojoField> pojoFieldList = TableUtils.getPojoFieldList(tableName);
        Configure.openComment().openSetGet();

        POJOUtil.startCreatePOJO(path, pojoName, pojoFieldList);
        DAOUtil.startCreateDAO(path, tableName, pojoName, pojoFieldList);
        TestUtil.createTest();
    }

}
