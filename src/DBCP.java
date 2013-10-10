import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCP {
    private static BasicDataSource dataSource = null;
      public DBCP() {
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
            Properties p = new Properties();
            p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
            p.setProperty("url", "jdbc:mysql://127.0.0.1:3306/teach");
            p.setProperty("username", "root");
            p.setProperty("password", "123456");
            p.setProperty("maxActive", "200");
            p.setProperty("maxIdle", "50");
            p.setProperty("minIdle", "30");
            p.setProperty("initialSize", "30");
            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
          } catch (Exception e) {
            }
    }
      public static synchronized Connection getConnection() throws  SQLException {

    	  return dataSource.getConnection();
    }
}
