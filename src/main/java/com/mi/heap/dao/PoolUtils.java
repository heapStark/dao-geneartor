package com.mi.heap.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by WZL on 2018/3/25.
 */
public class PoolUtils {
    static DruidDataSource pool;

    static {
        try {
            pool = (DruidDataSource) DruidDataSourceFactory.createDataSource(loadPropertiesFile("classes/db.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pool.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DruidDataSource getPool() {
        return pool;
    }

    private static Properties loadPropertiesFile(String fullFile) {
        String webRootPath = null;
        if (null == fullFile || fullFile.equals("")) {
            throw new IllegalArgumentException("Properties file path can not be null" + fullFile);
        }
        webRootPath = PoolUtils.class.getClassLoader().getResource("").getPath();
        webRootPath = new File(webRootPath).getParent();
        InputStream inputStream = null;
        Properties p = null;
        try {
            System.out.println(webRootPath);
            inputStream = new FileInputStream(new File(webRootPath + File.separator + fullFile));
            p = new Properties();
            p.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return p;
    }

}
