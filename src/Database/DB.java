package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

   
	    
	    private static final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
	    private static final String url = "jdbc:ucanaccess://DB\\AccountInfo.accdb";
	    //Put Location of Database on server above ^
	 
	    private Connection connection;
	    private PreparedStatement statement;
	    private ResultSet resultSet;
	    
	    
	    public DB()
	    {
	        try    // load driver
	        {
	            
	            Class.forName(driver);
	            System.out.println("Driver successfully loaded");
	            
	        }catch (ClassNotFoundException c)
	        {
	            System.out.println("Unable to load driver");
	        }
	        
	        try
	        {
	            connection = DriverManager.getConnection(url);
	            System.out.println("Connection successful");
	        }catch (SQLException e)
	                {
	                    System.out.println("Unable to connect"); 
	                    System.out.println(e);
	                }
	    }
	    
	    public ResultSet queryDB(String stmt) throws SQLException
	    {
	    	statement = connection.prepareStatement(stmt);
	    	
	    	resultSet = statement.executeQuery();
	    	return resultSet;
	    	
	    }
	    
	    public void update(String update) throws SQLException{
			
			statement = connection.prepareStatement(update);
			statement.executeUpdate();
			statement.close();
			
			
			
		}

	    
	    
	    
	}



