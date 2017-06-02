package com.icloud.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icloud.dto.BaseDto;
import com.icloud.form.RaiseForm;
import com.icloud.model.RaiseMsq;
import com.icloud.service.RaiseMsqService;
import com.icloud.util.RequestUtil;

@RestController
public class RaiseMsqController extends BaseController {
	
	public final static Logger log = LoggerFactory.getLogger(RaiseMsqController.class);
	@Autowired
	private RaiseMsqService raiseMsqService;
	/**
	 * 提醒发货
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/raise/notify", method = { RequestMethod.GET , RequestMethod.POST })
	public void notifyDelivery(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request);  
//		System.out.println("jsonText=="+jsonText);
		log.error(jsonText);
		RaiseMsq record = JSON.parseObject(jsonText,RaiseMsq.class);

		BaseDto dto = null;
		try {
			int idL = raiseMsqService.notifyDelivery(record);
			dto = new BaseDto("success","0000","提醒成功");
		} catch (Exception e) {
			e.printStackTrace();
			dto = new BaseDto("fail","10001","提醒失败");
		}
		log.error(JSONObject.toJSONString(dto));
		outObject(JSONObject.toJSONString(dto));
	}

}
