package com.icloud.web.eventmanage;

import java.util.Iterator;
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
import com.icloud.model.eventmanage.EventAllowAccess;
import com.icloud.model.eventmanage.EventFollow;
import com.icloud.model.eventmanage.EventInfo;
import com.icloud.model.eventmanage.EventStarRecord;
import com.icloud.model.staffmanage.Tuserrole;
import com.icloud.service.eventmanage.EventAllowAccessService;
import com.icloud.service.eventmanage.EventFollowService;
import com.icloud.service.eventmanage.EventInfoService;
import com.icloud.service.eventmanage.EventStarRecordService;
import com.icloud.service.staffmanage.StaffRoleManageService;
import com.icloud.web.BaseController;

@Controller
public class EventInfoController extends BaseController<EventInfo>{
	@Autowired
	private EventInfoService eventInfoService;
    @Autowired
	private EventStarRecordService eventStarRecordService;
    @Autowired
    private EventFollowService eventFollowService;
    @Autowired
    private EventAllowAccessService eventAllowAccessService;
    
    @Autowired
    private StaffRoleManageService staffRoleManageService;	
    

	@RequestMapping("/admin/event_list")
	public String list(HttpServletRequest request, EventInfo t)
			throws Exception {
	    PageInfo<EventInfo> page =	eventInfoService.findByPage(0, 10, new EventInfo());
	
	    request.setAttribute("page", page);
		return "eventmanage/event_list";
	}

    @RequestMapping("/admin/event_getlist")
	public String getList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String eventName = request.getParameter("eventName");
		String eventStatus = request.getParameter("eventStatus");
		String pageNo = request.getParameter("pageNo");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String showStatus = request.getParameter("showStatus");
		
		EventInfo event = new EventInfo();
		if(StringUtils.isNotBlank(eventName)){
			event.setEventName(eventName);
		}
		if(StringUtils.isNotBlank(eventStatus)){
			event.setEventStatus(eventStatus);
		}
		if(StringUtils.isNotBlank(showStatus)){
			event.setIsPublic(showStatus);
		}
		
		
		if(StringUtils.isNotBlank(endTime)&&StringUtils.isNotBlank(startTime)){
			event.setStartDate(DateTools.str2Date(startTime, "yyyy-MM-dd HH:mm:ss"));
			event.setEndDate(DateTools.str2Date(endTime, "yyyy-MM-dd HH:mm:ss"));
		}
		
		
		PageInfo<EventInfo> page = eventInfoService.findByPage(StringUtils.isNotBlank(pageNo)?Integer.parseInt(pageNo):1, 10,event);
		List<EventInfo> list = page.getList();
		
