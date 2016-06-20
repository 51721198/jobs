package com.vico.verify.util;

import java.util.Scanner;

public class ReadCode {

	@SuppressWarnings("resource")
	public static String readClientCode(){
		
		System.out.println("\n");
		Scanner sc = new Scanner(System.in);
		String code = sc.next(); 
		
		return code;
	}
	
	
}
