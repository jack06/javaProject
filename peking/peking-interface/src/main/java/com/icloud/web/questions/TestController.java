package com.icloud.web.questions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icloud.common.DateTools;
import com.icloud.common.util.StringUtil;
import com.icloud.dto.vo.questions.AnswersVo;
import com.icloud.dto.vo.questions.ConfigVo;
import com.icloud.dto.vo.questions.DataVo;
import com.icloud.dto.vo.questions.InnerQuetions;
import com.icloud.dto.vo.questions.OptionsVo;
import com.icloud.dto.vo.questions.QuestionsVo;
import com.icloud.dto.vo.questions.TestModuleVo;
import com.icloud.model.event.EventAllowAccess;
import com.icloud.model.event.EventModule;
import com.icloud.model.questions.Answer;
import com.icloud.model.questions.QuestionOptions;
import com.icloud.model.questions.Questionnaire;
import com.icloud.model.questions.Questions;
import com.icloud.model.user.User;
import com.icloud.service.event.EventAllowAccessService;
import com.icloud.service.event.EventModuleService;
import com.icloud.service.questions.AnswerService;
import com.icloud.service.questions.QuestionOptionsService;
import com.icloud.service.questions.QuestionnaireService;
import com.icloud.service.questions.QuestionsService;
import com.icloud.service.user.UserService;
import com.icloud.web.BaseController;

/**
 * 
 * @author z
 *
 */
@RestController
public class TestController extends BaseController {
	//问卷配置service
	@Autowired
	private QuestionnaireService questionnaireService;
	//问题service
	@Autowired
	private QuestionsService questionsService;
	//问题选项service
	@Autowired
	private QuestionOptionsService questionOptionsService;
	//用户答案service
	@Autowired
	private AnswerService answerService;
	@Autowired
	private UserService userService;
	@Autowired
	private EventModuleService eventModuleService;
	@Autowired
	private EventAllowAccessService eventAllowAccessService;
	
	//前台传入问题id 和 对应选项的下标，然后找出选项id
	private List<InnerQuetions> innerQuetionsOptionList = new ArrayList<InnerQuetions>();
		
	/**获取 调查问卷**/
	@RequestMapping("/getTestModule")
	public String  getTestModule(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			
			if(!parameterObj.containsKey("moduleId")||!parameterObj.containsKey("sid")){
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "参数缺失");
				return pakageJsonp(resultObj);
			}
			String sessionKey = parameterObj.getString("sid");
			User user = userService.getUserFromSession(sessionKey);
			if(null==user){
				resultObj.put("errCode", "2000");
				resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
				return pakageJsonp(resultObj);
			}
			
			ConfigVo config = new ConfigVo();//配置
			DataVo data = new DataVo();//数据
			List<QuestionsVo> questionsVoList = new ArrayList<QuestionsVo>();//问卷问题及选项
			
