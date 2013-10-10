import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class C3P0 {

    private static ComboPooledDataSource dataSource = null;
    public C3P0() {
    	dataSource = new ComboPooledDataSource();
    }

    public static void init()
    {
    	dataSource = new ComboPooledDataSource();
       try {
    	   dataSource.setDriverClass("com.mysql.jdbc.Driver");
    	   dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/teach");
           dataSource.setUser("root");
           dataSource.setPassword("123456");
           dataSource.setMaxPoolSize(500);
           dataSource.setMinPoolSize(30);
           dataSource.setInitialPoolSize(30);


        } catch (Exception e) {
            //
        }
    }


    public static synchronized Connection getConnection() throws  SQLException {
                       return dataSource.getConnection();
     }
}