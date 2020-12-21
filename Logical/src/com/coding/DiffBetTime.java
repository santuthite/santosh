package com.coding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DiffBetTime 
{
	public static String[] calculateDiff(String[] strArr) 
	{
		SimpleDateFormat format = new SimpleDateFormat("hh:mma");
		String first = "";
		String second = "";
		long previosDiff = 0;
		long currentDiff = 0;
		for(int i = 0; i < strArr.length; i++) 
		{
			String sessionTimeStart = strArr[i].substring(8);
			for(int j = i+1; j < strArr.length; j++) 
			{
				String sessionTimerEndOrCurrent = strArr[j].substring(0,7);
				Date d1 = null;
			    Date d2 = null; 
			    try
			    {
			        d1 = format.parse(sessionTimeStart);
			        d2 = format.parse(sessionTimerEndOrCurrent);
			    } 
			    catch (ParseException e1) 
			    {
			        e1.printStackTrace();
			    }
			    currentDiff = d2.getTime() - d1.getTime();
				if(previosDiff < currentDiff) {
					previosDiff = currentDiff;
					first = strArr[i];
					second = strArr[j];
				}
			}
		}
		String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(previosDiff),
	            TimeUnit.MILLISECONDS.toMinutes(previosDiff) - TimeUnit.HOURS.toMinutes
	            (TimeUnit.MILLISECONDS.toHours(previosDiff)));
		String[] str = {hms, first, second};
		return str;
	}
	
	public static void main(String[] args) 
	{
		String[] strArr = {
				"10:00AM-12:30PM","02:00PM-02:45PM","05:45PM-07:45PM","09:10AM-11:50AM","01:00PM-3:00PM"
		};
		String[] str =DiffBetTime.calculateDiff(strArr);
		System.out.println("Difference is "+str[0]);
		System.out.println("two events are "+str[1]+" & "+str[2]);
			}

}
