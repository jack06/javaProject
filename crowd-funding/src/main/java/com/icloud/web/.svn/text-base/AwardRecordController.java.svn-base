package com.icloud.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icloud.dto.AwardRecordListDto;
import com.icloud.dto.AwardRecordQueryDto;
import com.icloud.dto.AwardRecordVoDto;
import com.icloud.dto.BaseDto;
import com.icloud.dto.ExportExcleDto;
import com.icloud.dto.RaiseQueryDto;
import com.icloud.form.AwardRecordFrom;
import com.icloud.form.RaiseForm;
import com.icloud.model.AwardRecord;
import com.icloud.service.AwardRecordService;
import com.icloud.util.RequestUtil;
import com.icloud.vo.AwardRecordVo;

@RestController
@RequestMapping(value = "/award_record")
public class AwardRecordController extends BaseController {
	
	public final static Logger log = LoggerFactory.getLogger(AwardRecordController.class);

	@Autowired
	 private AwardRecordService awardRecordService;
	
	/**
     * 导出众筹中奖数据
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping(value = "/exportAwardRecord", method = {RequestMethod.POST })
    public void ListAwardRecord(HttpServletRequest request) throws Exception {
    	log.info("-----------------服务端ListAwardRecord------------------");
    	String jsonText = RequestUtil.readPostContent(request); 
		log.error(jsonText);
		AwardRecordFrom awardRecordFrom = JSON.parseObject(jsonText,AwardRecordFrom.class);
    	ExportExcleDto dto = null;
		try {
			dto = awardRecordService.ExportExlceAwardRecord(awardRecordFrom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dto = new ExportExcleDto("fail","10001","查询失败");
		}
    	
    	String ss = JSONObject.toJSONString(dto);
		outObject(JSONObject.toJSONString(dto));
    }
    
    @RequestMapping(value = "/findAwardRecordById", method = {RequestMethod.POST })
    public void findAwardRecordById(HttpServletRequest request) throws Exception {
    	log.info("-----------------服务端findAwardRecordById------------------");
    	String id = RequestUtil.readPostContent(request); 
    	AwardRecordQueryDto dto = null;
		try {
			AwardRecord awardRecord = awardRecordService.findAwardRecordById(id);
			dto = new AwardRecordQueryDto("sucess","10000",awardRecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dto = new AwardRecordQueryDto("fail","10001","查询失败");
		}
    	
    	String ss = JSONObject.toJSONString(dto);
		outObject(JSONObject.toJSONString(dto));
    }
    
    @RequestMapping(value = "/awardListByPage")
	public void listByPage(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request); 
		log.error(jsonText);
		AwardRecordFrom awardRecordFrom = new AwardRecordFrom();
		AwardRecordListDto  dto = null;
		try {
			if(jsonText!=null && !"".equals(jsonText)){
				awardRecordFrom = JSON.parseObject(jsonText,AwardRecordFrom.class);
			}
			dto = awardRecordService.listByPage(awardRecordFrom);
		} catch (Exception e) {
		   	 e.printStackTrace();
		   	dto = new AwardRecordListDto("fail","10001","查询失败");
		}
		String ss = JSONObject.toJSONString(dto);
		outObject(JSONObject.toJSONString(dto));
    }
    
    @RequestMapping(value = "/findAwardRecordById")
	public void findByOpenid(HttpServletRequest request) throws Exception {
		String id = RequestUtil.readPostContent(request);
		AwardRecordQueryDto dto = null;
		try {
			AwardRecord awardRecord = awardRecordService.findByOpenid(id);
			dto = new AwardRecordQueryDto("success","10000",awardRecord);
		} catch (Exception e) {
			e.printStackTrace();
			dto = new AwardRecordQueryDto("fail","10001","查询失败");
		}
		outObject(JSONObject.toJSONString(dto));
    }
    
    @RequestMapping(value = "/findAwardRecordVoById")
	public void findAwardRecordVoById(HttpServletRequest request) throws Exception {
		String id = RequestUtil.readPostContent(request);
		AwardRecordVoDto dto = null;
		try {
			AwardRecordVo awardRecordVo = awardRecordService.findAwardRecordVoById(id);
			dto = new AwardRecordVoDto("success","10000",awardRecordVo);
		} catch (Exception e) {
			e.printStackTrace();
			dto = new AwardRecordVoDto("fail","10001","查询失败");
		}
		outObject(JSONObject.toJSONString(dto));
    }
    
    @RequestMapping(value = "/updteAwardRecord")
	public void updateAwardRecord(HttpServletRequest request) throws Exception {
    	String jsonText = RequestUtil.readPostContent(request); 
		log.error(jsonText);
		AwardRecord record = new AwardRecord();
		if(jsonText!=null && !"".equals(jsonText)){
			record = JSON.parseObject(jsonText,AwardRecord.class);
		}

		BaseDto dto = awardRecordService.updateAwardRecord(record);

		outObject(JSONObject.toJSONString(dto));
    }
    /**
     * 根据id删除
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/deleteById")
	public void deleteById(HttpServletRequest request) throws Exception {
    	String id = RequestUtil.readPostContent(request); 
		log.error(id);
		BaseDto dto = awardRecordService.deleteById(id);
		outObject(JSONObject.toJSONString(dto));
    }
    
    @RequestMapping(value = "/addAwardRecord")
	public void addAwardRecord(HttpServletRequest request) throws Exception {
    	String jsonText = RequestUtil.readPostContent(request); 
		log.error(jsonText);
		AwardRecord record = new AwardRecord();
		if(jsonText!=null && !"".equals(jsonText)){
			record = JSON.parseObject(jsonText,AwardRecord.class);
		}

		BaseDto dto = awardRecordService.addAwardRecord(record);

		outObject(JSONObject.toJSONString(dto));
    }
    
}
