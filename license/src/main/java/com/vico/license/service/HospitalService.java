package com.vico.license.service;
import java.util.List;

import com.vico.license.pojo.Hospital;

public interface HospitalService {
	
	public List<Hospital> showAll();
	
	public void addHospital(Hospital record);
	
	public Hospital showOne();

}
