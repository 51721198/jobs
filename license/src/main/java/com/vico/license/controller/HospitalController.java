package com.vico.license.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.vico.license.pojo.Hospital;
import com.vico.license.service.HospitalService;

@Controller
@RequestMapping(value="hospitalController")
public class HospitalController {

	@Autowired
	private HospitalService hospitalservice;
	
	//至展示所有医院页面
	@RequestMapping(value="tohospital")
	public String hospital(){
		return "hospital";
	}
	
	//数据库检索所有医院条目
	@RequestMapping(value="showhospital")
	public void showHospital(HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		List<Hospital> list =  hospitalservice.showAll();
		
		String jsonlist = JSON.toJSONString(list);
		JSON res = JSON.parseArray(jsonlist);
		//Json jsonallcodes = licenseService.objectToJson(list);
		try {
			response.getWriter().print(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//至添加医院页面
	@RequestMapping(value="toadd")
	public String toAdd(){
		return "addhospital";
	}
	
	//AJAX添加医院
	@RequestMapping(value="addhospital")
	public String addHospital(Hospital hospital){
		hospitalservice.addHospital(hospital);
		return "redirect:/hospitalController/tohospital";
	} 
	
	//AJAX删除医院
	@RequestMapping(value="deletehospital")
	public void deleteHospital(HttpServletRequest request){
		
		hospitalservice.deleteHospital(request.getParameter("hospitalNumber"));
	}
	
	
}
