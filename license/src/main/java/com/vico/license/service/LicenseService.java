package com.vico.license.service;

import java.util.List;

import com.vico.license.pojo.LicenseDetail;

public interface LicenseService {
	
	public String createSourceCode(String duedate);
	
	public String createEncryptCode(String code);
	
	public void saveCode(LicenseDetail record);
	
	public List<LicenseDetail> listAllCodes();
	
	public LicenseDetail listOneCode(int serialNumberId);
	
	public int endDate(String date);
	
	public void deleteCode(String codeID);
	
	public List<LicenseDetail> selectByhospitalNumber(int hospitalNumber);
	
	/**
	 * @param:
	 * @return: void
	 * @Title: taskChangeData
	 * @Description: spring定时任务，每隔24小时自动修改一次序列号信息
	 */
	public void taskChangeData();
	
}
