package com.vico.verify.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDiff {
	
	
	//计算给定日期与系统当前日期之间相差时间，为负表示该给定日期过期
	public static int countDay(String duedate){
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateend = null;
		try {
			dateend = sdf.parse(duedate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date datenow = new Date();
		long n1 = dateend.getTime();
		long n2 = datenow.getTime();
		long diff = n1 - n2;

		diff /= 3600 * 1000 * 24;
		return (int) diff;
		
	}
}
