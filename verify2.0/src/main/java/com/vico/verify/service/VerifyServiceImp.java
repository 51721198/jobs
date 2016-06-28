package com.vico.verify.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vico.verify.dao.ClientDao;
import com.vico.verify.util.Encrypt;
import com.vico.verify.util.MachineID;
import com.vico.verify.util.MixDate;
import com.vico.verify.util.TimeDiff;

@Service
public class VerifyServiceImp implements VerifyService {
	
	@Autowired
	private ClientDao clientdao;
	
	public String sourceNumber() {
		String code = null;
		code = clientdao.selectSourceNumber();
		return code;
	}

	public boolean checkDate(String code) {
		//如果用户输入的序列号长度不够，则采用substring会报错，应该先进行判断！！
		if(code.length() < 56){
			return false;
		}
		String str = MixDate.demixDate(code);
		
		String date = str.substring(10,20);
		int days = TimeDiff.countDay(date);
		if(days > 0){
		return true;}
		else return false;
	}
	
	/**
	 * 
	 * @Title: insertCode
	 * @Description: 读取机器ID，拼在原始序列号后存入数据库
	 * @param code
	 * @see com.vico.verify.service.VerifyService#insertCode(java.lang.String)
	 */
	public void insertCode(String code) {
		String comID = null;
		comID =  MachineID.getCPUSerial();
		StringBuffer sb = new StringBuffer();
		sb.append(code);
		sb.append(comID);
		clientdao.insertSourceNumber(sb.toString());
	}

	public boolean checkSourceNUmber(String sourcenumber) {
		String code = null;
		if(sourcenumber.length() < 56){
			return false;
		}
		code = sourcenumber.substring(0, 56);
		String encryptcode = null;
		encryptcode = Encrypt.MD5(code);
		if(encryptcode.equals(clientdao.selectEncryptedNumber())){
			return true;
		}
		return false;
	}
	
	public boolean checkComID(String sourcenumber) {
		if(sourcenumber.length() < 56){
			return false;
		}
		String comID = sourcenumber.substring(56);
		
		if(comID.equals(MachineID.getCPUSerial())){
			return true;
		}
		return false;
	}
}