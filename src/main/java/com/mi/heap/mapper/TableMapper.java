package com.mi.heap.mapper;

import com.mi.heap.dao.ColumnBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * blogcode
 * Created by wangzhilei3 on 2018/1/7.
 */
public interface TableMapper {

    List<ColumnBean> getColumnBeanList(@Param("tableName") String tableName);

}
