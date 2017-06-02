package com.icloud.service.eventmanage.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.common.Contants;
import com.icloud.common.DateTools;
import com.icloud.common.excel.ReadExcel;
import com.icloud.common.util.wx.HttpClientUtils;
import com.icloud.dao.doubleChoice.CompanyDetailsMapper;
import com.icloud.dao.eventmanage.EventDetailsConfigMapper;
import com.icloud.dao.eventmanage.EventDetailsMapper;
import com.icloud.dao.eventmanage.EventInfoMapper;
import com.icloud.dao.eventmanage.EventModuleMapper;
import com.icloud.dao.eventmanage.TeventTypeMapper;
import com.icloud.model.doubleChoice.CompanyDetails;
import com.icloud.model.eventmanage.EventDetails;
import com.icloud.model.eventmanage.EventDetailsConfig;
import com.icloud.model.eventmanage.EventInfo;
import com.icloud.model.eventmanage.EventModule;
import com.icloud.model.eventmanage.TeventType;
import com.icloud.service.eventmanage.EventInfoService;


@Service
public class EventInfoServiceImpl implements EventInfoService {

	@Autowired
	private EventInfoMapper eventInfoMapper;
	@Autowired
	private EventDetailsConfigMapper eventDetailsConfigMapper;
	@Autowired
	private EventDetailsMapper eventDetailsMapper;
	@Autowired
	private EventModuleMapper eventModuleMapper;
	@Autowired
	private TeventTypeMapper eventTypeMapper;
	@Autowired
	private CompanyDetailsMapper companyDetailsMapper;
	
	@Override
	public void save(EventInfo t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(EventInfo t) throws Exception {
          eventInfoMapper.update(t);
	}

	@Override
	public List<EventInfo> findList(EventInfo t) throws Exception {
		return null;
	}

	@Override
	public Integer findCount(EventInfo t) throws Exception {
		return null;
	}

	@Override
	public void delete(String id) throws Exception {

	}

	@Override
	public EventInfo findByKey(String id) throws Exception {
		return eventInfoMapper.findForObject(id);
	}

	@Override
	public PageInfo<EventInfo> findByPage(PageInfo<EventInfo> page, EventInfo t)
			throws Exception {
		return null;
	}
	

	public PageInfo<EventInfo> findByPage(int pageNo,int pageSize,EventInfo t)
			throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<EventInfo> list = eventInfoMapper.findForList(t);
		return new PageInfo<EventInfo>(list);
	}

	@Override
	public int importData(MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
				.substring(fileName.lastIndexOf(".") + 1);
		    ReadExcel excel = new ReadExcel();
		
			File temFile = File.createTempFile("temp", "."+extension);
			file.transferTo(temFile);
		    List<List<Object>> data = excel.readExcel(temFile,0, 1, 0, 7);
		    Map<String,List<List<Object>>> map = mergeData(excel.readExcel(temFile,1, 1, 0,2));
		    if(null!=data&&data.size()>0){
		    	List<TeventType> typeList = eventTypeMapper.selectAllList();
		    	for(List<Object> list:data){
		    			EventInfo info = new EventInfo();
		    			
		    			info.setEventName(list.get(0).toString());
		    			info.setCreateDate(DateTools.str2Date(list.get(1).toString(), "yyyy-MM-dd HH:mm:ss"));
		    			
		    			
		    			info.setParentId("0");
		    			info.setIsPublic("1");
		    			info.setEventStatus("2");
		    			
		    			/** 设置类型 **/
		    			for(TeventType eventType : typeList){
		    				if(eventType.getTypeMark().equals(list.get(3).toString())){
		    					info.setTypeId(eventType.getId());
		    					break;
		    				}
		    			}
		    			if(StringUtils.isNotBlank(list.get(4).toString())){
		    			info.setIsTop(list.get(4).toString());
		    			}else{
		    				info.setIsTop("0");
		    			}
		    			if(StringUtils.isNotBlank(list.get(5).toString())){
		    				info.setStartDate(DateTools.str2Date(list.get(5).toString(), "yyyy-MM-dd HH:mm:ss"));
			    			info.setEndDate(DateTools.str2Date(list.get(5).toString(), "yyyy-MM-dd HH:mm:ss"));
		    			}else{
		    				info.setStartDate(info.getCreateDate());
			    			info.setEndDate(info.getCreateDate());
		    			}
		    			
		    			if(StringUtils.isNotBlank(list.get(6).toString())){
		    				info.setAddress(list.get(6).toString().trim());
			    			JSONObject json = HttpClientUtils.get(Contants.BD_LBS_GEOCODER_URL.replace("ADDRESS",list.get(6).toString().trim()).replace("AK", Contants.BD_LBS_API_KEY));
			    			if(json.getString("status").equals("0")){
			    				info.setLatitude(json.getJSONObject("result").getJSONObject("location").getString("lat"));
			    				info.setLongitude(json.getJSONObject("result").getJSONObject("location").getString("lng"));
			    			}
		    			}
		    			
		    			info.setPublisherType("system");
		    			info.setEventOriginator("system");
		    			
		    		    eventInfoMapper.save(info);
		    		    addEventDetails(info,list.get(2).toString());
		    		    
		    		     List<List<Object>> ll= map.get(list.get(7).toString());
		    		     if(null!=ll&&ll.size()>0){
		    		    	 CompanyDetails d = null;
		    		    	 List<CompanyDetails> dList = new ArrayList<CompanyDetails>();
		    		    	 for(List<Object> l:ll){
		    		    		 d = new CompanyDetails();
		    		    		 d.setCompanyName(l.get(0).toString());
		    		    		 d.setDetails(l.get(1).toString());
		    		    		 d.setEventId(info.getId());
		    		    		 d.setEventName(info.getEventName());
		    		    		 dList.add(d);
		    		    	 }
		    		    	 companyDetailsMapper.batchInsert(dList);
		    		    	 
		    		     }
		    		    
		    		    
		    		}
		    	}
		    return data.size();
		
	}
	
	public void addEventDetails(EventInfo event,String content){
		com.icloud.model.eventmanage.EventDetailsConfig config = new EventDetailsConfig();
		config.setEventId(event.getId());
		eventDetailsConfigMapper.save(config);
		eventDetailsConfigMapper.deleteByObj(config);
		/**设置详情信息**/
		 EventDetails details = new EventDetails();
		 details.setContent(content);
		 details.setEventId(event.getId());
		 details.setModuleId(config.getId());
		 details.setSortNum(0);
		 details.setType("1");
		 eventDetailsMapper.save(details);
		
		/**设置模块与事件关联 **/
		EventModule em = new EventModule();
		em.setEventId(event.getId());
		em.setModuleId(config.getId());
		em.setModuleLabel("1");
		eventModuleMapper.delete(em);
		eventModuleMapper.save(em);
	}
	
	
	public Map<String,List<List<Object>>> mergeData(List<List<Object>> data){
		
		Map<String,List<List<Object>>> map = new HashMap<String,List<List<Object>>>();
		for(List<Object> list:data){
			if(null==map.get(list.get(2).toString())){
				List<List<Object>> ll = new ArrayList<List<Object>>();
				ll.add(list);
				map.put(list.get(2).toString(), ll);
			}else{
				List<List<Object>> ll = map.get(list.get(2).toString());
				ll.add(list);
				map.put(list.get(2).toString(), ll);
			}
		}
		return map;
		
	}

}
