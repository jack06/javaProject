package com.icloud.web.event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.DateTools;
import com.icloud.dto.vo.EventIamge;
import com.icloud.model.event.EventAllowAccess;
import com.icloud.model.event.EventDetails;
import com.icloud.model.event.EventDetailsConfig;
import com.icloud.model.event.EventFollow;
import com.icloud.model.event.EventInfo;
import com.icloud.model.event.EventModule;
import com.icloud.model.event.EventStarRecord;
import com.icloud.model.event.EventType;
import com.icloud.model.user.User;
import com.icloud.service.event.EventAllowAccessService;
import com.icloud.service.event.EventDetailsConfigService;
import com.icloud.service.event.EventDetailsService;
import com.icloud.service.event.EventFollowService;
import com.icloud.service.event.EventInfoService;
import com.icloud.service.event.EventModuleService;
import com.icloud.service.event.EventStarRecordService;
import com.icloud.service.event.EventTypeService;
import com.icloud.service.user.UserService;
import com.icloud.web.BaseController;

@RestController
public class EventInfoController extends BaseController {
	public final static Logger logger = LoggerFactory.getLogger(EventInfoController.class);
	@Autowired
	private EventInfoService eventInfoService;

	@Autowired
	private UserService userService;
	@Autowired
	private EventFollowService eventFollowService;
	@Autowired
	private EventModuleService eventModuleService;
	@Autowired
	private EventStarRecordService eventStarRecordService;
	@Autowired
	private EventDetailsService eventDetailsService;
	@Autowired
	private EventTypeService eventTypeService;
	@Autowired
	private EventAllowAccessService eventAllowAccessService;
	@Autowired
	private EventDetailsConfigService eventDetailsConfigService;

	/**
	 * 事件列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/getEventsListByMonth")
	public String getEventsListByMonth(HttpServletRequest request) throws Exception {

		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("sid") || !parameterObj.containsKey("year")
				|| !parameterObj.containsKey("month")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失");
			return pakageJsonp(resultObj);
		}
		User user = userService.getUserFromSession(parameterObj.getString("sid"));
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
			return pakageJsonp(resultObj);
		}
		//认证中或者已经认证成功直接可以进去首页
		/**if (null == user.getUserExt()) {
			resultObj.put("errCode", "0013");// 用户不存在
			resultObj.put("resultMsg", "用户尚未认证");
			return pakageJsonp(resultObj);
		}
		if (user.getUserExt().equals("2")) {
			resultObj.put("errCode", "0013");// 用户不存在
			resultObj.put("resultMsg", "用户认证失败");
			return pakageJsonp(resultObj);
		}**/
        
		String year = parameterObj.getString("year");
		String month = parameterObj.getString("month");
		String offset = parameterObj.getString("offset");
		String size = parameterObj.getString("size");
		String eventType = parameterObj.getString("eventType");
		String publisherType = parameterObj.getString("publisherType");

		Calendar baseDate = Calendar.getInstance();
		baseDate.set(Calendar.YEAR, Integer.parseInt(year));
		baseDate.set(Calendar.MONTH, Integer.parseInt(month) - 1);

		int firstDay = baseDate.getActualMinimum(Calendar.DATE);
		int lastDay = baseDate.getActualMaximum(Calendar.DATE);

		baseDate.set(Calendar.DATE, firstDay);
		baseDate.set(Calendar.HOUR_OF_DAY, 0);
		baseDate.set(Calendar.MINUTE, 0);
		baseDate.set(Calendar.SECOND, 0);
		baseDate.set(Calendar.MILLISECOND, 0);
		Date startDate = baseDate.getTime();

		baseDate.set(Calendar.DATE, lastDay);
		baseDate.set(Calendar.HOUR_OF_DAY, 23);
		baseDate.set(Calendar.MINUTE, 59);
		baseDate.set(Calendar.SECOND, 59);
		baseDate.set(Calendar.MILLISECOND, 0);
		Date endDate = baseDate.getTime();

		EventInfo event = new EventInfo();
		event.setStartDate(startDate);
		event.setEndDate(endDate);
		if (StringUtils.isNotBlank(eventType)) {
			event.setTypeId(eventType);
		}
		if (StringUtils.isNotBlank(publisherType)) {
			event.setPublisherType(publisherType);
		}
		