		JSONArray array = new JSONArray();
		for(int i=0;null!=list&&i<list.size();i++){
			JSONObject obj = new JSONObject();
			obj.put("id", list.get(i).getId());
			obj.put("eventName", list.get(i).getEventName());
			obj.put("createDate",DateTools.convertDateToString(list.get(i).getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
			obj.put("startDate",DateTools.convertDateToString(list.get(i).getStartDate(), "yyyy-MM-dd HH:mm:ss"));
			obj.put("endDate",DateTools.convertDateToString(list.get(i).getEndDate(), "yyyy-MM-dd HH:mm:ss"));
			String status = "待发布";
			eventStatus = list.get(i).getEventStatus();
			if("1".equals(eventStatus)){
				status = "已发布待审核";
			}
			if("2".equals(eventStatus)){
				status = "已发布";
			}
			if("3".equals(eventStatus)){
				status = "审核未通过";
			}
			if("4".equals(eventStatus)){
				status = "已完结";
			}
			obj.put("status",status);
			obj.put("eventStatus",eventStatus);
			array.add(obj);
		}
		
		ResponseUtils.renderJson(response,array.toJSONString());
		return null;
	}

	@RequestMapping("/admin/event_to_input")
	public String toinput(String id, HttpServletRequest request)
			throws Exception {
        EventInfo event = eventInfoService.findByKey(id);
        request.setAttribute("event", event);
        List<EventAllowAccess> list = eventAllowAccessService.findListByEvent(event.getId());
        StringBuffer roles = new StringBuffer();
        if(null!=list){
        	String[] ids = new String[list.size()];
	        for(int i=0;i<list.size();i++){
	        	ids[i] = list.get(i).getUserRoleId();
		    }
	        List<Tuserrole> rolelist = staffRoleManageService.selectRoleList(ids);
            Iterator<Tuserrole> it=  rolelist.iterator();
            while(it.hasNext()){
            	roles.append(it.next().getRoleName()+",");
            }
            
        }
        request.setAttribute("role", roles.length()==0?"不限":roles.toString());
        
		return "eventmanage/event_view";
	}

	@Override
	public String input(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	public String del(String id, HttpServletResponse response)
			throws Exception {
		return null;
	}
	
	/**事件审核 **/
	@RequestMapping("/admin/event_audit")
	public String audit(String id,String type,HttpServletResponse response) throws Exception{
		 EventInfo event = eventInfoService.findByKey(id);
		 if(null!=event){
			 if(event.getEventStatus().equals("1")){
				 event.setEventStatus(type);
				 eventInfoService.update(event);
				 ResponseUtils.renderText(response, "0000");
			 }else{
				 ResponseUtils.renderText(response, "0001");
			 }
		 }else{
			 ResponseUtils.renderText(response, "0002");
		 }
		 
		 return null;
	}
	
	/** 打开点赞记录**/
	@RequestMapping("/admin/star_record")
	public String openStarRecord(){
		return "/eventmanage/star_record";
	}
	
	/**点赞记录**/
	@RequestMapping("/admin/event_star")
	public String starRecord(HttpServletRequest request,HttpServletResponse response){
		String eventId = request.getParameter("eventId");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		JSONObject resultObj = new JSONObject();
		if(StringUtils.isBlank(eventId)){
			resultObj.put("errCode", "0001");
			resultObj.put("resultMsg", "事件ID为空");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		EventStarRecord es = new EventStarRecord();
		es.setEventId(eventId);
		
		PageInfo<EventStarRecord> page = eventStarRecordService.findList(StringUtils.isBlank(pageNo)?1:Integer.parseInt(pageNo), StringUtils.isBlank(pageSize)?10:Integer.parseInt(pageSize), es);
		JSONArray alist = new JSONArray();
		List<EventStarRecord> list = page.getList();
		for(int i=0;null!=list&&i<list.size();i++){
			JSONObject e = new JSONObject();
			e.put("nick", list.get(i).getNick());
			e.put("headerImg", list.get(i).getHeaderImg());
			e.put("createDate", DateTools.convertDateToString(list.get(i).getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			alist.add(e);
			
			
		}
		
		resultObj.put("list", alist);
		resultObj.put("pages", page.getPages());
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "点赞记录获取成功");
		ResponseUtils.renderJson(response, resultObj.toJSONString());
		return null;
	}

	@RequestMapping("/admin/event_follow")
	public String openFollowRecord(){
		return "/eventmanage/follow";
	}
	
	@RequestMapping("/admin/event_follow_list")
	public String followRecord(HttpServletRequest request,HttpServletResponse response){
		String eventId = request.getParameter("eventId");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		JSONObject resultObj = new JSONObject();
		if(StringUtils.isBlank(eventId)){
			resultObj.put("errCode", "0001");
			resultObj.put("resultMsg", "事件ID为空");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		EventFollow es = new EventFollow();
		es.setEventId(eventId);
	    PageInfo<EventFollow> page = eventFollowService.findForList(StringUtils.isBlank(pageNo)?1:Integer.parseInt(pageNo), StringUtils.isBlank(pageSize)?10:Integer.parseInt(pageSize), es);
	    List<EventFollow>  list = page.getList();
		JSONArray alist = new JSONArray();
	    for(int i=0;null!=list&&i<list.size();i++){
			JSONObject e = new JSONObject();
			e.put("nick", list.get(i).getNick());
			e.put("headerImg", list.get(i).getHeaderImg());
			e.put("createDate", DateTools.convertDateToString(list.get(i).getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			alist.add(e);
		}
	    
	    resultObj.put("list", alist);
		resultObj.put("pages", page.getPages());
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "点赞记录获取成功");
		ResponseUtils.renderJson(response, resultObj.toJSONString());
		return null;
	}

}
