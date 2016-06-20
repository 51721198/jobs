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
	
	private VerifyLicense verifylicense;
	

	public VerifyLicense getVerifylicense() {
		return verifylicense;
	}

	@Autowired
	public void setVerifylicense(VerifyLicense verifylicense) {
		this.verifylicense = verifylicense;
	}


	@Test
	public void verifyTest() {
		//fail("Not yet implemented");
		
		verifylicense.verify();
	}

}
