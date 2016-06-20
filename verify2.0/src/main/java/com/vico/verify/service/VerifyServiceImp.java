package com.vico.verify.service;
import com.vico.verify.dao.ClientDao;
import com.vico.verify.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.vico.verify.util.TimeDiff;

@Service
public class VerifyServiceImp implements VerifyService {

	private ClientDao clientdao;
	
	public ClientDao getClientdao() {
		return clientdao;
	}
	
	@Autowired
	public void setClientdao(ClientDao clientdao) {
		this.clientdao = clientdao;
	}

	public String sourceNumber() {
		// TODO Auto-generated method stubs
		String code = null;
		code = clientdao.selectSourceNumber();
        System.out.println("+++++++++++++++++++++++++++++++++SOURCENUMBERIS!!!!!!!!!!+++++>>>>>>>>"+code);
		return code;
	}

	public boolean checkDate(String code) {
		// TODO Auto-generated method stub
		
		//取序列号的第11-20位字符，调用Timediff计算日期是否过期
		//如果用户输入的序列号长度不够，则采用substring会报错，应该先进行判断！！
		if(code.length() < 56){
			return false;
		}
		String date = code.substring(10,20);
		//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++>>>>>>>>>>>>>>>>>>>>>"+date);
		int days = TimeDiff.countDay(date);
		//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++>>>>>>>>>>>>>>>>>>>>>"+days);
		if(days > 0){
			//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++>>>>>>>>>>>>>>>>>>>>>"+days);
		return true;}
		else return false;
	}

	public void insertCode(String code) {
		// TODO Auto-generated method stub
		String comID = null;
		comID =  MachineID.getCPUSerial();
		StringBuffer sb = new StringBuffer();
		sb.append(code);
		sb.append(comID);
		clientdao.insertSourceNumber(sb.toString());
	}

	public boolean checkSourceNUmber(String sourcenumber) {
		// TODO Auto-generated method stub
		//取序列号的前56位，调用Encrypt方法加密，然后从数据库中取加密序列号，比对
		String code = null;
		if(sourcenumber.length() < 56){
			return false;
		}
		code = sourcenumber.substring(0, 56);
		//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++>>>>>>>>>>>>>>>>>>>>>"+code);
		String encryptcode = null;
		encryptcode = Encrypt.MD5(code);
		if(encryptcode.equals(clientdao.selectEncryptedNumber())){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++>>>>>>>code is right CANHERER!!!!!!!!!!!!>>>>>>>>>>>>>>");
			return true;
		}
		return false;
	}

	public boolean checkComID(String sourcenumber) {
		// TODO Auto-generated method stub
		//取序列号的57位之后的字符，与机器id进行比对
		if(sourcenumber.length() < 56){
			return false;
		}
		String comID = sourcenumber.substring(56);
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++>>>>>>>>>>>>>>>>>>>>>"+comID);
		
		if(comID.equals(MachineID.getCPUSerial())){
			return true;
		}
		return false;
	}
}