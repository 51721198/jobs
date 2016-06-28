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

/**
 * 
 * @ClassName: LicenseController
 * @Description: 序列号管理系统控制器
 * @author: Liu.Dun
 * @date: 2016年6月27日 下午8:40:05
 */
public class LicenseController {
	
	@Autowired
	private LicenseService licenseService;
	
	@Autowired
	private HospitalService hospitalService;
	
	@RequestMapping(value="tocreatecode")
	public String toCreate(HttpServletRequest request){
		return "creatcode2";
	}
	
	
	@RequestMapping(value="createcode")
	public void sourceCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		
		String sourcecode = licenseService.createSourceCode(request.getParameter("duedate"));
		
		String jsonstr = "{'success':'true','msg':'"+sourcecode+"'}";
		JSON res = JSON.parseObject(jsonstr);
		
		response.getWriter().print(res);
	}
	
	@RequestMapping(value="encryptcode")
	public void encryptCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String code = request.getParameter("encryptcode");
		
		response.setContentType("text/html;charset=UTF-8");
		String encryptcode = licenseService.createEncryptCode(code);
		
		String jsonstr = "{'success':'true','msg':'"+encryptcode+"'}";
		JSON res = JSON.parseObject(jsonstr);
		
		response.getWriter().print(res);
	}
	
	
	@RequestMapping(value="toshowallcodes")
	public String showAll(HttpServletRequest request,HttpServletResponse response){
		
		return "showallcodes2";
	}
	
	/**
	 * @param:
	 * @return: void
	 * @Title: showAllCodes
	 * @Description: 获取所有序列号，并且获取序列号对应的医院名称
	 * @param request
	 * @param response
	 *
	 */
	@RequestMapping(value="showallcodes")
	public void showAllCodes(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		List<LicenseDetail> list =  licenseService.listAllCodes();
		
		for(LicenseDetail ldetail:list){
			String hospitalName = null;
			hospitalName = hospitalService.selectHospitalName(ldetail.getHospitalNumber());
			ldetail.setHospitalName(hospitalName);
			int lastday = licenseService.endDate(ldetail.getExpiredDate());    
			
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
		try {
			response.getWriter().print(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param:
	 * @return: void
	 * @Title: deleteCode
	 * @Description: 删除序列号，如果序列号未过期则禁止删除
	 * @param request
	 * @param response
	 *
	 */
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
				e.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping(value="savecode")
	public String saveCode(LicenseDetail licensedetail){
		
		licenseService.saveCode(licensedetail);
		return "redirect:/licenseController/toshowallcodes";
	}
	
}
