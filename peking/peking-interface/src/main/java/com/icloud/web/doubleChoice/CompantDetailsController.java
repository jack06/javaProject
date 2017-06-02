package com.icloud.web.doubleChoice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.icloud.model.doubleChoice.CompanyDetails;
import com.icloud.service.doubleChoice.CompanyDetailsService;
import com.icloud.web.BaseController;

@RestController
public class CompantDetailsController extends BaseController {
	
	@Autowired
	private CompanyDetailsService companyDetailsService;
	
	@RequestMapping("/getCompanyList")
	public String getCompanyList(HttpServletRequest request,HttpServletResponse response){
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if(!parameterObj.containsKey("eventId")){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失eventId");
			return pakageJsonp(resultObj);
		}
		
		JSONObject resultData = new JSONObject();
		String eventId = parameterObj.getString("eventId");
		if(StringUtils.isBlank(eventId)){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失eventId");
			return pakageJsonp(resultObj);
		}
		List<CompanyDetails> list = companyDetailsService.selectNameList(eventId);
		resultData.put("companyList", list);
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取列表成功");
		return pakageJsonp(resultObj);
		
	}
	
	@RequestMapping("/getCompanyDetails")
	public String getCompanyDetails(HttpServletRequest request,HttpServletResponse response){
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if(!parameterObj.containsKey("companyId")){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失companyId");
			return pakageJsonp(resultObj);
		}
		
		JSONObject resultData = new JSONObject();
		String id = parameterObj.getString("companyId");
		if(StringUtils.isBlank(id)){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失companyId");
			return pakageJsonp(resultObj);
		}
		CompanyDetails details = companyDetailsService.findByKey(id);
		resultData.put("details", details);
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取详情成功");
		return pakageJsonp(resultObj);
		
	}

}
