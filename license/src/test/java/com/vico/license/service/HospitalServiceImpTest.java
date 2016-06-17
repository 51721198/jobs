package com.vico.license.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.vico.license.dao.HospitalDao;
import com.vico.license.pojo.LicenseDetail;

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class HospitalServiceImpTest {

	
	private HospitalDao hosdao;
	
	public HospitalDao getHosdao() {
		return hosdao;
	}

	@Autowired
	public void setHosdao(HospitalDao hosdao) {
		this.hosdao = hosdao;
	}

//	@Test
//	public void testShowNames() {
//		hosdao.showNames();
//	}

}