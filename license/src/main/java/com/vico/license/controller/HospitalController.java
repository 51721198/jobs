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
import com.vico.license.service.LicenseService;


	/** 
	 * 
	 *@医院信息系统控制器
	 * @author Liu.Dun
	 *
	 */
@Controller
@RequestMapping(value="hospitalController")
public class HospitalController {

	@Autowired
	private HospitalService hospitalservice;
	
	@Autowired
	private LicenseService licenseservice;
	
	@RequestMapping(value="toshowallhospital")
	public String hospital(){
		return "hospital2";
	}
	
	@RequestMapping(value="showhospital")
	public void showAllHospital(HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		List<Hospital> list =  hospitalservice.showAllHospitals();
		
		String jsonlist = JSON.toJSONString(list);
		JSON res = JSON.parseArray(jsonlist);
		try {
			response.getWriter().print(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="toaddhospital")
	public String toAdd(){
		return "addhospital2";
	}
	
	/**
	 * @由医院编号获取医院信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="showone")
	public void selectOneHospital(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		
		if(request.getParameter("hospitalNumber") != null && request.getParameter("hospitalNumber") != "" ){
		int hospitalNumber = Integer.parseInt(request.getParameter("hospitalNumber"));
		Hospital hospital = hospitalservice.showOneHospital(hospitalNumber);
				
		String jsonlist = JSON.toJSONString(hospital);
				JSON res = JSON.parseObject(jsonlist);
				try {
					response.getWriter().print(res);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}	
		else{
			String jsonlist = "{}";
		JSON res = JSON.parseObject(jsonlist);
		try {
			response.getWriter().print(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
		
	
	@RequestMapping(value="addhospital")
	public String addHospital(Hospital hospital){
		
		//根据hospitalNumber的值是否存在判断是添加还是修改
		if(hospital.getHospitalNumber() == null){
		hospitalservice.addHospital(hospital);
		return "redirect:/hospitalController/toshowallhospital";
		}
		
		//修改医院信息
		hospitalservice.updateHospital(hospital);
		return "redirect:/hospitalController/toshowallhospital";
	} 
	
	/**
	 * @param:
	 * @return: void
	 * @Title: deleteHospital
	 * @Description: 删除医院，有关联序列号信息的医院禁止删除
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="deletehospital")
	public void deleteHospital(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		List<LicenseDetail> list = licenseservice.selectByhospitalNumber(Integer.parseInt(request.getParameter("hospitalNumber")));
		if(list.isEmpty()){
			String jsonstr = "{'success':'true','msg':'删除成功'}";
			hospitalservice.deleteHospital(request.getParameter("hospitalNumber"));
			JSON res = JSON.parseObject(jsonstr);
			try {
				response.getWriter().print(res);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else{
			String jsonstr = "{'success':'false','msg':'该医院有关联序列号信息，删除失败'}";
			JSON res = JSON.parseObject(jsonstr);
			try {
				response.getWriter().print(res);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}
