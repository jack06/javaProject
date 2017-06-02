package com.icloud.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.icloud.dto.LuckyNoListDto;
import com.icloud.service.LuckyNoService;
import com.icloud.util.RequestUtil;

@RestController
@RequestMapping(value="/luckyNo")
public class LuckyNoController extends BaseController{

	@Autowired
	private LuckyNoService luckyNoService;
	
	/**
     * 根据众筹id获取幸运号
     */
    @RequestMapping(value = { "findLuckyNoByRaiseId" }, method = RequestMethod.POST)
	public void findLuckyNoByRaiseId(HttpServletRequest request, HttpServletResponse response){
    	LuckyNoListDto dto = null;
		try {
			JSONObject jsonObj = RequestUtil.readToJSONObect(request);
			dto = luckyNoService.selectByRaiseId(jsonObj.getLongValue("id"));
		} catch (Exception e) {
			e.printStackTrace();
			dto = new LuckyNoListDto("fail", "10001", null);
			dto.setResultCode("10001");
			dto.setResultType("fail");
			dto.setErrorMsg("查询失败");
		}
		String json = JSONObject.toJSONString(dto);
		System.out.println(json);
		outObject(json);
    }
}
