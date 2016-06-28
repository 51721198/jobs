package com.vico.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vico.verify.service.VerifyService;
import com.vico.verify.util.ReadCode;


	/**
	 * 
	 * @ClassName: VerifyLicenseImp
	 * @author: Liu.Dun
	 * @date: 2016年6月27日 下午9:05:19
	 * @Description: 参数：   arg0:验证通过，用户合法
						    arg1：序列号过期
						    arg2:用户更改了机器
						    arg3：未知错误，程序需退出
						    arg4:用户修改了序列号
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
@Controller
public class VerifyLicenseImp implements VerifyLicense {
		@Autowired
	    private VerifyService verifyService;
		
		
		
		
		/**
		 * 
		 * @Title: verify
		 * @Description: 验证序列号
		 * @return
		 * @see com.vico.verify.VerifyLicense#verify()
		 */
		public String verify() {
		System.out.println( "欢迎使用维科序列号验证系统!" );
		String sourcenumber = null;
		sourcenumber = verifyService.sourceNumber();
		
		/**
		 * 第一种情况：数据库没有原始序列号，用户新装
		 */
		if(sourcenumber==null || sourcenumber.equals("")){
			
			System.out.println("请输入序列号");
			
			boolean res = true;
			while(res){
				sourcenumber = ReadCode.readClientCode();
				if(!verifyService.checkSourceNUmber(sourcenumber)){
					System.out.println("序列号错误，请重新输入");
				}
				
				if(verifyService.checkSourceNUmber(sourcenumber)){
					if(!verifyService.checkDate(sourcenumber)){
						System.out.println("该序列号过期，请联系软件供应商！！");
						System.out.println("系统将在5秒后退出！！");
						int j = 5;
						for(int i=1000;i<=5000;i+=1000){
							try {
								Thread.sleep(i);
								System.out.println(j--);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println("系统退出");
						return "arg1";
					}
					if(verifyService.checkDate(sourcenumber)){
					System.out.println("欢迎使用1111111111");
					verifyService.insertCode(sourcenumber);
					return "arg0";
					}
				}	
			}
		}
		
		/**
		 * 第二种情况：数据库中已经存在原始序列号，老用户
		 */
		if(sourcenumber != null && !sourcenumber.equals("") ){
			
			if(!verifyService.checkSourceNUmber(sourcenumber)){
				System.out.println("用户修改了序列号！！");
				System.out.println("系统将在5秒后退出！！");
				int j = 5;
				for(int i=1000;i<=5000;i+=1000){
					try {
						Thread.sleep(i);
						System.out.println(j--);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("系统退出");
				
				return "arg4";
			}
	
			if(!verifyService.checkDate(sourcenumber)){
				System.out.println("序列号过期，请联系软件供应商！");
				System.out.println("系统将在5秒后退出！！");
				int j = 5;
				for(int i=1000;i<=5000;i+=1000){
					try {
						Thread.sleep(i);
						System.out.println(j--);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("系统退出");
				return "arg1";
			}
			
			if(!verifyService.checkComID(sourcenumber)){
				System.out.println("用户更换了电脑");
				System.out.println("系统将在5秒后退出！！");
				int j = 5;
				for(int i=1000;i<=5000;i+=1000){
					try {
						Thread.sleep(i);
						System.out.println(j--);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("系统退出");
				return "arg2";
			}
			else{
				System.out.println("欢迎使用22222");
				return "arg0";
			}
		}
		System.out.println("未知错误。。。");
		return "arg3";
	}
}
