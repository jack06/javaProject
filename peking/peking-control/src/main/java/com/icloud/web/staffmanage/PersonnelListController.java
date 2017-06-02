package com.icloud.web.staffmanage;

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
import com.icloud.model.staffmanage.Tuser;
import com.icloud.service.staffmanage.PersonnelListService;

@Controller
public class PersonnelListController extends StaffManageBaseController<Tuser>{

	@Autowired
	private PersonnelListService personnelListService;		
	
	@RequestMapping("/staffmanage/personnel_list")
	public String list(HttpServletRequest request, Tuser user) throws Exception {
		List<Tuser> list = personnelListService.findList(user);
		  request.setAttribute("user", user);
		  request.setAttribute("list", list);
		  return "staffmanage/personnel_list";
	}	
	
	@RequestMapping("/staffmanage/personnel_getlist")
	public String getList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String nick = request.getParameter("nick");
		String pageNo = request.getParameter("pageNo");		
		JSONObject json = new JSONObject();
		Tuser user = new Tuser();
		if(StringUtils.isNotBlank(nick)){
			user.setNick(nick);
		}
		if(StringUtils.isNotBlank(pageNo)){
			user.setPageNo(Integer.parseInt(pageNo));
		}
		List<Tuser> list =  personnelListService.findList(user);
		json.put("pages", user.getPages());
		JSONArray array = new JSONArray();
		for(Tuser u:list){
			JSONObject sub = new JSONObject();
			sub.put("id",u.getId());
			sub.put("openId", u.getOpenId());
			sub.put("nick", u.getNick());
			sub.put("wxHeadImg", u.getWxHeadImg());
			
			array.add(sub);
		}
		json.put("list", array);
		ResponseUtils.renderJson(response, json.toJSONString());
		return null;
		
		
	}

	@Override
	public String toinput(String id, HttpServletRequest request)
			throws Exception {
		return null;
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
	
	
}
