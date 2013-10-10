import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBTest implements Runnable /*extends Thread*/ {
	public long date1=0;
	private static Proxool proxool;
	public static int count = 0;
	public static int threadCount=400;
	public static void main(String[] args) throws Exception {
		DBCP.init();
		//C3P0.init();
		//proxool = Proxool.getInstance();
		//BoneCPConn.init();
		//TomcatJdbcPool.init();
		DBTest test = new DBTest();
		test.startup();
	}

	public void startup() {
		for (int i = 0; i <threadCount; i++) {
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	public void run() {
		/*String currThreadName = Thread.currentThread().getName();
		System.out.println(currThreadName+"---");*/
		/*if(currThreadName.equals("Thread-0"))
		{
			date1 = System.currentTimeMillis();
			System.out.println(date1);
		}*/
		if(count==0&&date1==0)
		{
			date1 = System.currentTimeMillis();
			System.out.println(date1);
		}
		for(int i=0 ; i<10 ; i++){
		try {
			Connection conn = DBCP.getConnection();
			//Connection conn = C3P0.getConnection();
			//Connection conn =proxool.getConnection();
			//Connection conn = BoneCPConn.getConnection();
			//Connection conn = TomcatJdbcPool.getConnection();
				if (conn != null) {
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("select * from user where id=1");
				while (rs.next()) {
					String username = rs.getString(2);
					System.out.println(username);
				}
				rs.close();
				statement.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		count++;
/*		if (currThreadName.equals("Thread-4")) {
			long date2 = System.currentTimeMillis();
			System.out.println(date2);
			System.out.println("运行完毕!耗时为：" + (date2 - date1) + "ms");
		}*/
		if(count==threadCount)
		{
			long date2 = System.currentTimeMillis();
			System.out.println(date2);
			System.out.println("运行完毕!耗时为：" + (date2 - date1) + "ms");
		}
	}
}