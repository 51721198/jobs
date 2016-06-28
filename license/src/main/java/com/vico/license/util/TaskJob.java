package com.vico.license.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vico.license.service.LicenseService;


	/**
	 * 
	 * @ClassName: TaskJob
	 * @Description: spring定时任务，每隔一段时间执行一次更新数据库任务
	 * @author: Liu.Dun
	 * @date: 2016年6月27日 下午9:01:29
	 */
@Component
public class TaskJob {

	@Autowired
	private LicenseService licenseservice;
	
	
	@Scheduled(cron="0 0 2 * * ?")           //每天的凌晨2点触发一次
	//@Scheduled(cron="0/5 * * * * ?")       //每隔5秒触发一次
	public void job1(){                      //定时器任务不能有返回值       
		
		System.out.println("！！！！定时器任务执行！！！！！！！");
		licenseservice.taskChangeData();
	}
	
}