			String moduleId = parameterObj.getString("moduleId");
			/*1、问卷表信息*/
			Questionnaire questionnaire = questionnaireService.findByKey(moduleId);
			if(null==questionnaire){
				resultObj.put("errCode", "3008");//用户不存在
				resultObj.put("resultMsg", "模块不存在");
				return pakageJsonp(resultObj);
			}
			config.setStartTime(DateTools.convertDateToString(questionnaire.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
			config.setEndTime(DateTools.convertDateToString(questionnaire.getEndTime(),"yyyy-MM-dd HH:mm:ss"));
			config.setActive(questionnaire.getIsOpen().equals("1")?true:false);
			
			data.setTitle(questionnaire.getTitle());
			data.setDescription(questionnaire.getDescription());
			Boolean isAllowTest = false;
			String userType = questionnaire.getUserType();
			//等于0,默认通过
			Date currentDate = new Date();
			if(StringUtil.checkObj(userType) && userType.equals("0")){
				if(currentDate.before(questionnaire.getStartTime())||currentDate.after(questionnaire.getEndTime())){
					isAllowTest = false;
				}else{
					isAllowTest = true;
				}
			}else{
				if(null!=user.getUserExt() && "1".equals(user.getUserExt().getIsCertification())){
					if(null!=user.getUserExt()){
						if(StringUtil.checkObj(user.getUserExt().getRoleId())){
							if(StringUtil.checkObj(userType)){
								JSONArray isAllowList = JSONArray.parseArray(userType);
								isAllowTest = isAllowList.contains(user.getUserExt().getRoleId());
							    if(isAllowTest){
							    	if(currentDate.before(questionnaire.getStartTime())||currentDate.after(questionnaire.getEndTime())){
										isAllowTest = false;
									}else{
										isAllowTest = true;
									}
							    }
							}
						}
					}
				}
			}
			config.setAllowTest(isAllowTest);//是否允许当前用户权限
		
			/*2、 登录用户答卷记录*/
			Answer answer = new Answer();
			answer.setQuestioonnaireId(moduleId);
			answer.setUserId(user.getId());
			List<Answer> myAnswerList = answerService.getList(answer);
			if(myAnswerList!=null && myAnswerList.size()>0){
				data.setHasTested(true);
			}else{
				data.setHasTested(false);
			}
			
			/*3、 问卷的问题列表*/
			Questions questions = new Questions();
			questions.setQuestionnaireId(moduleId);
			List<Questions> questionsList = questionsService.getList(questions);
		    if(null!=questionsList){
			    	QuestionsVo questionVo = null;
			    	Questions question = null;
				for(int i=0;i<questionsList.size();i++){
					question = questionsList.get(i);
					questionVo = new QuestionsVo(); 
					questionVo.setContent(question.getQuestionContent());
					questionVo.setQuestionId(question.getId());
					questionVo.setType(Integer.parseInt(question.getQuestionType()));
					List<QuestionOptions> options = question.getOptionList();
					
					OptionsVo optionVo = null;
					QuestionOptions option = null;
					List<OptionsVo> optionVoList =  new ArrayList<OptionsVo>();		
					for(int j=0;options!=null&&j<options.size();j++){
						option = options.get(j);
						optionVo = new OptionsVo();
						optionVo.setOptionId(option.getId());
						optionVo.setOptionName(option.getOptionsContent());
						optionVoList.add(optionVo);
					}
					questionVo.setOptions(optionVoList);
					questionsVoList.add(questionVo);
				 }
			
		    }
			data.setQuestions(questionsVoList);
			TestModuleVo testModuleVo = new TestModuleVo();
			testModuleVo.setConfig(config);
			testModuleVo.setData(data);
			
			resultObj.put("resultData", JSON.toJSON(testModuleVo));
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "获取问卷信息成功");
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	/**提交问卷
	 * @throws Exception **/
	@RequestMapping("/submitQuestion")
	public String submitQuestion(HttpServletRequest request) throws Exception{
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			if(!parameterObj.containsKey("moduleId")||!parameterObj.containsKey("sid")){
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "参数缺失");
				return pakageJsonp(resultObj);
			}
			String sessionKey = parameterObj.getString("sid");
			User user = userService.getUserFromSession(sessionKey);
			if(null==user){
				resultObj.put("errCode", "2000");//用户不存在
				resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
				return pakageJsonp(resultObj);
			}
			if(null==user.getUserExt()||user.getUserExt().getIsCertification().equals("2")){
				resultObj.put("errCode", "0013");
				resultObj.put("resultMsg","用户尚未认证");
				return pakageJsonp(resultObj);
			}
			
			if(user.getUserExt().getIsCertification().equals("0")){
				resultObj.put("errCode", "0020");
				resultObj.put("resultMsg","用户身份正在审核中");
				return pakageJsonp(resultObj);
			}
			String moduleId = parameterObj.getString("moduleId");
			JSONArray answersJson = parameterObj.getJSONArray("answers");
			List<AnswersVo> answersVoList = null;
			if(answersJson!=null){
				answersVoList = JSON.parseArray(answersJson.toJSONString(), AnswersVo.class);
			}
			//1、判断用户是否可以参与
			Questionnaire questionnaire = questionnaireService.findByKey(moduleId);
			if(null!=questionnaire){
				String userType = questionnaire.getUserType();
				//等于0,默认通过
				Boolean isAllowTest = false;
				if(StringUtil.checkObj(userType) && userType.equals("0")){
					isAllowTest = true;
				}else{
					if(null!=user.getUserExt() && "1".equals(user.getUserExt().getIsCertification())){
						if(null!=user.getUserExt()){
							if(StringUtil.checkObj(user.getUserExt().getRoleId())){
								if(StringUtil.checkObj(userType)){
									JSONArray isAllowList = JSONArray.parseArray(userType);
									isAllowTest = isAllowList.contains(user.getUserExt().getRoleId());
								}
							}
						}
					}
				}
				if(!isAllowTest){
					resultObj.put("errCode", "3002");
					resultObj.put("rusultMsg","没有权限参与问卷");
					return pakageJsonp(resultObj);
				}
			}
			
			Date currentDate = new Date();
			if(currentDate.before(questionnaire.getStartTime())||currentDate.after(questionnaire.getEndTime())){
				resultObj.put("errCode", "3009");//用户不存在
				resultObj.put("resultMsg","评价时间已过或未到");
				return pakageJsonp(resultObj);
			}
			if(questionnaire.getIsOpen().equals("0")){
				resultObj.put("errCode", "3006");//用户不存在
				resultObj.put("resultMsg","尚未开启，请耐心等待");
				return pakageJsonp(resultObj);
			}
			
			//2、判断是否已参与
			Answer anwser = new Answer();
			anwser.setQuestioonnaireId(moduleId);
			anwser.setUserId(user.getId());
			List<Answer> anserList = answerService.getList(anwser);
			if(anserList!=null && anserList.size()>0){
				resultObj.put("errCode", "3001");
				resultObj.put("rusultMsg","已参与");
				return pakageJsonp(resultObj);
			}
			
			if(answersVoList==null){
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "参数缺失");
				return pakageJsonp(resultObj);
			}
			//查询模块对应问题
			Questions questions = new Questions();
			questions.setQuestionnaireId(moduleId);
			List<Questions> questionsList = questionsService.getList(questions);
			List<String> anwserIdList = new ArrayList<String>();
			//3、保存答案
			Questions question = null;
			for (AnswersVo answersVo : answersVoList) {
				question = new Questions();
				question.setId(answersVo.getQuestionId());
				int index = questionsList.indexOf(question);
				if(index>=0){
					question = questionsList.get(index);
				}
				//判断是否是选择题还是填空题
				if(StringUtil.checkObj(question.getQuestionType()) &&
						("1".equals(question.getQuestionType()) || "2".equals(question.getQuestionType()))
								&& StringUtil.checkObj(answersVo.getOptionValue())){
					String[] options = answersVo.getOptionValue().split(",");
					if(options!=null && options.length>0){
						for (int i = 0; i < options.length; i++) {
							Answer answer = new Answer();
							answer.setQuestionId(answersVo.getQuestionId());
							answer.setQuestioonnaireId(moduleId);
							answer.setUserId(user.getId());
							answer.setOptionsId(options[i]);
							answer.setCreateDate(new Date());
							answerService.save(answer);
							anwserIdList.add(answer.getId());
							
//							InnerQuetions innerQuetions = new InnerQuetions();
//							innerQuetions.setIndex(Integer.parseInt(options[i]));
//							innerQuetions.setQuestionId(answersVo.getQuestionId());
//							int index2 = innerQuetionsOptionList.indexOf(innerQuetions);
//							if(index2>=0){
//								innerQuetions = innerQuetionsOptionList.get(index2);
//								Answer answer = new Answer();
//								answer.setQuestionId(answersVo.getQuestionId());
//								answer.setQuestioonnaireId(moduleId);
//								answer.setUserId(user.getId());
//								answer.setQuestionId(innerQuetions.getOptionId());
//								answerService.save(answer);
//								anwserIdList.add(answer.getId());
//							}
						}
					}
				}else{
					Answer answer = new Answer();
					answer.setQuestionId(answersVo.getQuestionId());
					answer.setQuestioonnaireId(moduleId);
					answer.setUserId(user.getId());
					answer.setOptionsId(answersVo.getOptionValue());
					answer.setCreateDate(new Date());
					answerService.save(answer);
					anwserIdList.add(answer.getId());
				}
			}
			resultObj.put("resultData", JSON.toJSON(anwserIdList));
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "提交问卷成功");
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
			resultObj.put("resultData", null);
			resultObj.put("errCode", "1001");
			resultObj.put("resultMsg", "系统内部错误");
			return pakageJsonp(resultObj);
		}
		
	}
	
	@RequestMapping("/addTestConfig")
	public String addTestConfig(HttpServletRequest request) throws Exception{
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("eventId")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失eventId");
			return pakageJsonp(resultObj);
		}
		if(!parameterObj.containsKey("moduleType")){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失moduleType");
			return pakageJsonp(resultObj);
		}
		String configStr = parameterObj.getString("config");
		if(StringUtils.isBlank(configStr)){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失config");
			return pakageJsonp(resultObj);
		}
		String questionsStr = parameterObj.getString("questions");
		if(StringUtils.isBlank(configStr)){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失question");
			return pakageJsonp(resultObj);
		}
		String eventId = parameterObj.getString("eventId");
		String moduleId = parameterObj.getString("moduleId");
		String moduleType = parameterObj.getString("moduleType");
		
		JSONObject config = JSONObject.parseObject(configStr);
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setDescription(config.getString("description"));
		questionnaire.setTitle(config.getString("title"));
		questionnaire.setEventId(eventId);
		questionnaire.setIsOpen(config.getBoolean("isActive")?"1":"0");

		//取对应事件的权限作为模块的权限
		List<EventAllowAccess> accessList = eventAllowAccessService.findListByEvent(eventId);
		if(null==accessList||accessList.size()<=0){
			questionnaire.setUserType("0");
		}else{
			JSONArray array = new JSONArray(); 
			for(EventAllowAccess aa:accessList){
				array.add(aa.getUserRoleId());
			}
			questionnaire.setUserType(array.toJSONString());
		 }
		
		Calendar nowDate = Calendar.getInstance();
		if(StringUtils.isNotBlank(config.getString("startTime"))){
			questionnaire.setStartTime(DateTools.str2Date(config.getString("startTime"),"yyyy-MM-dd HH:mm:ss"));
		}else{
			questionnaire.setStartTime(nowDate.getTime());
		}
		
		if(StringUtils.isNotBlank(config.getString("endTime"))){
			questionnaire.setEndTime(DateTools.str2Date(config.getString("endTime"),"yyyy-MM-dd HH:mm:ss"));
		}else{
			nowDate.set(Calendar.YEAR, nowDate.get(Calendar.YEAR)+1);
			questionnaire.setEndTime(nowDate.getTime());
		}
		
		/**第一次添加  **/
		if(StringUtils.isBlank(moduleId)){
			questionnaireService.save(questionnaire);
			moduleId = questionnaire.getId();
		}else{
			questionnaire.setId(moduleId);
			questionnaireService.update(questionnaire);
		}
		
		//先删掉问题
		questionsService.deleteByModule(moduleId);
		questionOptionsService.deleteByModule(moduleId);
		//再添加
		JSONArray questionArray = JSONArray.parseArray(questionsStr);
		Questions q = null;
		JSONObject questionJson = null;
		int startNo = questionsService.selectMaxNo(moduleId);
		for(int i=0;null!=questionArray&&i<questionArray.size();i++){
			q = new Questions();
			questionJson = questionArray.getJSONObject(i);
			q.setQuestionContent(questionJson.getString("content"));
			q.setQuestionType(questionJson.getString("questionType"));
			q.setQuestionnaireId(moduleId);
			q.setQuestionNo(++startNo);
			questionsService.save(q);
			if(!questionJson.getString("questionType").equals("3")){
			JSONArray qoArray = questionJson.getJSONArray("options");
			String optionName = null ;
			QuestionOptions option = null;
			for(int j=0;j<qoArray.size();j++){
				optionName = qoArray.getString(j);
				option = new QuestionOptions();
				option.setQuestionId(q.getId());
				option.setQuestionnaireId(moduleId);
				option.setIsCorrect("0");
				option.setOptionsContent(optionName);
				questionOptionsService.save(option);
			}
			
			}
		}
		
		EventModule em = new EventModule();
		
		em.setEventId(eventId);
		em.setModuleLabel(moduleType);
		/**先刪後加 以防重複**/
		eventModuleService.delete(em);
		em.setModuleId(moduleId);
		eventModuleService.save(em);

		JSONObject resultData = new JSONObject();
		resultData.put("moduleId",moduleId);
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg","問卷模块添加成功");
		return pakageJsonp(resultObj);
		
		
	}
	
}
