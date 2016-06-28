package com.vico.license.service;
import java.util.List;

import com.vico.license.pojo.Hospital;

public interface HospitalService {
	
	public List<Hospital> showAllHospitals();
	
	public String selectHospitalName(int hospitalNumber);
	
	public void addHospital(Hospital hospital);
	
	public Hospital showOneHospital(int hospitalNumber);
	
	public void deleteHospital(String hospitalNumber);
	
	public void updateHospital(Hospital hospital);
	

}
