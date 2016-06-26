package com.vico.license.service;
import java.util.List;

import com.vico.license.pojo.Hospital;

public interface HospitalService {
	
	public List<Hospital> showAll();
	
	public String selectHospitalName(int hospitalNumber);
	
	public void addHospital(Hospital hospital);
	
	public Hospital showOne();
	
	public void deleteHospital(String hospitalnumber);

}
