package com.vico.license.util.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vico.license.service.test.LicenseServiceImpTest;
import com.vico.license.util.Encrypt;

public class EncryptTest {

	private static final Logger logger = Logger.getLogger(LicenseServiceImpTest.class);
	
	@Test
	public void testMD5() {
		//fail("Not yet implemented");
		logger.info("***************"+Encrypt.MD5("2016-06-222016-06-2361749bf0-4894-4d83-a34a-713389760f9d"));
		
	}
}
