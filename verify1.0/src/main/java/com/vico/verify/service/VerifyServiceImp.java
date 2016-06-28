package com.vico.verify.service;

import com.vico.verify.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.vico.verify.util.TimeDiff;

@Service
public class VerifyServiceImp implements VerifyService {

	Connection con = null;
	Statement stm = null;
	ResultSet res = null;

	public String sourceNumber() {
		String code = null;
		con = TakeConnection.getConnection();
		try {
			stm = (Statement) con.createStatement();
			res = stm.executeQuery("select source_number from client_verify");
			while (res.next()) {
				code = res.getString("source_number");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return code;
	}

	public boolean checkDate(String code) {

		// 如果用户输入的序列号长度不够，则采用substring会报错，应该先进行判断！！
		if (code.length() < 56) {
			return false;
		}
		String str = MixDate.demixDate(code);
		String date = str.substring(10, 20);

		int days = TimeDiff.countDay(date);
		if (days > 0) {
			return true;
		} else
			return false;
	}

	public void insertCode(String code) {
		String comID = null;
		comID = MachineID.getCPUSerial();
		StringBuffer sb = new StringBuffer();
		sb.append(code);
		sb.append(comID);

		con = TakeConnection.getConnection();
		try {
			stm = (Statement) con.createStatement();
			stm.executeUpdate("update client_verify set source_number='" + sb.toString() + "'");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	public boolean checkSourceNUmber(String sourcenumber) {

		String code = null;
		if (sourcenumber.length() < 56) { // 取序列号的前56位，调用Encrypt方法加密，然后从数据库中取加密序列号，比对
			return false;
		}
		code = sourcenumber.substring(0, 56);
		String encryptcode = null;
		encryptcode = Encrypt.MD5(code);
		String str = null;
		con = TakeConnection.getConnection();
		try {
			stm = (Statement) con.createStatement();
			res = stm.executeQuery("select encrypted_number from client_verify");
			while (res.next()) {
				str = res.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		if (encryptcode.equals(str)) {
			return true;
		}
		return false;
	}

	public boolean checkComID(String sourcenumber) {

		if (sourcenumber.length() < 56) {
			return false;
		}
		String comID = sourcenumber.substring(56); // 取序列号的57位之后的字符，与机器id进行比对
		if (comID.equals(MachineID.getCPUSerial())) {
			return true;
		}
		return false;
	}

	public void closeAll() {
		if (res != null) // 关闭数据库链接，释放资源
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (stm != null)
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
