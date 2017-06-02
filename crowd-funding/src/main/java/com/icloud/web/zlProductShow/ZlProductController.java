package com.icloud.web.zlProductShow;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icloud.bean.Page;
import com.icloud.bean.ResultMessage;
import com.icloud.dto.BaseAddDto;
import com.icloud.dto.BaseDto;
import com.icloud.dto.zlProductShow.ZlProductListDto;
import com.icloud.dto.zlProductShow.ZlProductQueryDto;
import com.icloud.form.zlProductShow.ZlProductListForm;
import com.icloud.model.zlProductShow.ZlProduct;
import com.icloud.service.zlProductShow.ZlProductService;
import com.icloud.util.RequestUtil;
import com.icloud.web.BaseController;

@RestController
@RequestMapping(value = "/zl_product")
public class ZlProductController extends BaseController {

	public final static Logger log = LoggerFactory.getLogger(ZlProductController.class);
	@Autowired
	private ZlProductService zlProductService;
	
	/**
	 * 新增
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/add_product")
	public void addProduct(HttpServletRequest request) throws Exception {
	    String jsonText = RequestUtil.readPostContent(request); 
		log.error(jsonText);
		ZlProduct record = new ZlProduct();
		if(jsonText!=null && !"".equals(jsonText)){
			record = JSON.parseObject(jsonText,ZlProduct.class);
		}
		BaseAddDto dto = zlProductService.addProduct(record);
		log.error(JSONObject.toJSONString(dto));
		outObject(JSONObject.toJSONString(dto));
	}
	/**
	 * 修改
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/update_product")
	public void updateProduct(HttpServletRequest request) throws Exception {
	    String jsonText = RequestUtil.readPostContent(request); 
		log.error(jsonText);
		ZlProduct record = new ZlProduct();
		if(jsonText!=null && !"".equals(jsonText)){
			record = JSON.parseObject(jsonText,ZlProduct.class);
		}
		BaseDto dto = zlProductService.updateProduct(record);
		log.error(JSONObject.toJSONString(dto));
		outObject(JSONObject.toJSONString(dto));
	}
	/**
	 * 根据id查询
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/findById")
	public void findById(HttpServletRequest request) throws Exception {
	    String id = RequestUtil.readPostContent(request); 
		ZlProductQueryDto dto = zlProductService.findById(Long.valueOf(id));
		log.error(JSONObject.toJSONString(dto));
		outObject(JSONObject.toJSONString(dto));
	}
	/**
	 * 后台分页
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/listByPage")
	public void listByPage(HttpServletRequest request) throws Exception {
	    String jsonText = RequestUtil.readPostContent(request); 
		log.error(jsonText);
		ZlProductListForm form = null;
		if(jsonText!=null && !"".equals(jsonText)){
			form = JSON.parseObject(jsonText,ZlProductListForm.class);
		}
		ZlProductListDto dto = zlProductService.listByPage(form);
		log.error(JSONObject.toJSONString(dto));
		outObject(JSONObject.toJSONString(dto));
	   }
	
	/**
     * 根据id删除
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/deleteById")
	public void deleteById(HttpServletRequest request) throws Exception {
    	String id = RequestUtil.readPostContent(request); 
		log.error(id);
		BaseDto dto = zlProductService.deleteById(Long.valueOf(id));
		outObject(JSONObject.toJSONString(dto));
    }
    /**
     * 微信分页
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/listByPage4Wx")
	public void listByPage4Wx(HttpServletRequest request) throws Exception {
    	log.error("---------------微信端产品展示----------------");
    	String jsonText = RequestUtil.readPostContent(request); 
		log.error("--------------"+jsonText);
		ZlProductListForm form = null;
		if(jsonText!=null && !"".equals(jsonText)){
			form = JSON.parseObject(jsonText,ZlProductListForm.class);
		}
//    	ZlProductListForm form = new ZlProductListForm();
//    	form.setPageNum(1);
//    	form.setPageSize(4);
//    	form.setTotalCount(7L);
//    	int offset = form.getOffset();
//    	int end = form.getEndRownum();
		ZlProductListDto dto = null;
		try {
			ResultMessage result = zlProductService.listByPage4Wx(form);
			if (result.isSuccess()) {
				Page<ZlProduct> page = (Page<ZlProduct>) result.getData();
				dto = new ZlProductListDto("success","10000",page.getRecordList());
				dto.setPageNum(page.getPageNum());
				dto.setPageSize(page.getPageSize());
				dto.setTotalCount(page.getTotalCount());
				dto.setTotalPage(page.getTotalPage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			dto = new ZlProductListDto("fail","10001","查询失败");
		}
		log.error(JSONObject.toJSONString(dto));
		outObject(JSONObject.toJSONString(dto));
    }
}
