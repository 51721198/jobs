package com.vico.license.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vico.license.dao.HospitalDao;
import com.vico.license.pojo.Hospital;




@Service
public class HospitalServiceImp implements HospitalService {

	private HospitalDao hospitaldao;
	
	public HospitalDao getHospitaldao() {
		return hospitaldao;
	}
	
	@Autowired
	public void setHospitaldao(HospitalDao hospitaldao) {
		this.hospitaldao = hospitaldao;
	}

	@Override
	public List<Hospital> showAll() {
		// TODO Auto-generated method stub
		
		List<Hospital> list = hospitaldao.showAll();
		return list;
	}

	@Override
	public void addHospital(Hospital record) {
		// TODO Auto-generated method stub
		hospitaldao.insert(record);
	}

	@Override
	public Hospital showOne() {
		// TODO Auto-generated method stub
		
		return null;
	}

}
