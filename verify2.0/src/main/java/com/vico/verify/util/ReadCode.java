package com.vico.verify.util;

import java.util.Scanner;

	/**
	 * 
	 * @ClassName: ReadCode
	 * @Description: 读取用户输入的序列号
	 * @author: Liu.Dun
	 * @date: 2016年6月27日 下午9:13:48
	 */
public class ReadCode {

	@SuppressWarnings("resource")
	public static String readClientCode(){
		
		System.out.println("\n");
		Scanner sc = new Scanner(System.in);
		String code = sc.next(); 
		
		return code;
	}
	
	
}
