package Storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManagerOfStorage {
	
	public static void Store(String fileContent, String Send, String Recieve) throws IOException {
		
		try{
            // Create new file
			
            String content = fileContent;
            String path="A:\\"+Send + "-" + Recieve + ".txt";
            File file = new File(path);
            System.out.println(content);

            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                
                

                // Write in file
                bw.write(content);

                // Close connection
                bw.close();
            }

            
        }
        catch(Exception e){
            System.out.println(e);
        }
		
	}

}
