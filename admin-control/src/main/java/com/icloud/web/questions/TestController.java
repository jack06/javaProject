package com.icloud.web.questions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

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
import com.icloud.model.questions.Answer;
import com.icloud.model.questions.AnswerRecord;
import com.icloud.model.questions.Questionnaire;
import com.icloud.model.questions.Questions;
import com.icloud.model.staffmanage.Tuserrole;
import com.icloud.service.questions.AnswerService;
import com.icloud.service.questions.QuestionnaireService;
import com.icloud.service.staffmanage.StaffRoleManageService;


@Controller
public class TestController {
	@Autowired
	private QuestionnaireService questionnaireService;
    @Autowired
	private AnswerService answerService;
    @Autowired
    private StaffRoleManageService staffRoleManageService;	
	@RequestMapping("/admin/to_test")
	public String toTest(){
		return "/test/test";
	}
	
	
	@RequestMapping("/admin/test_config")
	public String getTestConfig(HttpServletRequest request,HttpServletResponse response){
		String eventId = request.getParameter("eventId");
		JSONObject resultObj = new JSONObject();
		if(StringUtils.isBlank(eventId)){
			resultObj.put("errCode","0001");
			resultObj.put("resultMsg","事件ID为空");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		
		List<Questionnaire> list = questionnaireService.findForList(eventId);
		if(null!=list&&list.size()>0){
			Questionnaire questionnaire = list.get(0);
			JSONObject data = new JSONObject();
			data.put("id", questionnaire.getId());
			data.put("title", questionnaire.getTitle());
			data.put("description", questionnaire.getDescription());
			data.put("startTime", DateTools.convertDateToString(questionnaire.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
			data.put("entTime",  DateTools.convertDateToString(questionnaire.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
			data.put("status", questionnaire.getIsOpen().equals("1")?"开启":"关闭");
			if(!questionnaire.getUserType().equals("0")){
			    JSONArray array =JSONArray.parseArray(questionnaire.getUserType());
			    String[] ids = new String[array.size()];
			    for(int i=0;i<array.size();i++){
			    	ids[i] = array.getString(i);
			    }
			    List<Tuserrole> rolelist = staffRoleManageService.selectRoleList(ids);
			    data.put("roles", rolelist);
			}else{
				data.put("roles","0");
			}
			resultObj.put("config", data);
			
			PageInfo<AnswerRecord> page = answerService.findAnswerRecord(1,10,questionnaire.getId());
			List<AnswerRecord> records = page.getList();
			JSONArray recordArray = new JSONArray();
			if(null!=records&&records.size()>0){
				AnswerRecord r = null;
				for(int i=0;i<records.size();i++){
					JSONObject o = new JSONObject();
					r =  records.get(i);
					o.put("nick",r.getNick());
					o.put("wxHeaderImg", r.getWxHeadImg());
					o.put("recordId", r.getId());
					o.put("createTime", DateTools.convertDateToString(r.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
					recordArray.add(o);
				}
				
			}
			resultObj.put("pages", page.getPages());
			resultObj.put("records", recordArray);
			resultObj.put("errCode","0000");
			resultObj.put("resultMsg","成功");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}else{
			resultObj.put("errCode","0002");
			resultObj.put("resultMsg","该事件未配置问卷模块");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
	}
	
	/**获取参与问卷分页 **/
	@RequestMapping("/admin/test_records")
	public String answerRecord(HttpServletRequest request,HttpServletResponse response){
		String moduleId = request.getParameter("moduleId");
		JSONObject resultObj = new JSONObject();
		if(StringUtils.isBlank(moduleId)){
			resultObj.put("errCode","0002");
			resultObj.put("resultMsg","模块ID为空");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
        String pageNo = request.getParameter("pageNo");
		PageInfo<AnswerRecord> page = answerService.findAnswerRecord(StringUtils.isBlank(pageNo)?1:Integer.parseInt(pageNo),10,moduleId);
		List<AnswerRecord> records = page.getList();
		JSONArray recordArray = new JSONArray();
		if(null!=records&&records.size()>0){
			AnswerRecord r = null;
			for(int i=0;i<records.size();i++){
				JSONObject o = new JSONObject();
				r =  records.get(i);
				o.put("nick",r.getNick());
				o.put("wxHeaderImg", r.getWxHeadImg());
				o.put("recordId", r.getId());
				o.put("createTime", DateTools.convertDateToString(r.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
				recordArray.add(o);
			}
			
		}
		resultObj.put("records", recordArray);
		resultObj.put("errCode","0000");
		resultObj.put("resultMsg","成功");
		ResponseUtils.renderJson(response, resultObj.toJSONString());
		return null;
		
	}
	
	@RequestMapping("/admin/test_result")
	public String getTestResult(HttpServletRequest request,HttpServletResponse response){
		JSONObject resultObj = new JSONObject();
		String moduleId = request.getParameter("moduleId");
		String userId = request.getParameter("userId");
		if(StringUtils.isBlank(userId)||StringUtils.isBlank(moduleId)){
			resultObj.put("errCode","0002");
			resultObj.put("resultMsg","模块ID为空");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		
		Answer answer = new Answer();
		answer.setQuestioonnaireId(moduleId);
		answer.setUserId(userId);
		List<Answer> list = answerService.selectAllByQuestionnaire(answer);

		SortedMap<String,Questions> result = new TreeMap<String,Questions>();
		Iterator<Answer> it = list.iterator();
		while(it.hasNext()){
			Answer an = it.next();
			Questions q = null;
			if(result.containsKey(an.getQuestionId())){
				q = result.get(an.getQuestionId());
			}else{
				q = an.getQuestion();
			}
			List<Answer> ans = q.getAnswers();
			if(null==ans){
				ans = new ArrayList<Answer>();
			}
			ans.add(an);
			q.setAnswers(ans);
			result.put(an.getQuestionId(), q);
			
		}
		request.setAttribute("list", result.values());
		
		return "/test/test_result";
	}
	
	
}
