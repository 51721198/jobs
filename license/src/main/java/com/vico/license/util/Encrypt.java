package com.vico.license.util;

import java.security.MessageDigest;

public class Encrypt {
	   
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            //System.out.println(str);
            
            
            //第二次加密
            String str1 = new String(str);
            StringBuffer sb = new StringBuffer();
            
            char[] oricode = str1.toCharArray();
            int leng = oricode.length;
            int[] afcode = new int[leng];
            for(int i=0;i<leng;i++){
            	afcode[i] = oricode[i] + 2;                //所有字符的ascii码偏移两位
            	char lastcode = (char)afcode[i];
            	sb.append(lastcode);
           }
            
            /*  第二次加密的反运算：
            StringBuffer sb2 = new StringBuffer();
            char[]oricode2 = sb.toString().toCharArray();
            int leng2 = oricode2.length;
            int[] afcode2 = new int[leng2];
            for(int m=0;m<leng2;m++){
            	afcode2[m] = (int)oricode2[m] -2;
            	char lastcode2 = (char)afcode2[m];
            	sb2.append(lastcode2);
            }
            	System.out.println("==========sb2======"+sb2.toString());
            	*/
            
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
