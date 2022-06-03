/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

import javax.swing.JOptionPane;

import Database.UseDB;
import ServerEncryption.Digester;
import Storage.ManagerOfStorage;

/**
 *
 * @author William Marais
 */
public class ThreadServer {
	static String[] Q = new String[100];
	static int QLength = 0;
	
  public static void main(String[] args) throws IOException {
        final int PORT = 25565;
        ServerSocket serverSocket = new ServerSocket(PORT);
        ThreadServerFiles.init();
        
        
        
        
        System.out.println("Server started...");
        System.out.println("Wating for clients...");
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread t = new Thread() {
                public void run() {
                	String input2 = "";
                    try (
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        Scanner in = new Scanner(clientSocket.getInputStream());
                    		
                    ) {
                        while (in.hasNextLine()) {
                            String input = in.nextLine();
                            if (input.equalsIgnoreCase("exit")) {
                                break;
                            }
                            
                            
                            //System.out.println("A");
                            
                            ///////////////
                            Scanner control = new Scanner(input).useDelimiter(",");
                            String[] holding = new String[50];
                            int count = 0;
                            while (control.hasNext()) {
                            	holding[count] = control.next();
                            	count++;
                            }
                            //System.out.println("B");
                            
                            
                            
                            if (holding[0].equalsIgnoreCase("gH")) {
                            	//get Shared Key to send back, using holding[1] and holding[2]
                            	//get Hashkey for those holding[1] and holding[2] from database 
                            	out.println(Digester.getKey(UseDB.getHash(holding[1]), UseDB.getHash(holding[2])));
                            	
                            }
                            
                            //System.out.println("B");
                            if (holding[0].equalsIgnoreCase("RF")) {
                            	//System.out.println("temp1");
                            	File fl = new File(".\\src\\UserFiles\\" + holding[1] + "," + holding[2] + ".txt");
                            	Scanner sc = new Scanner(fl);
                            	
                            	
                            	String tmp = sc.nextLine();
                            	
                            	
                            	if (((Integer.parseInt(holding[3])+1)*60000) > tmp.length()) {
                            		out.println("DONE" + tmp.substring(Integer.parseInt(holding[3])*60000, tmp.length()));
                            		Files.deleteIfExists(Paths.get(".\\src\\UserFiles\\" + holding[1] + "," + holding[2] + ".txt"));
                            	}else {
                            		out.println(tmp.substring(Integer.parseInt(holding[3])*60000, (Integer.parseInt(holding[3])+1)*60000));
                            	}
                            	
                            	
                            	
                            	
                            	
                            	out.println();
                            	
                            	//out.println();
                            	sc.close();
                            }
                            if (holding[0].equalsIgnoreCase("SF")) {
                            	Q[QLength] = holding[1] + "," + holding[2];
                            	QLength++;
                            	out.println("Added to Q");
                            	
                            }
                            
							if (holding[0].equalsIgnoreCase("Login")) {
								out.println(UseDB.Login(holding[1], holding[2]));   
							}
							if (holding[0].equalsIgnoreCase("Register")) {
								UUID uuid = UUID.randomUUID();
						        String uuidS = uuid.toString();
						        //System.out.println(uuidS);
								out.println(UseDB.Register(holding[1], holding[2], holding[3], uuidS));
								
							}
							if (holding[0].equalsIgnoreCase("Alive")) {
                            	//System.out.println(holding[1] + " is Alive");
                            	Online.Online.input(holding[1]);
                            	
                            	// if Clients not in list add, if not Ignore
                            }
							if (holding[0].equalsIgnoreCase("Active")) {
                            	//Get The Active Clients\
								//System.out.println(Online.Online.getOnline());
								out.println(Online.Online.getOnline());
                            }
							if (holding[0].equalsIgnoreCase("Terminate")) {
                            	//Remove Client from "Online"
								Online.Online.removeOnline(holding[1]);
                            }
                            
                            
                            
                            
                            
                            
                            ///////////////
                            if (input.equals("LOGIN")) {
                            	out.println("Authentication Token Granted");
                            }else {
                            
                            //String str = new String(clientSocket.getInputStream().readAllBytes());
                            //System.out.println(str);
                            
                            //System.out.println("String from Client: " + input);
                            
                            //double radius = Double.valueOf(input);
                            //double area = Math.PI* radius *radius ;
                            //out.println(area);
                        
                            }
                            
                            //control.close();
                            //in.close();
                        
                        
                        }
                        
                        
                    } catch (IOException | SQLException e) { }
                }
                
            };
            t.start();
        }
    }
  
  public static String getFirstInQ() {
	  String temp = Q[0];
	  for (int i = 0; i < QLength; i++) {
		  Q[i] = Q[i+1];
	  }
	  QLength--;
	  
	  return temp;
  }
}
