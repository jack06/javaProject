package com.icloud.web.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icloud.service.user.EncryptionService;
import com.icloud.web.BaseController;


@RestController
public class EncryptionController extends BaseController {
	@Autowired
	 private EncryptionService encryptionService;
	
 
	/**微信小程序加密接口**/
	@RequestMapping(value = "/encry/encryption")
	 public Object encryption(String data,HttpServletRequest request) throws Exception {
        return  pakageJsonp(encryptionService.encryption(data));
    }

}