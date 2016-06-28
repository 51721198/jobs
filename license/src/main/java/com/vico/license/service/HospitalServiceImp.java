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
	

	@Override
	public List<Hospital> showAllHospitals() {
		
		List<Hospital> list = hospitaldao.showAll();
		return list;
	}
	
	@Override
	public void addHospital(Hospital hospital) {
		
		hospitaldao.insert(hospital);
	}
	
	@Override
	public void deleteHospital(String hospitalnumber) {
		
		hospitaldao.deleteByPrimaryKey(Integer.parseInt(hospitalnumber));
	}
	
	@Override
	public String selectHospitalName(int hospitalNumber) {
		
		String hospitalName = null;
		
		if(hospitaldao.selectByPrimaryKey(hospitalNumber) == null){
			return "无此医院信息";
		}
		
		hospitalName = hospitaldao.selectByPrimaryKey(hospitalNumber).getHospitalName();
		
		return hospitalName;
	}
	
	@Override
	public void updateHospital(Hospital hospital) {
		
		hospitaldao.updateByPrimaryKey(hospital);
	}
	
	
	@Override
	public Hospital showOneHospital(int hospitalNumber) {
		
		return hospitaldao.selectByPrimaryKey(hospitalNumber);
	}

}
