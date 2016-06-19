package com.vico.license.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.vico.license.pojo.LicenseDetail;


@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class LicenseServiceImpTest {

	
	
	private LicenseServiceImp lsi;
	
	public LicenseServiceImp getLsi() {
		return lsi;
	}
	
	@Autowired
	public void setLsi(LicenseServiceImp lsi) {
		this.lsi = lsi;
	}

	@Test
	public void testcreateSourceCode() {
		//lsi.createSourceCode("20170610064507", 32);
	}
	
	@Test
		public void testMybatis() {
		
			LicenseDetail ldetail = new LicenseDetail();
			ldetail.setCreateDay("2016-05-28");
			ldetail.setEncryptedNumber("asdhhas");
			ldetail.setExpiredDate("2017-07-15");
			ldetail.setExpiredFlag(1);
			ldetail.setHospitalNumber(23);
			//ldetail.setSerialNumberId(1);
			ldetail.setSourceNumber("asdsadsaaaaaadsa");
			ldetail.setValidDays(100);
			
			System.out.println(ldetail);
			lsi.saveCode(ldetail);
			//logger.info(JSON.toJSONStringWithDateFormat("add "+i, "yyyy-MM-dd HH:mm:ss"));
		}
	
	@Test
	public void showAllCodesTest(){
		lsi.listAllCodes();
	}
}
