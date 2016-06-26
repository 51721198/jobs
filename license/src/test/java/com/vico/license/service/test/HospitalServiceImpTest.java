package com.vico.license.service.test;


import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vico.license.pojo.Hospital;
import com.vico.license.service.HospitalService;

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class HospitalServiceImpTest {

	@Autowired
	private HospitalService hospitalservice;
	private static final Logger logger = Logger.getLogger(LicenseServiceImpTest.class);
	
	@Test
	public void testShowNames() {
		List<Hospital> list = hospitalservice.showAll();
		for(Hospital h:list){
		logger.info("**************测试信息*****************"+h.getHospitalName());
	 }
	}
	
	@Test
	public void testSelectHospitalName(){
		
		logger.info("********************"+hospitalservice.selectHospitalName(25));
	}
	
	
	
	
}