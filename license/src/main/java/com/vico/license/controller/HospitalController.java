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
import com.vico.license.pojo.LicenseDetail;
import com.vico.license.service.HospitalService;

@Controller
@RequestMapping(value="hospitalController")
public class HospitalController {

	private HospitalService hospitalservice;
	
	public HospitalService getHospitalservice() {
		return hospitalservice;
	}
	
	@Autowired
	public void setHospitalservice(HospitalService hospitalservice) {
		this.hospitalservice = hospitalservice;
	}

	@RequestMapping(value="tohospital")
	public String hospital(){
		return "hospital";
	}
	
	@RequestMapping(value="showhospital")
	public void showHospital(HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		List<Hospital> list =  hospitalservice.showAll();
		
		String jsonlist = JSON.toJSONString(list);
		System.out.println(jsonlist);
		
		JSON res = JSON.parseArray(jsonlist);
		System.out.println(res);
		//Json jsonallcodes = licenseService.objectToJson(list);
		
		try {
			response.getWriter().print(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="toadd")
	public String toAdd(){
		return "addhospital";
	}
	
	@RequestMapping(value="addhospital")
	public String addHospital(Hospital hospital){
		hospitalservice.addHospital(hospital);
		return "redirect:/hospitalController/tohospital";
	} 
}
