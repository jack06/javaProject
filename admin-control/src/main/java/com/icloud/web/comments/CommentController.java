package com.icloud.web.comments;

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
import com.icloud.model.comments.CommentConfig;
import com.icloud.model.comments.CommentRecord;
import com.icloud.model.comments.Comments;
import com.icloud.model.staffmanage.Tuserrole;
import com.icloud.service.comments.CommentConfigService;
import com.icloud.service.comments.CommentsService;
import com.icloud.service.staffmanage.StaffRoleManageService;

@Controller
public class CommentController {
	@Autowired
	private CommentConfigService commentConfigService;
	@Autowired
    private StaffRoleManageService staffRoleManageService;	
	@Autowired
	private CommentsService commentsService;
	
	@RequestMapping("/admin/toComment")
	public String toComment(){
		return "/comments/comments";
	}
	
	@RequestMapping("/admin/comment_config")
	public String getCommentConfig(HttpServletRequest request,HttpServletResponse response){
		String eventId = request.getParameter("eventId");
		JSONObject resultObj = new JSONObject();
		if(StringUtils.isBlank(eventId)){
			resultObj.put("errCode","0001");
			resultObj.put("resultMsg","事件ID为空");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
			
		}
		List<CommentConfig> configs = commentConfigService.selectByEvent(eventId);
		if(null!=configs&&configs.size()>0){
			CommentConfig  config = configs.get(0);
			JSONObject configJson = new JSONObject();
			configJson.put("startTime", DateTools.convertDateToString(config.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
			configJson.put("endTime", DateTools.convertDateToString(config.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
			configJson.put("status", config.getIsOpen().equals("0")?"关闭":"开启");
			configJson.put("id", config.getId());
			resultObj.put("config", configJson);
			if(!config.getIsAllowType().equals("0")){
			    JSONArray array =JSONArray.parseArray(config.getIsAllowType());
			    String[] ids = new String[array.size()];
			    for(int i=0;i<array.size();i++){
			    	ids[i] = array.getString(i);
			    }
			    List<Tuserrole> rolelist = staffRoleManageService.selectRoleList(ids);
			    resultObj.put("roles", rolelist);
			}else{
				resultObj.put("roles","0");
			}
			Comments comments = new Comments();
			
			comments.setCommentConfigId(config.getId());
		    PageInfo<Comments> page =	commentsService.findForList(1, 10, comments);
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
			data.put("pages",page.getPages());
			resultObj.put("data", data);
		}else{
			resultObj.put("errCode", "0001");
			resultObj.put("resultMsg","获取评论模块成功");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg","获取评论模块成功");
		ResponseUtils.renderJson(response, resultObj.toJSONString());
		return null;
		
	}
	@RequestMapping("/admin/comments_list")
	public String commentsList(HttpServletRequest request,HttpServletResponse response){
		String pageNo = request.getParameter("pageNo");
		String moduleId = request.getParameter("moduleId");
		JSONObject resultObj = new JSONObject();
		if(StringUtils.isBlank(moduleId)){
			resultObj.put("errCode","0001");
			resultObj.put("resultMsg","模块件ID为空");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		Comments comment = new Comments();
		comment.setCommentConfigId(moduleId);
		
		
		 PageInfo<Comments> page = commentsService.findForList(StringUtils.isNotBlank(pageNo)?Integer.parseInt(pageNo):1, 10, comment);
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
			resultObj.put("data", data);
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg","获取评论模块成功");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
			
	}

	
}
