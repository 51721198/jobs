package com.vico.verify.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

	/**
	 * 
	 * @ClassName: TimeDiff
	 * @Description: 比较传入的日期与系统当前日期的时间差
	 * @author: Liu.Dun
	 * @date: 2016年6月27日 下午9:03:01
	 */
public class TimeDiff {
	
	public static int countDay(String duedate){
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateend = null;
		try {
			dateend = sdf.parse(duedate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date datenow = new Date();
		long n1 = dateend.getTime();
		long n2 = datenow.getTime();
		long diff = n1 - n2;

		diff /= 3600 * 1000 * 24;
		return (int) diff+1;
		
	}
}
