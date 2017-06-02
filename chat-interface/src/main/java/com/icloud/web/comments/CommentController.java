package com.icloud.web.comments;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.DateTools;
import com.icloud.model.comments.CommentConfig;
import com.icloud.model.comments.CommentRecord;
import com.icloud.model.comments.Comments;
import com.icloud.model.event.EventAllowAccess;
import com.icloud.model.event.EventModule;
import com.icloud.model.user.User;
import com.icloud.service.comment.CommentConfigService;
import com.icloud.service.comment.CommentRecordService;
import com.icloud.service.comment.CommentsService;
import com.icloud.service.event.EventAllowAccessService;
import com.icloud.service.event.EventModuleService;
import com.icloud.service.user.UserService;
import com.icloud.web.BaseController;


@RestController
public class CommentController extends BaseController {

	@Autowired
	private CommentConfigService commentConfigService;
	@Autowired
	private CommentRecordService commentRecordService;
	@Autowired
	private UserService userService;
	@Autowired
	private EventModuleService eventModuleService;
	@Autowired
	private EventAllowAccessService eventAllowAccessService;
	@Autowired
	private CommentsService commentsService;
	/**获取评论模块 **/
	@RequestMapping("/getCommentModule")
	public String getCommentModule(HttpServletRequest request) throws Exception{
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if(!parameterObj.containsKey("moduleId")||!parameterObj.containsKey("sid")){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失moduleId，sid");
			return pakageJsonp(resultObj);
		}
		
		JSONObject resultData = new JSONObject();
		String moduleId = parameterObj.getString("moduleId");
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if(null==user){
			resultObj.put("errCode", "2000");//用户不存在
			resultObj.put("resultMsg","用户不存在，请检查sessionKey");
			return pakageJsonp(resultObj);
		} 
		//配置
		CommentConfig config = commentConfigService.findByKey(moduleId);
		JSONObject jsonConfig = new JSONObject();
		Date currentDate = new Date();
		if(null!=config){
			String isAllowType = config.getIsAllowType();
			if("0".equals(isAllowType)){
				//0表示不限制評論
				if(currentDate.before(config.getStartTime())||currentDate.after(config.getEndTime())){
					jsonConfig.put("isAllowComment",false);
				}else{
			       jsonConfig.put("isAllowComment",true);
				}
			}else {
				if(null!=user.getUserExt()){
					if(user.getUserExt().getIsCertification().equals("0")||user.getUserExt().getIsCertification().equals("2")){
					   jsonConfig.put("isAllowComment",false);
					}else{
					   JSONArray isAllowList = JSONArray.parseArray(isAllowType);
					   Boolean isAllowComment = isAllowList.contains(user.getUserExt().getRoleId());
					   if(isAllowComment){
						   if(currentDate.before(config.getStartTime())||currentDate.after(config.getEndTime())){
								jsonConfig.put("isAllowComment",false);
							}else{
						        jsonConfig.put("isAllowComment",true);
							}
					   }
					}
				}else{
					jsonConfig.put("isAllowComment",false);
				}
			}
			
		}
		resultData.put("config", jsonConfig);
		//评论记录
		Comments comments = new Comments();
		comments.setCommentConfigId(moduleId);
		
		String size = parameterObj.getString("size");
		String offset = parameterObj.getString("offset");
		int pageNo = StringUtils.isNotBlank(offset)?Integer.parseInt(offset):1;
		int pageSize = StringUtils.isNotBlank(size)?Integer.parseInt(size):10;
		PageInfo<Comments> page = commentsService.findForList(pageNo,pageSize,comments);
		List<Comments> list = page.getList();
		JSONObject data = new JSONObject();
		JSONArray commentsList = new JSONArray();
		if(null!=list&&list.size()>0){
			JSONObject o =null;
			Comments currentComments  = null;
			for(int i=0;null!=list&&i<list.size();i++){
				currentComments = list.get(i);
				o = new JSONObject();
				o.put("commentId", currentComments.getId());
				o.put("avatar", currentComments.getHeadrImg());
				o.put("nick", currentComments.getNick());
				o.put("createAt", DateTools.date2Str(currentComments.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				JSONArray contents = new JSONArray();
				List<CommentRecord> records = currentComments.getRecordList();
				for(int j=0;null!=records&&j<records.size();j++){
					JSONObject content = new JSONObject();
					content.put("type", records.get(j).getType());
					content.put("value", records.get(j).getContent());
					contents.add(content);
				}
				o.put("content", contents);
				commentsList.add(o);
			}
			
		}
		data.put("commentList", commentsList);
		data.put("hasMore", page.getPageNum()<page.getPages());
		data.put("size", pageSize);
		data.put("offset",pageNo);
		data.put("totalCount",page.getTotal());
		resultData.put("data", data);
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg","获取评论模块成功");
		return pakageJsonp(resultObj);
	}
	
	/**发布评论 **/
	@RequestMapping("/addComment")
	public String addComment(HttpServletRequest request) throws Exception{
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if(!parameterObj.containsKey("moduleId")||!parameterObj.containsKey("sid")){
			resultObj.put("errCode", "1000");
			return pakageJsonp(resultObj);
		}
		
		String moduleId = parameterObj.getString("moduleId");
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if(null==user){
			resultObj.put("errCode", "2000");//用户不存在
			resultObj.put("resultMsg","用户不存在，请检查sessionKey");
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
		
		CommentConfig config = commentConfigService.findByKey(moduleId);
		if(null!=config){
			String isAllowType = config.getIsAllowType();
				if(!"0".equals(isAllowType)){
					if(null!=user.getUserExt()&&user.getUserExt().getIsCertification().equals("1")){
					JSONArray isAllowList = JSONArray.parseArray(isAllowType);
					Boolean isAllowComment = isAllowList.contains(user.getUserExt().getRoleId());
					if(!isAllowComment){
						resultObj.put("errCode", "3002");//用户不存在
						resultObj.put("resultMsg","没有评论权限");
						return pakageJsonp(resultObj);
					}
				}
			}
		}else{
			resultObj.put("errCode", "3008");//
			resultObj.put("resultMsg", "模块不存在");
			return pakageJsonp(resultObj);
		}
		
		Date currentDate = new Date();
		if(currentDate.before(config.getStartTime())||currentDate.after(config.getEndTime())){
			resultObj.put("errCode", "3009");//用户不存在
			resultObj.put("resultMsg","评论时间已过或未到");
			return pakageJsonp(resultObj);
		}
		
		if(config.getIsOpen().equals("0")){
			resultObj.put("errCode", "3006");//用户不存在
			resultObj.put("resultMsg","尚未开启，请耐心等待");
			return pakageJsonp(resultObj);
		}
		JSONObject resultData = new JSONObject();
		String content = parameterObj.getString("content");
		if(StringUtils.isBlank(content)){
			
			resultObj.put("errCode", "3009");
			resultObj.put("resultMsg","发布评论成功");
			return pakageJsonp(resultObj);
		}
		
		JSONArray contentArray = JSONArray.parseArray(content);
		
		
		Comments comments = new Comments();
		comments.setCommentConfigId(moduleId);
		comments.setUserId(user.getId());
		comments.setCreateTime(new Date());
		comments.setHeadrImg(user.getWxHeadImg());
		comments.setNick(user.getNick());
		commentsService.save(comments);
		
		CommentRecord record = null;
		JSONObject currentContent = null;
		for(int i=0;i<contentArray.size();i++){
			currentContent = contentArray.getJSONObject(i);
			String type = currentContent.getString("type");
			String value = currentContent.getString("value");
			record = new CommentRecord();
			record.setContent(value);
			record.setType(type);
			record.setSortNum(i);
			record.setCommentId(comments.getId());
			
			commentRecordService.save(record);
		}
		
		
		resultData.put("commentId", comments.getId());
		resultData.put("icon", comments.getHeadrImg());
		resultData.put("nick", comments.getNick());
		resultData.put("createAt",  DateTools.convertDateToString(comments.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg","发布评论成功");
		return pakageJsonp(resultObj);
	}
	
	/**添加事件评论配置
	 * @throws Exception **/
	@RequestMapping("/addCommentConfig")
	public String addCommentConfig(HttpServletRequest request) throws Exception{
		
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
		//String configStr = parameterObj.getString("config");
		String eventId = parameterObj.getString("eventId");
		String moduleType = parameterObj.getString("moduleType");
		String moduleId = parameterObj.getString("moduleId");
		CommentConfig config = new CommentConfig();
		
		Calendar nowDate = Calendar.getInstance();
		config.setStartTime(nowDate.getTime());
		nowDate.set(Calendar.YEAR, nowDate.get(Calendar.YEAR)+1);
		config.setEndTime(nowDate.getTime());
		
		config.setEventId(eventId);
		config.setModel("1");
		config.setIsOpen("1");
		
		
		//取对应事件的权限作为模块的权限
		List<EventAllowAccess> accessList = eventAllowAccessService.findListByEvent(eventId);
		if(null==accessList||accessList.size()<=0){
			config.setIsAllowType("0");
		}else{
			JSONArray array = new JSONArray(); 
			for(EventAllowAccess aa:accessList){
				array.add(aa.getUserRoleId());
			}
			config.setIsAllowType(array.toJSONString());
		}
		
		
		if(StringUtils.isBlank(moduleId)){
		    commentConfigService.save(config);
		    moduleId =config.getId();
		}else{
			//更新
			config.setId(moduleId);
			commentConfigService.update(config);
		}
		EventModule em = new EventModule();
		em.setEventId(eventId);
		em.setModuleLabel(moduleType);
		eventModuleService.delete(em);
		
		em.setModuleId(config.getId());
		eventModuleService.save(em);
		
		JSONObject resultData = new JSONObject();
		resultData.put("moduleId", moduleId);
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg","评论模块配置成功");
		return pakageJsonp(resultObj);
		
	}
	
	
}
