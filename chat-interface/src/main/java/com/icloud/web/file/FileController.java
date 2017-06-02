package com.icloud.web.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.icloud.common.Contants;
import com.icloud.common.ftp.FtpFileService;
import com.icloud.common.util.ConfigUtil;
import com.icloud.web.BaseController;

@RestController
public class FileController extends BaseController {
	@Autowired
	private FtpFileService ftpFileService;
	
	
	@RequestMapping("/fileUpload")
	public String fileUpFtp(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		JSONObject resultObj = new JSONObject();
		if(null!=file){
			String fileName = file.getOriginalFilename();
			fileName = UUID.randomUUID().toString().replace("-", "")+"_"+fileName;
			String basePath = Contants.IMG_BASE_PATH_;
			try {
				boolean result = false;
				synchronized (this) {
					result = ftpFileService.upload(file.getBytes(), ConfigUtil.get("filepath")+basePath, fileName);
				}
				if(!result){
			    	resultObj.put("errCode", "4001");
					resultObj.put("resultMsg", "服务器未知错误");
					return pakageJsonp(resultObj);
			    }
			
			} catch (Exception e) {
				e.printStackTrace();
				
				resultObj.put("errCode", "4001");
				resultObj.put("resultMsg", "服务器未知错误");
				return pakageJsonp(resultObj);
			}finally {
				
			}
			JSONObject resultDate = new JSONObject();
			resultDate.put("imgUrl", Contants._DO_MAIN_+basePath+fileName);
			resultObj.put("resultData", resultDate);
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "图片上传成功");
			return pakageJsonp(resultObj);
		}else{
			resultObj.put("errCode", "4000");
			resultObj.put("resultMsg", "图片为空");
			return pakageJsonp(resultObj);
		}
   }

	/**上传至本地 **/
	public String handleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		JSONObject resultObj = new JSONObject();
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String basepath = Contants.IMG_BASE_PATH_+UUID.randomUUID().toString().replace("-", "")+"/"+fileName;
			File toFile = new File(request.getServletContext().getRealPath(basepath));
			if(!toFile.getParentFile().exists()){
				toFile.getParentFile().mkdirs();
			}
			try {
				file.transferTo(toFile);
				JSONObject resultDate = new JSONObject();
				resultDate.put("imgUrl", Contants._DO_MAIN_+basepath);
				resultObj.put("resultData", resultDate);
				resultObj.put("errCode", "0000");
				resultObj.put("resultMsg", "图片上传成功");
				return pakageJsonp(resultObj);
				
			} catch (IllegalStateException |IOException e) {
				e.printStackTrace();
				resultObj.put("errCode", "4001");
				resultObj.put("resultMsg", "服务器未知错误");
				return pakageJsonp(resultObj);
			}
		} else {

			resultObj.put("errCode", "4000");
			resultObj.put("resultMsg", "图片为空");
			return pakageJsonp(resultObj);
		}
		
	}
	
	
	
	
	
}