		event.setEventStatus("2");
		event.setIsPublic("1");
		
		int pageNo = StringUtils.isNotBlank(offset) ? Integer.parseInt(offset) : 1;
		int pageSize = StringUtils.isNotBlank(size) ? Integer.parseInt(size) : 20;
		PageInfo<EventInfo> page = eventInfoService.findByPage(pageNo, pageSize, event);
		List<EventInfo> list = page.getList();
		JSONObject resultData = new JSONObject();
		JSONArray eventArray = new JSONArray();
		JSONObject ev = null;
		JSONArray days = new JSONArray();
		for (int i = 0; null != list && i < list.size(); i++) {
			ev = new JSONObject();
			event = list.get(i);
			ev.put("eventId", event.getId());
			ev.put("name", event.getEventName());
			ev.put("year", year);
			ev.put("month", event.getStartDate().getMonth() + 1);
			ev.put("date", event.getStartDate().getDate());
			ev.put("day", event.getStartDate().getDay());
			ev.put("createTime", DateTools.convertDateToString(event.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			ev.put("startTime", DateTools.convertDateToString(event.getStartDate(), "HH:mm"));
			ev.put("address", event.getAddress());
			EventFollow ef = new EventFollow();
			ef.setEventId(event.getId());
			ef.setUserId(user.getId());
			int count = eventFollowService.findCount(ef);
			ev.put("hasFollow", count > 0 ? true : false);
			ef.setUserId(null);
			int totalCount = eventFollowService.findCount(ef);
			JSONObject followInfo = new JSONObject();
			followInfo.put("total", totalCount);
			PageInfo<EventFollow> efList = eventFollowService.findByPage(1, 6, ef);
			JSONArray avatars = new JSONArray();
			for (int j = 0; null != efList.getList() && j < efList.getList().size(); j++) {
				avatars.add(efList.getList().get(j).getHeaderImg());
			}
			followInfo.put("avatars", avatars);
			ev.put("followInfo", followInfo);

			// 记录下有事件的日期
			if (!days.contains(DateTools.convertDateToString(event.getStartDate(), "dd"))) {
				days.add(DateTools.convertDateToString(event.getStartDate(), "dd"));
			}
			// end
			eventArray.add(ev);
		}
		resultData.put("list", eventArray);
		resultData.put("eventDays", days);
		resultData.put("count", page.getTotal());
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "列表获取成功");
		return pakageJsonp(resultObj);

	}

