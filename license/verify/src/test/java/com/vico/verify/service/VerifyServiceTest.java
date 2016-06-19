package com.vico.verify.service;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})

public class VerifyServiceTest {
	
	VerifyService verifyservice = new VerifyServiceImp();


	@Test
	public void sourceNumberTest() {
		
		
		verifyservice.sourceNumber();
		
		
		//fail("Not yet implemented");
	}
	
	@Test
	public void checkDateTest() {
			
			
			verifyservice.checkDate("2016-06-182016-06-23cedca35b-482a-4c19-ae1c-3ad59d399e3b");
			
			
			//fail("Not yet implemented");
		}
	
	@Test
	public void checkSourceNUmberTest(){
		verifyservice.checkSourceNUmber("2016-06-182016-06-23cedca35b-482a-4c19-ae1c-3ad59d399e3b");
	}
	
	@Test
	public void checkComIDTest(){
		verifyservice.checkComID("2016-06-182016-06-23cedca35b-482a-4c19-ae1c-3ad59d399e3b-78d7s9ahdsa8d9saj291jdsa");
	}
	
	@Test
	public void insertCodeTest(){
		
		verifyservice.insertCode("this is the code!!!!!");
		
	}
	
	
	
	
	
	
	
	
	
	
	
//
//	public VerifyService getVerifyservice() {
//		return verifyservice;
//	}
//
//	@Autowired
//	public void setVerifyservice(VerifyService verifyservice) {
//		this.verifyservice = verifyservice;
//	}

}
