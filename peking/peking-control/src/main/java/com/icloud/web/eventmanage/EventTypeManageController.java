package com.icloud.web.eventmanage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icloud.common.ResponseUtils;
import com.icloud.model.eventmanage.TeventType;
import com.icloud.service.eventmanage.EventTypeManageService;


@Controller
public class EventTypeManageController extends EventManageBaseController<TeventType>{

	@Autowired
	private EventTypeManageService eventTypeManageService;
	

	
	@RequestMapping("/eventmanage/eventtype_list")
	public String list(HttpServletRequest request,TeventType eventType) throws Exception{	  
	  List<TeventType> list = eventTypeManageService.findList(eventType);
	  request.setAttribute("eventType", eventType);
	  request.setAttribute("list", list);
	  return "eventmanage/eventtype_list";
	}
	
	@RequestMapping("/eventmanage/eventtype_getlist")
	public String getList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String typeName = request.getParameter("typeName");
		
		String pageNo = request.getParameter("pageNo");		
		JSONObject json = new JSONObject();
		TeventType eventType = new TeventType();
		if(StringUtils.isNotBlank(typeName)){
			eventType.setTypeName(typeName);
		}
		if(StringUtils.isNotBlank(pageNo)){
			eventType.setPageNo(Integer.parseInt(pageNo));
		}
		List<TeventType> list = eventTypeManageService.findList(eventType);
		json.put("pages", eventType.getPages());
		JSONArray array = new JSONArray();
		for(TeventType t:list){
			JSONObject sub = new JSONObject();
			sub.put("id",t.getId());
			sub.put("typeName", t.getTypeName());
			sub.put("typeMark", t.getTypeMark());
			if(t.getParentId().equals("0")){
				sub.put("parentId", "一级");}
			else if(t.getParentId().equals("1")){
				sub.put("parentId", "二级");}
			sub.put("sortNum", t.getSortNum());
			sub.put("typeIcon", t.getTypeIcon());
			
			array.add(sub);
		}
		json.put("list", array);
		ResponseUtils.renderJson(response, json.toJSONString());
		return null;
		
		
	}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/eventmanage/eventtype_to_input")
	public String toinput(String id, HttpServletRequest request) throws Exception{
		if(StringUtils.isNotBlank(id)){
			TeventType eventType = eventTypeManageService.findByKey(id);
			request.setAttribute("eventType", eventType);
		}else{
			request.setAttribute("eventType", null);
		}
				
		return "eventmanage/eventtype_input";
		
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/eventmanage/eventtype_input")
	public String input(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String id = request.getParameter("id");
		String typeName = request.getParameter("typeName");
		String typeMark = request.getParameter("typeMark");
		String parentId = request.getParameter("parentId");
		String sortNum = request.getParameter("sortNum");
		String typeIcon = request.getParameter("typeIcon");
		
		if(StringUtils.isNotBlank(id)){
			TeventType eventType = eventTypeManageService.findByKey(id);
			
			eventType.setTypeName(typeName);
			eventType.setTypeMark(typeMark);
			eventType.setParentId(parentId);
			eventType.setSortNum(Integer.parseInt(sortNum));
			eventType.setTypeIcon(typeIcon);
			eventTypeManageService.update(eventType);
			
			ResponseUtils.renderText(response, "0002");
			return null;
			
		}else{
			int count = eventTypeManageService.selectCountByName(typeName);
			if(count>0){
				ResponseUtils.renderText(response, "0001");
				return null;
			}
			
			int typeMarkcount = eventTypeManageService.selectCountByTypeMark(typeMark);
			if(typeMarkcount>0){
				ResponseUtils.renderText(response, "0003");
				return null;
			}		
			
			TeventType eventType = new TeventType();
			eventType.setTypeName(typeName);
			eventType.setTypeMark(typeMark);
			eventType.setParentId(parentId);
			eventType.setSortNum(Integer.parseInt(sortNum));
			eventType.setTypeIcon(typeIcon);
			
			eventTypeManageService.save(eventType);
		}
		
		ResponseUtils.renderText(response, "0000");
		return null;
	}
	
	@RequestMapping("/eventmanage/eventtype_del")
	public String del(String id, HttpServletResponse response) throws Exception {
	
		
		int count = eventTypeManageService.countByParent(id);
		if(count>0){
			ResponseUtils.renderText(response, "0002");
			return null;
		}
		
		TeventType eventType = eventTypeManageService.findByKey(id);
		if(null!=eventType){
			
		eventTypeManageService.delete(id);
		ResponseUtils.renderText(response, "0000");
		return null;
				
		}
		ResponseUtils.renderText(response, "0001");
		return null;
	}

}
