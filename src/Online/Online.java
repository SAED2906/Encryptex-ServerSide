package Online;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Online {
	
	public static String[] online = new String[1000];
	private static int Count = 0;
	
	
	public static void addOnline(String UUID) {
		online[Count] = UUID;
		Count++;
	}
	
	public static void removeOnline(String UUID) {
		int index = -1;
		for (int i = 0; i < Count-1; i++) {
			if (online[i].contains(UUID)){
				index = i;
				System.out.println("found");
			}
		}
	  
	        String[] anotherArray = new String[1000];
	        System.arraycopy(online, 0, anotherArray, 0, index);
	        System.arraycopy(online, index + 1, anotherArray, index, online.length - index - 1);
	        online = anotherArray;
	        Count--;
		
		
	}
	
	public static void input(String UUID) throws FileNotFoundException {
		
	
		boolean found = false;
//		String searchedValue = UUID;
//
//		for(String x : online){
//		    if(x.equals(searchedValue)){
//		        found = true;
//		        break;
//		    }
//		}
		//if (found != true) {
			addOnline(UUID);
		//}
		System.out.println(found);
		
		
		
//		
//		if (!online.toString().contains(UUID)) {
//			addOnline(UUID);
//		}
		
		
	}
	
	public static String getOnline() {
		String temp = "";
		for (int i = 0; i < Count; i++) {
			temp += online[i] + ",";
		}
		System.out.println(temp);
		return temp;
	}

}
