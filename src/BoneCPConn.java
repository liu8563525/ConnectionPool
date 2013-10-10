import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;



public class BoneCPConn {
	public static BoneCP connectionPool = null;
	    public BoneCPConn() {
	     }

	    public static void init()
	    {
	    	try {
	    	BoneCPConfig config = new BoneCPConfig();
	    	Class.forName("com.mysql.jdbc.Driver");
	    	 config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/teach");
	    	 config.setUsername("root");
	    	 config.setPassword("123456");
	    	 config.setMinConnectionsPerPartition(30);
	    	 config.setMaxConnectionsPerPartition(100);
	    	 config.setPartitionCount(3);
	    	 connectionPool= new BoneCP(config);
	    	 System.out.println("connectionPool"+connectionPool);
	        } catch (Exception e) {
	            System.out.println("连接获取失败"+e.getMessage());
	        }
	    }
	    public static synchronized Connection getConnection() throws  SQLException {
	                       return connectionPool.getConnection();
	     }
	    public static void main(String[] args){
	    	init();
	    }
}

