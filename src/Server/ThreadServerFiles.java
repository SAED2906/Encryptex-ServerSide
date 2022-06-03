package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class ThreadServerFiles {

	public static void init() throws IOException {
		ServerSocketChannel serverSocket = ServerSocketChannel.open();
		serverSocket.socket().bind(new InetSocketAddress(25575));
		Thread a = new Thread(){ 
			public void run()  {
				try {
				
		
		while (true) {
            
    		SocketChannel client = serverSocket.accept();
    		
    		
    		System.out.println("connection established .." + client.getRemoteAddress());
            Thread t = new Thread() {
                public void run()  {
                	SocketChannel socketChannel = client;
                	
                	try {
                	String first = ThreadServer.getFirstInQ();
                	Path path = Paths.get(".\\src\\UserFiles\\" + first + ".txt");
                	
            		FileChannel fileChannel;
					
						fileChannel = FileChannel.open(path,
								EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE));
            		ByteBuffer buffer = ByteBuffer.allocate(1024);
            		
						while (socketChannel.read(buffer) > 0) {
							buffer.flip();
							
								fileChannel.write(buffer);
							
							buffer.clear();
						}
						fileChannel.close();
					
            		System.out.println("Receving file successfully!");
            		
						socketChannel.close();
					
            		
                	} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
                	
                }
                
            };
        t.start();}
	
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
			
			}
	};
	a.start();
	}
	
	

	

}

