package com.vico.verify;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})

public class VerifyLicenseTest {
	
	
	private VerifyLicense vl;
	
	public VerifyLicense getVl() {
		return vl;
	}

	@Autowired
	public void setVl(VerifyLicense vl) {
		this.vl = vl;
	}

	@Test
	public void testVerify() {
		
		vl.verify();
		
		//fail("Not yet implemented");
	}

}
