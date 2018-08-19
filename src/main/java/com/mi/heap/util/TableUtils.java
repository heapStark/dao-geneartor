package com.mi.heap.util;

import com.mi.heap.dao.ColumnBean;
import com.mi.heap.dao.PojoField;
import com.mi.heap.dao.SessionFactory;
import com.mi.heap.mapper.TableMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WZL on 2018/3/25.
 */
public class TableUtils {

    private static SqlSessionFactory sessionFactory = SessionFactory.getFactory();

    /**
     * @param table
     * @return
     */
    private static List<ColumnBean> getColumnBeanList(String table) {
        SqlSession session = sessionFactory.openSession(true);
        try {
            TableMapper mapper = session.getMapper(TableMapper.class);
            List<ColumnBean> columnBeanList = mapper.getColumnBeanList(table);
            return columnBeanList;
        } finally {
            session.close();
        }
    }

    public static List<PojoField> getPojoFieldList(String table) {

        List<ColumnBean> columnBeanList = getColumnBeanList(table);

        List<PojoField> pojoFieldList = new ArrayList<>();
        for (ColumnBean columnBean : columnBeanList) {
            pojoFieldList.add(new PojoField(columnBean));
        }
        return pojoFieldList;
    }

}