	/** 获取事件基本信息 **/
	@RequestMapping("/getEventBase")
	public String getEventBase(HttpServletRequest request) throws Exception {

		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();

		if (!parameterObj.containsKey("sid") || !parameterObj.containsKey("eventId")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失");
			return pakageJsonp(resultObj);
		}

		User user = userService.getUserFromSession(parameterObj.getString("sid"));
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "用户不存在，请检查sessionKey");
			return pakageJsonp(resultObj);
		}

		JSONObject resultData = new JSONObject();
		String eventId = parameterObj.getString("eventId");
		EventInfo event = eventInfoService.findByKey(eventId);

		//判断权限 事见发布者刚好为查看者 不用判断权限
		/**if(!user.getId().equals(event.getEventOriginator())){
			if (null != event.getAccessList() && event.getAccessList().size() > 0) {
				if (user.getUserExt() == null) {
					resultObj.put("errCode", "0013");// 用户不存在
					resultObj.put("resultMsg", "用户尚未认证");
					return pakageJsonp(resultObj);
				}
				if (user.getUserExt().getIsCertification().equals("2")) {
					resultObj.put("errCode", "0013");// 用户不存在
					resultObj.put("resultMsg", "用户认证失败");
					return pakageJsonp(resultObj);
				}
				UserExt ext = user.getUserExt();
				boolean allowView = false;
				  for (int i = 0; i < event.getAccessList().size(); i++) {
					String userRoleId = event.getAccessList().get(i).getUserRoleId();
					if (ext.getRoleId().equals(userRoleId)) {
						allowView = true;
					}
				}
				if (!allowView) {
					resultObj.put("errCode", "3004");// 用户不存在
					resultObj.put("resultMsg", "没有查看事件的权限");
					return pakageJsonp(resultObj);
				}
			}
		}**/
		resultData.put("poster", event.getIndexUrl());
		resultData.put("name", event.getEventName());
		resultData.put("pictureUrls", event.getPictureUrls());
		resultData.put("startTime", DateTools.convertDateToString(event.getStartDate(), "yyyy-MM-dd HH:mm:ss"));
		resultData.put("endTime", DateTools.convertDateToString(event.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
		resultData.put("createTime", DateTools.convertDateToString(event.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
		resultData.put("address", event.getAddress());
		resultData.put("latitude", event.getLatitude());
		resultData.put("longitude", event.getLongitude());
        resultData.put("typeId",event.getTypeId());
        resultData.put("status", event.getEventStatus());
        
        List<EventAllowAccess> allowList = eventAllowAccessService.findListByEvent(event.getId());
        JSONArray allowRoleIds = new JSONArray();
        for(int i=0;null!=allowList&&allowList.size()>i;i++){
        	allowRoleIds.add(allowList.get(i).getUserRoleId());
        }
        
        resultData.put("allowRoleIds", allowRoleIds);
        
		EventFollow ef = new EventFollow();
		ef.setEventId(event.getId());
		ef.setUserId(user.getId());
		int count = eventFollowService.findCount(ef);
		resultData.put("isFollow", count > 0 ? true : false);
		// 加入点赞事件 默认数据
		EventStarRecord star = new EventStarRecord();
		star.setEventId(eventId);
		star.setUserId(user.getId());
		
		EventStarRecord userstar = eventStarRecordService.selectByUser(star);
		resultData.put("isStar", null != userstar ? true : false);

		int starCount = eventStarRecordService.findCountByEvent(eventId);
		resultData.put("starCount", starCount);

		List<EventModule> moduleList = eventModuleService.findForList(event.getId());
		JSONArray modules = new JSONArray();
		EventModule em = null;
		JSONObject obj = null;
		for (int i = 0; null != moduleList && i < moduleList.size(); i++) {
			obj = new JSONObject();
			em = moduleList.get(i);
			obj.put("moduleType", em.getModuleLabel());
			obj.put("moduleId", em.getModuleId());
			modules.add(obj);
		}
		resultData.put("modules", modules);

		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取成功");
		resultObj.put("resultData", resultData);
		return pakageJsonp(resultObj);
	}

	/** 获取事件详情 **/
	@RequestMapping("/getDescriptionModule")
	public String getDescriptionModule(HttpServletRequest request) {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("moduleId")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失");
			return pakageJsonp(resultObj);
		}

		JSONObject resultData = new JSONObject();
		String moduleId = parameterObj.getString("moduleId");
		EventDetails eventDetails = new EventDetails();
		eventDetails.setModuleId(moduleId);
		List<EventDetails> list = eventDetailsService.findForList(eventDetails);

		JSONArray paragraphs = new JSONArray();
		JSONObject paragraph = null;
		JSONObject data = new JSONObject();
		for (int i = 0; null != list && i < list.size(); i++) {
			eventDetails = list.get(i);
			paragraph = new JSONObject();
			paragraph.put("type", eventDetails.getType());
			paragraph.put("value", eventDetails.getContent());
			paragraphs.add(paragraph);
		}
		data.put("paragraphs", paragraphs);

		resultData.put("config", new JSONObject());// 详情的配置先默认为空
		resultData.put("data", data);

		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取详情模块成功");
		return pakageJsonp(resultObj);
	}

	/** 关注事件 **/
	@RequestMapping("/followEvent")
	public String followEvent(HttpServletRequest request) throws Exception {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("eventId") || !parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失");
			return pakageJsonp(resultObj);
		}
		String eventId = parameterObj.getString("eventId");
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "当前用户不存在");
			return pakageJsonp(resultObj);
		}
		JSONObject resultData = new JSONObject();
		EventFollow follow = new EventFollow();
		follow.setEventId(eventId);
		follow.setUserId(user.getId());
		int count = eventFollowService.findCount(follow);
		if (count <= 0) {
			follow.setHeaderImg(user.getWxHeadImg());
			follow.setNick(user.getNick());
			follow.setCreateDate(new Date());
			eventFollowService.save(follow);
		}
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "事件关注成功");
		resultObj.put("resultData", resultData);
		return pakageJsonp(resultObj);

	}

	/** 取消关注事件 **/
	@RequestMapping("/unfollowEvent")
	public String unfollowEvent(HttpServletRequest request) {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("eventId") || !parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			return pakageJsonp(resultObj);
		}
		String eventId = parameterObj.getString("eventId");
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "当前用户不存在");
			return pakageJsonp(resultObj);
		}
		JSONObject resultData = new JSONObject();
		EventFollow follow = new EventFollow();
		follow.setEventId(eventId);
		follow.setUserId(user.getId());
		eventFollowService.deleteByObject(follow);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "取消关注成功");
		resultObj.put("resultData", resultData);
		return pakageJsonp(resultObj);
	}

	/** 点赞 **/
	@RequestMapping("/addStar")
	public String addStar(HttpServletRequest request) {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("eventId") || !parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			return pakageJsonp(resultObj);
		}
		String eventId = parameterObj.getString("eventId");
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "当前用户不存在");
			return pakageJsonp(resultObj);
		}
		JSONObject resultData = new JSONObject();
		EventStarRecord star = new EventStarRecord();
		star.setEventId(eventId);
		star.setUserId(user.getId());
		EventStarRecord userstar = eventStarRecordService.selectByUser(star);
		if (null == userstar) {
			userstar = new EventStarRecord();
			userstar.setCreateDate(new Date());
			userstar.setEventId(eventId);
			userstar.setHeaderImg(user.getWxHeadImg());
			userstar.setUserId(user.getId());
			userstar.setNick(user.getNick());
			eventStarRecordService.save(userstar);
		}
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "点赞成功");
		int starCount = eventStarRecordService.findCountByEvent(eventId);
		resultData.put("starCount", starCount);
		resultObj.put("resultData", resultData);
		return pakageJsonp(resultObj);
	}

	/** 我的发布列表 **/
	@RequestMapping("/myPublished")
	public String myPublished(HttpServletRequest request) throws Exception {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失sid");
			return pakageJsonp(resultObj);
		}
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "当前用户不存在");
			return pakageJsonp(resultObj);
		}
		
		String offset = parameterObj.getString("offset");
		String size = parameterObj.getString("size");
		int pageNo = StringUtils.isNotBlank(offset) ? Integer.parseInt(offset) : 0;
		int pageSize = StringUtils.isNotBlank(size) ? Integer.parseInt(size) : 20;
		EventInfo event = new EventInfo();
		event.setEventOriginator(user.getId());

		PageInfo<EventInfo> page = eventInfoService.findMyList(pageNo, pageSize, event);
		List<EventInfo> list = page.getList();

		JSONObject resultData = new JSONObject();
		JSONArray jsonList = new JSONArray();
		JSONObject ev = null;
		for (int i = 0; null != list && i < list.size(); i++) {
			ev = new JSONObject();
			event = list.get(i);
			ev.put("eventId", event.getId());
			ev.put("name", event.getEventName());
			ev.put("pictureUrl", event.getFirstImg());
			ev.put("address", event.getAddress());
			ev.put("year", DateTools.convertDateToString(event.getStartDate(), "yyyy"));
			ev.put("month", DateTools.convertDateToString(event.getStartDate(), "MM"));
			ev.put("date", DateTools.convertDateToString(event.getStartDate(), "dd"));
			ev.put("startTime", DateTools.convertDateToString(event.getStartDate(), "HH:ss"));
			ev.put("status", event.getEventStatus());
			ev.put("toggle", event.getIsPublic());
			jsonList.add(ev);
		}
		resultData.put("list", jsonList);
		resultData.put("offset", pageNo);
		resultData.put("size", pageSize);
		resultData.put("hasMore",  (page.getPageNum()<page.getPages()));
		resultData.put("count", page.getTotal());
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取我的发布成功");
		return pakageJsonp(resultObj);
	}

	/** 我的关注 **/
	@RequestMapping("/myFollows")
	public String myFollows(HttpServletRequest request) throws Exception {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失sid");
			return pakageJsonp(resultObj);
		}
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "当前用户不存在");
			return pakageJsonp(resultObj);
		}
		
		String offset = parameterObj.getString("offset");
		String size = parameterObj.getString("size");
		int pageNo = StringUtils.isNotBlank(offset) ? Integer.parseInt(offset) : 0;
		int pageSize = StringUtils.isNotBlank(size) ? Integer.parseInt(size) : 20;
		EventInfo event = new EventInfo();
		event.setEventOriginator(user.getId());

		PageInfo<EventInfo> page = eventInfoService.findUserFollowList(pageNo, pageSize, event);
		List<EventInfo> list = page.getList();

		JSONObject resultData = new JSONObject();
		JSONArray jsonList = new JSONArray();
		JSONObject ev = null;
		for (int i = 0; null != list && i < list.size(); i++) {
			ev = new JSONObject();
			event = list.get(i);
			ev.put("eventId", event.getId());
			ev.put("name", event.getEventName());
			ev.put("pictureUrl", event.getFirstImg());
			ev.put("address", event.getAddress());
			ev.put("year", DateTools.convertDateToString(event.getStartDate(), "yyyy"));
			ev.put("month", DateTools.convertDateToString(event.getStartDate(), "MM"));
			ev.put("date", DateTools.convertDateToString(event.getStartDate(), "dd"));
			ev.put("startTime", DateTools.convertDateToString(event.getStartDate(), "HH:ss"));
			ev.put("status", event.getEventStatus());
			jsonList.add(ev);
		}
		resultData.put("list", jsonList);
		resultData.put("offset", pageNo);
		resultData.put("size", pageSize);
		resultData.put("hasMore", (page.getPageNum()<page.getPages()));
		resultData.put("count", page.getTotal());
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取我的关注成功");
		return pakageJsonp(resultObj);
	}

	/** 获取事件类型列表 **/
	@RequestMapping("/getEventTypeList")
	public String getEventTypeList(HttpServletRequest request) {

		JSONObject resultObj = new JSONObject();
		JSONObject resultDate = new JSONObject();

		List<EventType> list = eventTypeService.findAll();
		JSONArray jsonList = new JSONArray();
		JSONObject role = null;
		for (int i = 0; null != list && i < list.size(); i++) {
			role = new JSONObject();
			role.put("typeId", list.get(i).getId());
			role.put("typeName", list.get(i).getTypeName());
			role.put("typeIcon", list.get(i).getTypeIcon());
			jsonList.add(role);
		}
		resultDate.put("list", jsonList);
		resultObj.put("resultData", resultDate);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取成功");
		return pakageJsonp(resultObj);
	}

	/**
	 * 添加事件的基本信息
	 * 
	 * @throws Exception
	 **/
	@RequestMapping("/addEventBase")
	public String addEventBase(HttpServletRequest request) throws Exception {
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("sid")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失sid");
			return pakageJsonp(resultObj);
		}
		String sessionKey = parameterObj.getString("sid");
		User user = userService.getUserFromSession(sessionKey);
		if (null == user) {
			resultObj.put("errCode", "2000");// 用户不存在
			resultObj.put("resultMsg", "用户不存在请检查sid");
			return pakageJsonp(resultObj);
		}
		if (null == user.getUserExt()||user.getUserExt().getIsCertification().equals("2")) {
			resultObj.put("errCode", "0013");
			resultObj.put("resultMsg", "当前用户未认证,或身份认证失败");
			return pakageJsonp(resultObj);
		}
		if (user.getUserExt().getIsCertification().equals("0")) {
			resultObj.put("errCode", "0020");
			resultObj.put("resultMsg", "当前用户身份审核中");
			return pakageJsonp(resultObj);
		}

		String eventData = parameterObj.getString("data");
		JSONObject data = JSONObject.parseObject(eventData);
		String eventName = data.getString("eventName");
		if (StringUtils.isBlank(eventName)) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失eventName");
			return pakageJsonp(resultObj);
		}
		String eventpics = data.getString("eventPics");
		if (StringUtils.isBlank(eventpics)) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失eventpics");
			return pakageJsonp(resultObj);
		}
		String startTime = data.getString("startTime");
		if (StringUtils.isBlank(startTime)) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失startTime");
			return pakageJsonp(resultObj);
		}
		String endTime = data.getString("endTime");
		if (StringUtils.isBlank(endTime)) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失endTime");
			return pakageJsonp(resultObj);
		}
		String createTime = data.getString("createTime");
		/*if (StringUtils.isBlank(createTime)) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失createTime");
			return pakageJsonp(resultObj);
		}*/
		String typeId = data.getString("typeId");
		if (StringUtils.isBlank(typeId)) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失typeId");
			return pakageJsonp(resultObj);
		}
		String allowViewId = data.getString("allowViewId");
		if (StringUtils.isBlank(allowViewId)) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失allowViewId");
			return pakageJsonp(resultObj);
		}
		String address = data.getString("address");
		if (StringUtils.isBlank(address)) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失address");
			return pakageJsonp(resultObj);
		}
		String status = data.getString("isPublish");
		if(StringUtils.isBlank(status)){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失isPublish");
			return pakageJsonp(resultObj);
		}
		String latitude = data.getString("latitude");
		String longitude = data.getString("longitude");
		if(StringUtils.isBlank(latitude)||StringUtils.isBlank(longitude)){
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "缺少经纬度");
			return pakageJsonp(resultObj);
		}
		String poster = data.getString("poster");		
        EventInfo event = new EventInfo();
        event.setAddress(address);
		event.setLatitude(latitude);
		event.setLongitude(longitude);
		/** 根据用户角色判断发布的事件是否需要审批 **/
		if (user.getUserExt().getUserRole().getIsNeedVerify().equals("1")&&"1".equals(status)) {
			if("1".equals(status)){
			     event.setEventStatus("2");// 无需审批
			 }else{
				 event.setEventStatus(status);// 无需审批
			 }
		} else {
			event.setEventStatus(status);// 需审批
		}
		event.setEventOriginator(user.getId());
		event.setPublisherType(user.getUserExt().getRoleId());
		event.setEventName(eventName);
		event.setTypeId(typeId);
		event.setEndDate(DateTools.str2Date(endTime, "yyyy-MM-dd HH:mm:ss"));
		event.setStartDate(DateTools.str2Date(startTime, "yyyy-MM-dd HH:mm:ss"));
		event.setCreateDate(StringUtils.isNotBlank(createTime)?DateTools.str2Date(createTime, "yyyy-MM-dd HH:mm:ss"):new Date());
		event.setParentId("0");
		event.setIsPublic("1");
		/** 设置图片 **/
		JSONArray pics = JSONArray.parseArray(eventpics);
		String pic = null;
		EventIamge img = null;
		List<EventIamge> list = new ArrayList<EventIamge>();
		for (int i = 0; i < pics.size(); i++) {
			pic = pics.getString(i);
			img = new EventIamge();
			img.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			img.setUrl(pic);
		    img.setIsIndex(false);
			list.add(img);
		}
		
		if(StringUtils.isNotBlank(poster)){
			img = new EventIamge();
			img.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			img.setUrl(poster);
		    img.setIsIndex(true);
			list.add(img);
		}
		event.PictureUrls(list);
		String eventId = parameterObj.getString("eventId");
		if (StringUtils.isBlank(eventId)) {
			eventInfoService.save(event);
		} else {
			event.setId(eventId);
			eventInfoService.update(event);
		}
		/** 设置权限 **/
		JSONArray allowViewIds = JSONArray.parseArray(allowViewId);
		EventAllowAccess eaa = null;
		List<EventAllowAccess> accList = new ArrayList<EventAllowAccess>();
		for (int i = 0; i < allowViewIds.size(); i++) {
			eaa = new EventAllowAccess();
			eaa.setUserRoleId(allowViewIds.getString(i));
			eaa.setEventId(event.getId());
			accList.add(eaa);
		}
		// 先删除 后添加
		eventAllowAccessService.deleteByEvent(event.getId());
		eventAllowAccessService.bathSave(accList);

		JSONObject resultData = new JSONObject();
		resultData.put("eventId", event.getId());
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "事件保存成功");
		return pakageJsonp(resultObj);

	}

	/** 添加事件详情 **/
	@RequestMapping("/addEventdetailed")
	public String addEventdetailed(HttpServletRequest request){
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("eventId")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失eventId");
			return pakageJsonp(resultObj);
		}
		if (!parameterObj.containsKey("moduleType")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失moduleType");
			return pakageJsonp(resultObj);
		}
		String eventId = parameterObj.getString("eventId");
		String moduleType = parameterObj.getString("moduleType");
		String data = parameterObj.getString("data");
		if(StringUtils.isBlank(data)){
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "添加成功");
			return pakageJsonp(resultObj);
		}
		
		String moduleId = parameterObj.getString("moduleId");
		JSONObject datad = JSONObject.parseObject(data);
		JSONArray paragraphs = datad.getJSONArray("paragraphs");
		
		if(StringUtils.isBlank(moduleId)){
		
		/**设置配置 详情只有默认配置 **/
		
		EventDetailsConfig config = new EventDetailsConfig();
		config.setEventId(eventId);
		eventDetailsConfigService.save(config);
		eventDetailsConfigService.deleteByObj(config);
		/**设置详情信息**/
		
		
		EventDetails details = null;
		for(int i=0;i<paragraphs.size();i++){
			 details = new EventDetails();
			 details.setContent(paragraphs.getJSONObject(i).getString("value"));
			 details.setEventId(eventId);
			 details.setModuleId(config.getId());
			 details.setSortNum(i);
			 details.setType(paragraphs.getJSONObject(i).getString("type"));
			 eventDetailsService.save(details);
		}
		/**设置模块与事件关联 **/
		EventModule em = new EventModule();
		em.setEventId(eventId);
		em.setModuleId(config.getId());
		em.setModuleLabel(moduleType);
		eventModuleService.delete(em);
		eventModuleService.save(em);
		moduleId = config.getId();
		}else{
			//更新
			eventDetailsService.deleteByEvent(eventId);
			
			EventDetails details = null;
			for(int i=0;i<paragraphs.size();i++){
				 details = new EventDetails();
				 details.setContent(paragraphs.getJSONObject(i).getString("value"));
				 details.setEventId(eventId);
				 details.setModuleId(moduleId);
				 details.setSortNum(i);
				 details.setType(paragraphs.getJSONObject(i).getString("type"));
				 eventDetailsService.save(details);
			}
		}
		JSONObject resultData = new JSONObject();
		resultData.put("moduleId", moduleId);
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "事件详情保存成功");
		return pakageJsonp(resultObj);
		
		
	}
	
	/** 删除模块 **/
	
	@RequestMapping("/removeModule")
	public String removeModule(HttpServletRequest request){
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("eventId")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失eventId");
			return pakageJsonp(resultObj);
		}
		if (!parameterObj.containsKey("moduleId")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失moduleId");
			return pakageJsonp(resultObj);
		}
		if (!parameterObj.containsKey("moduleType")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失moduleType");
			return pakageJsonp(resultObj);
		}
		String eventId = parameterObj.getString("eventId");
		String moduleId = parameterObj.getString("moduleId");
		String moduleType = parameterObj.getString("moduleType");
		EventModule em = new EventModule();
		em.setModuleId(moduleId);
		em.setEventId(eventId);
		em.setModuleLabel(moduleType);
		eventModuleService.delete(em);
		
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "模块关联删除成功");
		return pakageJsonp(resultObj);
	}
	
	/**
	 * 获取事件的首页图片
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getEventPoster")
	public String getEventPoster(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("eventId")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失eventId");
			return pakageJsonp(resultObj);
		}
		
		String eventId = parameterObj.getString("eventId");
		EventInfo event = eventInfoService.findByKey(eventId);
		JSONObject resultData = new JSONObject();
		String indexUrl = event.getIndexUrl();
		resultData.put("poster", indexUrl);
		resultObj.put("resultData", resultData);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "获取首页大图成功");
		return pakageJsonp(resultObj);
	}
	
	/**改变事件显示状态
	 * @throws Exception */
	@RequestMapping("/toggleEvent")
	public String toggleEvent(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject parameterObj = super.readToJSONObect(request);
		JSONObject resultObj = new JSONObject();
		if (!parameterObj.containsKey("eventId")) {
			resultObj.put("errCode", "1000");
			resultObj.put("resultMsg", "参数缺失eventId");
			return pakageJsonp(resultObj);
		}
		String eventId = parameterObj.getString("eventId");
		EventInfo event = eventInfoService.findByKey(eventId);
		if(event.getIsPublic().equals("1")){
			event.setIsPublic("0");
		}else{
			event.setIsPublic("1");
		}
		eventInfoService.update(event);
		resultObj.put("errCode", "0000");
		resultObj.put("resultMsg", "展示状态修改成功");
		return pakageJsonp(resultObj);
	}

}
