package com.vico.license.util.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vico.license.service.test.LicenseServiceImpTest;
import com.vico.license.util.TimeDiff;

public class TimeDiffTest {
	
	private static final Logger logger = Logger.getLogger(LicenseServiceImpTest.class);
	
	@Test
	public void testCountDay() {
		//fail("Not yet implemented");
		
		TimeDiff.countDay("2018-01-01");
		logger.info("**************测试结果*******************"+TimeDiff.countDay("2018-01-01"));
	}
	
}
