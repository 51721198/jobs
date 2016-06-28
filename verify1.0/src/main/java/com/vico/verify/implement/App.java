package com.vico.verify.implement;

import com.vico.verify.service.VerifyService;
import com.vico.verify.service.VerifyServiceImp;
import com.vico.verify.swing.Demo;

/**
 * 
 * @ClassName: App
 * @Description: 验证系统主入口
 * @author: Liu.Dun
 * @date: 2016年6月28日 下午4:51:29
 */
public class App {

	public static void main(String[] args) {

		VerifyService verifyService = new VerifyServiceImp();
		String sourceNumber = verifyService.sourceNumber();
		
		if (sourceNumber == null || sourceNumber.equals("")) {
			Demo demo = new Demo();
		} else {
			String result = new VerifyLicenseImp(sourceNumber).verifyHasSourceNumber();
			Demo.showMessageDialog(result);
		}
	}
}
