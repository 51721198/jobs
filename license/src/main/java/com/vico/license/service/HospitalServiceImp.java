package com.vico.license.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vico.license.dao.HospitalDao;
import com.vico.license.pojo.Hospital;




@Service
public class HospitalServiceImp implements HospitalService {

	@Autowired
	private HospitalDao hospitaldao;

	//获取所有医院信息
	@Override
	public List<Hospital> showAll() {
		
		List<Hospital> list = hospitaldao.showAll();
		return list;
	}
	
	//添加一条医院记录
	@Override
	public void addHospital(Hospital record) {
		
		hospitaldao.insert(record);
	}

	//获取一条医院信息
	@Override
	public Hospital showOne() {
		
		return null;
	}

	@Override
	public void deleteHospital(String hospitalnumber) {
		// TODO Auto-generated method stub
		hospitaldao.deleteByPrimaryKey(Integer.parseInt(hospitalnumber));
	}

}
