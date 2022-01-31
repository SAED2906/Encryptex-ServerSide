package Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateSetter {
	
	public static String dateGetter() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        // Get the date today using Calendar object.
           Date today = Calendar.getInstance().getTime();        
        // Using DateFormat format method we can create a string 
        // representation of a date with the defined format.
           String reportDate = df.format(today);

        // Print what date is today!
           System.out.println("Report Date: " + reportDate);
		return reportDate;
		
	}
	
	public static void main(String[]args) {
		System.out.println(dateGetter());
	}

}
