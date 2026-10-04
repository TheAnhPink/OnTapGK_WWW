package iuh.fit.demobai3tuan345voirestapi.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static DataSource dataSource;

    public DBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static Connection getConnect(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
