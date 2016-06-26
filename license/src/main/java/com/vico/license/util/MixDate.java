package com.vico.license.util;

public class MixDate {
		
	//前20位ascii码向右偏移64位,写入数据库前操作，返回结果为不带日期序列号
	public static String mixDate(String sourceNumber){
		//序列号总长度为56个字符，前20位为日期，
		 String str1 = sourceNumber.substring(0, 20);
		 String str2 = sourceNumber.substring(20);
		 
         StringBuffer sb = new StringBuffer();
         char[] oricode = str1.toCharArray();
         int leng = oricode.length;
         int[] afcode = new int[leng];
         for(int i=0;i<leng;i++){
         	afcode[i] = oricode[i] + 64;                //所有字符的ascii码向右偏移64位
         	char lastcode = (char)afcode[i];
         	sb.append(lastcode);
         }
		sb.append(str2);
		return sb.toString();
	}
	
	//前20位ascii码向左偏移64位，读出数据库后操作，返回结果为带日期序列号
	public static String demixDate(String sourceNumber){
		String str1 = sourceNumber.substring(0, 20);
		String str2 = sourceNumber.substring(20);
		
		StringBuffer sb = new StringBuffer();
        char[]oricode = str1.toCharArray();
        int leng = oricode.length;
        int[] afcode = new int[leng];
        for(int m=0;m<leng;m++){
        	afcode[m] = (int)oricode[m] - 64;
        	char lastcode = (char)afcode[m];
        	sb.append(lastcode);
        }
        
		sb.append(str2);
		return sb.toString();
	}
	
}
