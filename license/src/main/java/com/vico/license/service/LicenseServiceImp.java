package com.vico.license.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vico.license.dao.LicenseDao;
import com.vico.license.pojo.LicenseDetail;
import com.vico.license.util.Encrypt;
import com.vico.license.util.MixDate;
import com.vico.license.util.TimeDiff;


@Service
public class LicenseServiceImp implements LicenseService {
	
	@Autowired
	private LicenseDao licensedao;
	
	
	@Override
	public String createSourceCode(String duedate) {
				StringBuffer sb = new StringBuffer();
				Date date= new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String current = sdf.format(date);
				sb.append(current);
				sb.append(duedate);
				String id = UUID.randomUUID().toString();
				sb.append(id);
				
				//混淆时间
				return MixDate.mixDate(sb.toString());
	}
	
	@Override
	public String createEncryptCode(String code) {
		
		String encryptcode = Encrypt.MD5(code);
		return encryptcode;
	}
	/**
	 * 
	 * @Title: saveCode
	 * @Description: 存储序列号，存储前计算序列号的有效日期和到期标识
	 * @param licensedetail
	 * @see com.vico.license.service.LicenseService#saveCode(com.vico.license.pojo.LicenseDetail)
	 */
	@Override
	public void saveCode(LicenseDetail licensedetail){
	String sourceNumber = MixDate.demixDate(licensedetail.getSourceNumber());   
	
	licensedetail.setCreateDay(sourceNumber.substring(0, 10));                 
	int days = endDate(licensedetail.getExpiredDate());                         
	if(days <= 0){
		licensedetail.setValidDays(0);
		licensedetail.setExpiredFlag(1);
	}
	if(days > 0 ){
		licensedetail.setValidDays(days);
		licensedetail.setExpiredFlag(0);                                        
	}
	licensedao.insert(licensedetail);
	}

	@Override
	public List<LicenseDetail> listAllCodes() {
		
		List<LicenseDetail> list = licensedao.selectAllLicenses();
		return list;
	}
	
	/**
	 * 
	 * @Title: endDate
	 * @Description: 计算有效日期
	 * @param date
	 * @return enddate
	 * @see com.vico.license.service.LicenseService#endDate(java.lang.String)
	 */
	@Override
	public int endDate(String date) {
		int enddate = TimeDiff.countDay(date);
		return enddate;
	}

	@Override
	public void deleteCode(String codeID) {
		
		licensedao.deleteByPrimaryKey(Integer.parseInt(codeID));
	}
	
	@Override
	public LicenseDetail listOneCode(int serialNumberId) {
		
		LicenseDetail licensedetail = licensedao.selectByPrimaryKey(serialNumberId);
		return licensedetail;
	}

	@Override
	public List<LicenseDetail> selectByhospitalNumber(int hospitalNumber) {
		
		List<LicenseDetail> list = licensedao.selectByhospitalNumber(hospitalNumber);
		return list;
	}
	
	/**
	 * 
	 * @Title: taskChangeData
	 * @Description:springtask定时任务，每24小时计算一次序列号的到期标识和有效天数，写入数据库
	 * @see com.vico.license.service.LicenseService#taskChangeData()
	 */
	@Override
	public void taskChangeData() {
		
		List<LicenseDetail> list = licensedao.selectAllLicenses();
		for(LicenseDetail ldetail:list){
			int days = endDate(ldetail.getExpiredDate());                      
			if(days <= 0){
				ldetail.setValidDays(0);
				ldetail.setExpiredFlag(1);
			}
			if(days > 0 ){
				ldetail.setValidDays(days);
				ldetail.setExpiredFlag(0);                                        
			}
			licensedao.updateByPrimaryKey(ldetail);
		}
	}

	
	
}
