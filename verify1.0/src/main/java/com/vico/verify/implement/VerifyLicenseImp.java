package com.vico.verify.implement;

import com.vico.verify.service.VerifyService;
import com.vico.verify.service.VerifyServiceImp;



public class VerifyLicenseImp{
		
			String sourcenumber;
		/*
		 * 	参数： arg0:验证通过，用户合法
		 * 		  arg1:序列号错误
		 * 		  arg2：序列号过期
		 *        arg3:用户更改了机器
		 *        arg4：未知错误，程序需退出
		 *        arg5:用户修改了序列号
		 *        
			完整验证流程：
			case.1:数据库原始序列号字段为空，说明是新用户，则执行以下流程
			1.第一步：对用户输入的序列号进行两次加密然后和加密序列号进行对比，匹配不通过则要求用户重新输入，通过则验证时间：
			3.第二步：时间验证不通过，提示用户联系软件供应商，退出。验证通过则读取机器ID，拼接在原始序列号后部，然后写入数据库
			4.第三步：用户欢迎信息,返回参数arg0：合法
				
						
			
			case.2:数据库原始序列号字段不为空，说明不是新用户，执行以下流程
			1.第一步：取序列号的前56位，两次加密后和加密序列号进行比对，不匹配则弹出警告并且返回错误参数：arg3：用户更改了原始序列号
			2.第二步：读取数据库的原始序列号字段的值，分别获取序列号的第11-20位字符（序列号到期日期），第57-字符串结尾的字符（机器ID）,
					   分别和系统当前时间和系统的机器ID进行对比，只要有一项没有通过，就弹出警告并且返回错误参数：arg1：序列号过期，arg2：用户更改了机器
			3.第三步：匹配成功，用户欢迎信息arg0
			*/
		public VerifyLicenseImp(){}
			
		public VerifyLicenseImp(String sourcenumber){
			this.sourcenumber = sourcenumber;
		}
	
	    
	    /**
	     * @param:
	     * @return: String
	     * @Title: verifyNoSourceNumber
	     * @Description: 数据库中不存在原始序列号时调用此方法
	     * @return
	     *
	     */
	 	public String verifyNoSourceNumber() {
	 		
	 		VerifyService verifyService = new VerifyServiceImp();
	 		
	 		if(!verifyService.checkSourceNUmber(sourcenumber)){
	 			return "arg1：序列号错误！";
	 		}
	 		
				if(verifyService.checkSourceNUmber(sourcenumber)){
					if(!verifyService.checkDate(sourcenumber)){
						return "arg2：序列号过期";
					}
					
					if(verifyService.checkDate(sourcenumber)){
					verifyService.insertCode(sourcenumber);
					return "arg0：验证通过";
					}
				}
			return "arg4：未知错误";	
	 	}			
		
	 	/**
	 	 * @param:
	 	 * @return: String
	 	 * @Title: verifyHasSourceNumber
	 	 * @Description: 数据库中存在原始序列号时候调用此方法
	 	 * @return
	 	 *
	 	 */
	 public String verifyHasSourceNumber() {
		 VerifyService verifyService = new VerifyServiceImp();
		if(sourcenumber != null && !sourcenumber.equals("") ){
			
			if(!verifyService.checkSourceNUmber(sourcenumber)){
				
				return "arg5：用户修改了序列号";
			}
	
			if(!verifyService.checkDate(sourcenumber)){
				
				return "arg2：序列号过期";
			}
			
			if(!verifyService.checkComID(sourcenumber)){
				
				return "arg3：用户更换电脑";
			}
			
			else{
				return "arg0：验证通过";
			}
		}
		return "arg4：未知错误";
	}
}