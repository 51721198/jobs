package com.vico.license.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.vico.license.pojo.LicenseDetail;
import com.vico.license.service.HospitalService;
import com.vico.license.service.LicenseService;

@Controller
@RequestMapping(value="licenseController")
public class LicenseController {
	
	@Autowired
	private LicenseService licenseService;
	
	@Autowired
	private HospitalService hospitalService;
	
	//去生成序列号页面
	@RequestMapping(value="tocreatecode")
	public String toCreate(HttpServletRequest request){
		return "creatcode2";
	}
	
	
	//接收前端AJAX请求:生成序列号
	@RequestMapping(value="createcode")
	public void sourceCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		
		String sourcecode = licenseService.createSourceCode(request.getParameter("duedate"));
		
		String jsonstr = "{'success':'true','msg':'"+sourcecode+"'}";
		JSON res = JSON.parseObject(jsonstr);
		
		System.out.println(res);
		response.getWriter().print(res);
	}
	
	//接受前端AJAX请求：加密序列号
	@RequestMapping(value="encryptcode")
	public void encryptCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String code = request.getParameter("encryptcode");
		
		response.setContentType("text/html;charset=UTF-8");
		String encryptcode = licenseService.createEncryptCode(code);
		
		String jsonstr = "{'success':'true','msg':'"+encryptcode+"'}";
		JSON res = JSON.parseObject(jsonstr);
		
		response.getWriter().print(res);
	}
	
	
	//跳转到展示所有序列号页面
	@RequestMapping(value="toshowallcodes")
	public String showAll(HttpServletRequest request,HttpServletResponse response){
		
		return "showallcodes2";
	}
	
	//跳至框架
	@RequestMapping(value="frame")
	public String frame(){
		return "frame1";
	}
	
	//接收AJAX请求：获取所有序列号用于展示
	@RequestMapping(value="showallcodes")
	public void showAllCodes(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		List<LicenseDetail> list =  licenseService.listAllCodes();
		
		for(LicenseDetail ldetail:list){
			String hospitalName = null;
			hospitalName = hospitalService.selectHospitalName(ldetail.getHospitalNumber());
			ldetail.setHospitalName(hospitalName);
			int lastday = licenseService.endDate(ldetail.getExpiredDate());    //获取每个序列号的剩余日期
			
			if(lastday <= 0){
				ldetail.setExpiredFlag(1);
				ldetail.setValidDays(0);
			}
			else{
				ldetail.setExpiredFlag(0);
				ldetail.setValidDays(lastday);
			}
		}
		
		String jsonlist = JSON.toJSONString(list);
		
		JSON res = JSON.parseArray(jsonlist);
		//Json jsonallcodes = licenseService.objectToJson(list);
		
		try {
			response.getWriter().print(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//接收AJAX请求，删除指定ID的序列号条目
	@RequestMapping(value="deletecode")
	public void deleteCode(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		
		String codeID = request.getParameter("codeid");
		
		LicenseDetail licensedetail = licenseService.listOneCode(Integer.parseInt(codeID));
		
		int lastdays = licenseService.endDate(licensedetail.getExpiredDate());
		
		if(lastdays > 0){
			String jsonstr = "{'success':'true','msg':'该条序列号并未过期，删除失败'}";
			JSON res = JSON.parseObject(jsonstr);
			try {
				response.getWriter().print(res);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			licenseService.deleteCode(codeID);
			String jsonstr = "{'success':'true','msg':'删除成功'}";
			JSON res = JSON.parseObject(jsonstr);
			try {
				response.getWriter().print(res);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		//return "showallcodes2";
	}
	
	
	//把序列号写进数据库,然后跳到展示所有序列号页面
	@RequestMapping(value="savecode")
	public String saveCode(LicenseDetail licensedetail){
		
		licenseService.saveCode(licensedetail);
		//return null;
		return "redirect:/licenseController/toshowallcodes";
	}
	
}
