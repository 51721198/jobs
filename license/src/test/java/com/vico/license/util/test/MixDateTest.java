package com.vico.license.util.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vico.license.service.test.LicenseServiceImpTest;
import com.vico.license.util.MixDate;

public class MixDateTest {

	private static final Logger logger = Logger.getLogger(LicenseServiceImpTest.class);
	
	@Test
	public void testMixDate() {
		//fail("Not yet implemented");
		
		logger.info("starthere>"+MixDate.mixDate("2016-06-222016-06-2361749bf0-4894-4d83-a34a-713389760f9d"));
	}
	
	@Test
    public void testDemixDate(){
		
		logger.error("endhere>"+MixDate.demixDate("rpqvmpvmrrrpqvmpvmrs61749bf0-4894-4d83-a34a-713389760f9d"));
	}
	
}
