package com.icloud.web.user;

import javax.servlet.http.HttpServletRequest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.icloud.service.user.LoginService;
import com.icloud.common.util.RequestUtil;
import com.icloud.web.BaseController;


@RestController
@CrossOrigin
public class LoginController extends BaseController {
	@Autowired
	private LoginService loginService;
 
	/**微信小程序登录接口**/
	@RequestMapping(value = "/wx/login")
	 public Object login(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request);  
		System.out.println("sdf"+jsonText);
		return  pakageJsonp(loginService.login(jsonText,request));
    }
	
	/**登录会话有效性验证**/
	@RequestMapping(value="/wx/checkSession")
	public Object checkSession(HttpServletRequest request){
		String jsonText = RequestUtil.readPostContent(request);
		Object obj = loginService.checkSession(jsonText, request);
		return pakageJsonp(obj);
	}
	
	
}