package com.mi.heap.dao;

import com.mi.heap.pojo.pojoTest;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;


@DAO
public interface TestDAO {

    String TABLE = "test";

    String FIELDS = "name,date,valid";

    @SQL("INSERT INTO " + TABLE + " (" + FIELDS + ") VALUES " +
            "(:p.name,:p.date,:p.valid)")
    int insert(@SQLParam("p") pojoTest pojoTest);
}