package com.icloud.web.vote;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.DateTools;
import com.icloud.common.ResponseUtils;
import com.icloud.model.vote.VoteConfig;
import com.icloud.model.vote.VoteOption;
import com.icloud.model.vote.VoteRecord;
import com.icloud.service.vote.VoteOptionService;
import com.icloud.service.vote.VoteRecordService;
import com.icloud.service.vote.VoteService;

@Controller
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	@Autowired
	private VoteOptionService voteOptionService;
	@Autowired
	private VoteRecordService voteRecordService;
	@RequestMapping("/admin/to_vote")
	public String toVote(){
		return "/vote/vote";
	}
	
	@RequestMapping("/admin/vote_result")
	public String getVoteResult(HttpServletRequest request,HttpServletResponse response){
		JSONObject resultObj = new JSONObject();
		String eventId = request.getParameter("eventId");
		if(StringUtils.isBlank(eventId)){
			resultObj.put("errCode","0001");
			resultObj.put("resultMsg","事件ID为空");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		List<VoteConfig> configs = voteService.selectByEvent(eventId);
		
		JSONArray result = new JSONArray();
		if(null!=configs&&configs.size()>0){
			VoteConfig config = configs.get(0);
			VoteRecord record = new VoteRecord();
			record.setModuleId(config.getId());
			int total = voteRecordService.selectOptionCount(record);
			resultObj.put("total", total);
			List<VoteOption> options = voteOptionService.selectByModule(config.getId());
			VoteOption option = null;
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数
			for(int i=0;null!=options&&options.size()>i;i++){
				option = options.get(i);
				JSONObject optionJson = new JSONObject();
				optionJson.put("optiond", option.getOptiond());
				record.setOptionId(option.getId());
				int optionCount = voteRecordService.selectOptionCount(record);
				optionJson.put("count",optionCount );
				if(total>0){
				    optionJson.put("percentage", ((Float.parseFloat(df.format((float)optionCount/total))*100+"%")));
				}else{
					optionJson.put("percentage", 0+"%");
						
				}
				result.add(optionJson);
			}
			
			PageInfo<VoteRecord> page = voteRecordService.findPage(1, 10, config.getId());
			JSONArray records = new JSONArray();
			List<VoteRecord> list = page.getList();
			
			for(int i=0;null!=list&&i<list.size();i++){
				record = list.get(i);
				JSONObject o = new JSONObject();
				o.put("nick", record.getNick()==null?"未知":record.getNick());
				o.put("headerImg", record.getHeaderImg());
				o.put("option", record.getOption().getOptiond());
				o.put("createDate", DateTools.date2Str(record.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				records.add(o);
			}
			resultObj.put("moduleId", config.getId());
			resultObj.put("pages", page.getPages());
			resultObj.put("records", records);
			resultObj.put("result", result);
			resultObj.put("errCode","0000");
			resultObj.put("resultMsg","成功");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
			
		}else{
			resultObj.put("errCode","0002");
			resultObj.put("resultMsg","该事件未投票报名模块");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		
	}
	@RequestMapping("/admin/vote_record_list")
	public String voteRecordList(HttpServletRequest request,HttpServletResponse response){
		JSONObject resultObj = new JSONObject();
		String moduleId = request.getParameter("moduleId");
		if(StringUtils.isBlank(moduleId)){
			resultObj.put("errCode","0001");
			resultObj.put("resultMsg","模块ID为空");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		String pageNo = request.getParameter("pageNo");
		PageInfo<VoteRecord> page = voteRecordService.findPage(StringUtils.isBlank(pageNo)?1:Integer.parseInt(pageNo), 10, moduleId);
		JSONArray records = new JSONArray();
		List<VoteRecord> list = page.getList();
		VoteRecord record = null;
		for(int i=0;null!=list&&i<list.size();i++){
			record = list.get(i);
			JSONObject o = new JSONObject();
			o.put("nick", record.getNick()==null?"未知":record.getNick());
			o.put("headerImg", record.getHeaderImg());
			o.put("option", record.getOption().getOptiond());
			o.put("createDate", DateTools.date2Str(record.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			records.add(o);
		}
		resultObj.put("records", records);
		resultObj.put("errCode","0000");
		resultObj.put("resultMsg","成功");
		ResponseUtils.renderJson(response, resultObj.toJSONString());
		return null;
	}

}
