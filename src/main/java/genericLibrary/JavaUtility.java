package genericLibrary;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Random;

public class JavaUtility {
	/*
	 * this method will generate local date 
	 */
	public void getLocalDate() {
		LocalDate l = LocalDate.now();
		int dm = l.getDayOfMonth();
		DayOfWeek dw = l.getDayOfWeek();
		int dy = l.getDayOfYear();
		System.out.println(dm);
		System.out.println(dw);
		System.out.println(dy);
	}
	
	public int getRandomNumber(int startRange,int endRange) {
		Random random = new Random();
		int low=startRange;
		int high=endRange;
			return  random.nextInt(high-low)+low;
			
	}

}
