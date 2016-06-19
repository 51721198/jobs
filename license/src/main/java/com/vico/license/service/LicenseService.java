package com.vico.license.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.vico.license.pojo.LicenseDetail;

public interface LicenseService {
	
	public String createSourceCode(String duedate);
	
	public String createEncryptCode(String code);
	
	public void saveCode(LicenseDetail record);
	
	public List<LicenseDetail> listAllCodes();
	
	public int endDate(String date);
	
}
