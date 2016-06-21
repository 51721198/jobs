package com.vico.verify;

/**
 * Hello world!
 *
 */

     //外部调用类
public class App 
{
    public static void main( String[] args )
    {
        
    	VerifyLicense vl = new VerifyLicenseImp();
        
       
        System.out.println(vl.verify());
    }
}
