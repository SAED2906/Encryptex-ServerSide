package Database;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UseDB {
	
	static DB db = new DB();
	private static ResultSet rs;
	
	public static String Login(String Username, String Password) throws SQLException {
		String status = "";
		String uN = Username;
        String ps = Password;
		try
        {
			
            //Send query to DB
			rs = db.queryDB("SELECT * FROM INFORMATION WHERE Username = '" + uN + "'");
            String userN = "";
            String passW = "";
            String UUID = "";
            
            
            while (rs.next())
            {
                userN = rs.getString("Username");
                passW = rs.getString("Password");
                UUID = rs.getString("UUID");
                
                
               
                if (userN.equals(uN) && passW.equals(ps))
                {
                	
                	//System.out.println(userN + "  " + uN + "  " + passW + "  " + ps);
                    //SETTING username to be used during entire program
                    //UserLink.setCurrentUserID(txtUsername.getText());
                    
                    //new frmMainMenu().setVisible(true);
                    //this.dispose();
                	status = UUID;
                }
            }
           
        }catch (Exception e)
            {
                System.out.println("Cannot execute query");     
            }
		return status;
		
		
    }
	
	
	
	
	
	
	
	
	public static boolean Register(String Username, String Password, String HK, String UUID) throws SQLException {
		System.out.println("Registering: ");
        String uN = Username;
        String ps = Password;
        boolean status = false;
        //_________________________________
        
        rs = db.queryDB("SELECT * FROM INFORMATION WHERE Username = '" + uN + "'");
        String userN = "";
        String passW = "";
        
        while (rs.next())
        {
            userN = rs.getString("Username");
            System.out.println(userN);
            passW = rs.getString("Password");
            System.out.println(passW);
           
            if (userN.equals(uN) && passW.equals(ps))
            {
                //SETTING username to be used during entire program
                //UserLink.setCurrentUserID(txtUsername.getText());
                
                //new frmMainMenu().setVisible(true);
                //this.dispose();
            	status = false;
            }
        }
        
       if (!userN.equals(uN) && !passW.equals(ps))
       {
    	   
    	   
           System.out.println("Username and password not found - registering" + DateSetter.dateGetter());
          
           
          
        
        
        
        
        //_________________________________
        
        try
        {
        	
            
            db.update("INSERT INTO INFORMATION (UUID, Username, Password, Hashkey, [Account Creation]) VALUES ('"+ UUID +"', '" + uN + "', '"+ ps + "', '"+ HK +"', '"+ DateSetter.dateGetter() + "')");
            status = true;
        } 
        catch(Exception e)
        {
            System.out.println("cannot execute query");
            status = false;
        }
       }
       
       return status;

	}
	
	public static String getHash(String UUID) throws SQLException {
		String uuid = UUID;
		String hash = "";
		try
        {
			
            //Send query to DB
			rs = db.queryDB("SELECT * FROM INFORMATION WHERE UUID = '" + uuid + "'");
            
            
  
            
            
            while (rs.next())
            {
                hash = rs.getString("Hashkey");
                
                
                
               
                
            }
           
        }catch (Exception e)
            {
                System.out.println("Cannot execute query");     
            }
		return hash;
		
		
    }
	
	
	
	public static void main(String []args) 
	{
		
//		try {
//			//Register();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//DisplaytblCD("Table CD", "SELECT * FROM tblCD;");
		//DisplaytblCD("Sorted by Table", "SELECT * FROM tblCD ORDER BY Title;");
		//DisplaytblCD("More than 10 songs", "SELECT * FROM tblCD WHERE NoOFSongs > 10;");
		//DisplaytblCD("Rock CD's", "SELECT * FROM tblCD WHERE Genre LIKE 'ROCK';");
		//DisplaytblCD("Genre starting with R", "SELECT * FROM tblCD WHERE Genre LIKE 'R%';");
		//DisplaytblCD("Titles with Tree", "SELECT * FROM tblCD WHERE Title LIKE '%Tree%';");
		
		//String search = JOptionPane.showInputDialog("Enter Genre to search for");
		//DisplaytblCD("Display according to Genre "+ search, "SELECT * FROM tblCD WHERE Genre = '" + search + "'");
		
		
		
		//try {
			
			//db.update("INSERT INTO tblCD (Title, Artist, NoOfSongs, Genre) VALUES ('GG', ' Will', 18, 'Pop')");
			//db.update("DELETE FROM tblCD WHERE Artist = 'Will' AND Title = 'GG';");
			//db.update("UPDATE tblCD SET Genre = 'GG' WHERE Title = 'Joshua Tree' AND Artist = 'U2';");
			
		//}catch(SQLException e) {
		//	System.out.print(e);
		//}
		
		//DisplaytblCD("Updated Table", "SELECT * FROM tblCD");
		
	}
	
	static void DisplaytblCD(String heading, String sql) {
		
		System.out.println();
		System.out.println(heading);
		System.out.println();
		
		try
		{
			ResultSet resultSet = db.queryDB(sql);
			
			while(resultSet.next() == true)
			{
				String t = resultSet.getString("Title");
				String a = resultSet.getString("Artist");
				int n = resultSet.getInt("NoOfSongs");
				String g = resultSet.getString("Genre");
				
				System.out.println(t + addSpaces(t, 25) + a + addSpaces(a, 20) + n + addSpaces(n+"", 6) + g);
				
			}
			
			
			
		}catch(SQLException e) {
			
			System.out.println("Cannot execute query");
			
		}
		
	}
	
	static String addSpaces(String s, int width) {
		
		String temp = " ";
		for (int i = 0; i < width - s.length(); i++)
		{
			
			temp += " ";
			
		}
		
		return temp;
		
		
	}
	
	
}
