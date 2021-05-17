/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corporateapplication.dbsource;

import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

/**
 *
 * @author unique
 */
public class DBSource {
    
    
    
    Connection conn = null;
    public static Connection connectDb() {
        try {
            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/assignmentdatabase");
            ds.setUser("root");
            ds.setPassword("");
            Connection conn = ds.getConnection();
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
