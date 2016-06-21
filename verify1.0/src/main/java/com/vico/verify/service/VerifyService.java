package com.vico.verify.service;

public interface VerifyService {
	
	
		
		//到数据库读取序列号字段的值
		public String sourceNumber();
		
		//验证时间是否过期
		public boolean checkDate(String code);
	
		//将用户输入的序列号+系统ID后插入数据库
		public void insertCode(String code);
		
		//校验序列号值是否匹配
		public boolean checkSourceNUmber(String sourcenumber);
		
		//验证电脑ID
		public boolean checkComID(String code);

}
