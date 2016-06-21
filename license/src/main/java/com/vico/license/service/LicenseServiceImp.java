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
import com.vico.license.util.TimeDiff;


@Service
public class LicenseServiceImp implements LicenseService {
	
	@Autowired
	private LicenseDao licensedao;
	
	
	//生成序列号
	@Override
	public String createSourceCode(String duedate) {
				StringBuffer sb = new StringBuffer();
				Date date= new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String current = sdf.format(date);
				sb.append(current);
				sb.append(duedate);
				
				//此处添加时间混淆算法
				
				String id = UUID.randomUUID().toString();
				sb.append(id);
		return sb.toString();
	}
	
	//加密序列号
	@Override
	public String createEncryptCode(String code) {
		
		String encryptcode = Encrypt.MD5(code);
		return encryptcode;
	}
	
	//保存序列号
	@Override
	public void saveCode(LicenseDetail record) {
		
		licensedao.insert(record);
		
	}

	//获取所有序列号
	@Override
	public List<LicenseDetail> listAllCodes() {
		
		List<LicenseDetail> list = licensedao.selectAll();
		return list;
	}

	//计算到期日期
	@Override
	public int endDate(String date) {
		
		int enddate = TimeDiff.countDay(date);
		return enddate;
	}

	@Override
	public void deleteCode(String codeID) {
		
		licensedao.deleteByPrimaryKey(Integer.parseInt(codeID));
	}
	
}
