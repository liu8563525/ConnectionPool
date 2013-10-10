import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;


public class Proxool {
	private static Proxool proxool = null;
	private Connection con = null;

    public Proxool() {
    	InputStream is = getClass().getResourceAsStream("/proxool.xml");
        try{
            JAXPConfigurator.configure(new InputStreamReader(is), false);
               System.out.println("Configuration file(proxool.xml) has been loaded !");
        }catch(Exception e){
          System.out.println("Load Configuration failed ! " + e.getMessage());
              }finally{
              try{
                  is.close();
                   }catch(Exception ex){}
              }
    }
     public static   Proxool getInstance(){
        if(null==proxool)
        	proxool =new Proxool();
        return proxool;
       }
     public synchronized Connection getConnection(){
         try{
             con = DriverManager.getConnection("proxool.teach");
             }catch(Exception e){
                   System.out.println("Connection failed ! "+e.getMessage());
               }
           return con;
       }
}

