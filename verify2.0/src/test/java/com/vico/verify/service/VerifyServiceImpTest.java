package com.vico.verify.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vico.verify.dao.ClientDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})

public class VerifyServiceImpTest {
	
	private VerifyService verifyservice;

	public VerifyService getVerifyservice() {
		return verifyservice;
	}
	
	@Autowired
	public void setVerifyservice(VerifyService verifyservice) {
		this.verifyservice = verifyservice;
	}

	@Test
	public void testSourceNumber() {
		verifyservice.sourceNumber();
	}

	@Test
	public void testCheckDate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testInsertCode() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCheckSourceNUmber() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCheckComID() {
		//fail("Not yet implemented");
	}

}
