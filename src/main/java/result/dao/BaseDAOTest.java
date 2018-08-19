package dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDAOTest {

    @Autowired
    public BasicDataSource dataSource;

    Connection conn;

    public void setUp() throws Exception {
        try {
            conn = dataSource.getConnection();
            Statement st = conn.createStatement();
            st.execute("drop all objects;");
            st.execute("runscript from '" + new DefaultResourceLoader()
                    .getResource("createTables.sql").getURL().toString() + "'");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tearDown() throws Exception {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

}