import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class TomcatJdbcPool {
    private static DataSource dataSource = null;
      public TomcatJdbcPool() {
    }

    public static void init() {
          if (dataSource != null) {
            try {
                dataSource.close();
            } catch (Exception e) {
                //
            }
            dataSource = null;
        }
          try {
        	PoolProperties p = new PoolProperties();
        	p.setDriverClassName("com.mysql.jdbc.Driver");//p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
            p.setUrl("jdbc:mysql://127.0.0.1:3306/teach");//p.setProperty("url", "jdbc:mysql://127.0.0.1:3306/teach");
            p.setUsername("root");//p.setProperty("username", "root");
            p.setPassword("123456");//p.setProperty("password", "123456");
            p.setMaxActive(200);//p.setProperty("maxActive", "200");
            p.setMaxIdle(50);//p.setProperty("maxIdle", "50");
            p.setMinIdle(30);//p.setProperty("minIdle", "30");
            p.setInitialSize(30);//p.setProperty("initialSize", "30");
            dataSource = new DataSource(p);
          } catch (Exception e) {
            }
    }
      public static synchronized Connection getConnection() throws  SQLException {
    	  return dataSource.getConnection();
    }
}
