package com.vico.license.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.vico.license.dao.LicenseDao;
import com.vico.license.pojo.LicenseDetail;
import com.vico.license.util.Encrypt;
import com.vico.license.util.TimeDiff;


@Service

public class LicenseServiceImp implements LicenseService {
	
	private LicenseDao licensedao;
	
	
	public LicenseDao getLicensedao() {
		return licensedao;
	}
	
	@Autowired
	public void setLicensedao(LicenseDao licensedao) {
		this.licensedao = licensedao;
	}

	@Override
	public String createSourceCode(String duedate) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//需要的参数有：当前时间，UUID，到期时间,医院的编号
				
				StringBuffer sb = new StringBuffer();
				
				Date date= new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String current = sdf.format(date);
				sb.append(current);
				sb.append(duedate);
				String id = UUID.randomUUID().toString();
				//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+id.length());
				sb.append(id);
				System.out.println("+++++++++++++++++++++++++++++duedate的总长度是+++++++++++++++++++"+duedate.length());
				System.out.println(sb);
				System.out.println("+++++++++++++++++++++++++++++SB的总长度是++++++++++++++++++++++++++"+sb.length());
		return sb.toString();
	}

	@Override
	public String createEncryptCode(String code) {
		// TODO Auto-generated method stub
		
		String encryptcode = Encrypt.MD5(code);
		return encryptcode;
	}

	@Override
	public void saveCode(LicenseDetail record) {
		// TODO Auto-generated method stub
		
		int i = licensedao.insert(record);
//		System.out.println(i);
		
	}

	@Override
	public List<LicenseDetail> listAllCodes() {
		// TODO Auto-generated method stub
		List<LicenseDetail> list = licensedao.selectAll();
		
		Iterator<LicenseDetail> it = list.iterator();
		while(it.hasNext()){
			LicenseDetail ld = it.next();
			String cday = ld.getCreateDay();
//			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
//			System.out.println(cday);
		}
		
		return list;
	}

	@Override
	public int endDate(String date) {
		// TODO Auto-generated method stub
		int enddate = TimeDiff.countDay(date);
		return enddate;
	}
	
}
