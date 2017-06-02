package com.icloud.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icloud.dto.BaseDto;
import com.icloud.dto.RaiseDetailDto;
import com.icloud.dto.RaiseEntityDto;
import com.icloud.dto.RaiseQueryDto;
import com.icloud.dto.RecordDto;
import com.icloud.form.RaiseForm;
import com.icloud.form.RecordForm;
import com.icloud.model.Raise;
import com.icloud.service.PrizeRecordService;
import com.icloud.service.RaiseOrderService;
import com.icloud.service.RaiseService;
import com.icloud.util.RaiseComparator;
import com.icloud.util.RequestUtil;


@RestController
@RequestMapping(value = "/raise")
public class RaiseController extends BaseController {
	
	public final static Logger log = LoggerFactory.getLogger(RaiseController.class);
	@Autowired
	private RaiseService raiseService; 
	@Autowired
	private RaiseOrderService raiseOrderService;
	@Autowired
	PrizeRecordService   prizeRecordService; 


	/**首页众筹筹活动列表**/
	@RequestMapping(value = "/raiselist")
	 public void raiselist(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request);  
		log.error("jsonText=="+jsonText);
		RaiseForm raiseForm = new RaiseForm();
		RaiseQueryDto  baseDto = null;
		try {
			if(jsonText!=null && !"".equals(jsonText)){
				raiseForm = JSON.parseObject(jsonText,RaiseForm.class);
				log.error("传入的没页条数====="+raiseForm.getPageSize());
				log.error("传入的页码====="+raiseForm.getPageNum());
			}
			baseDto = raiseService.listCenter(raiseForm);
			if(baseDto!=null){
				log.error("总记录数====="+(baseDto.getRaiseList()!=null?baseDto.getRaiseList().size():0));
				baseDto = getNewRaiseQueryDto(baseDto);
				log.error("第一次处理后的总记录数====="+(baseDto.getRaiseList()!=null?baseDto.getRaiseList().size():0));
			}
		} catch (Exception e) {
		   	 e.printStackTrace();
		     baseDto = new RaiseQueryDto("fail","10001","查询失败",null);
		}
		outObject(JSONObject.toJSONString(baseDto));
    }
	
	/**
     * 去除 productSpecifications，避免在页面转化出现错误
     * @author   : zdh
     * @date     : 创建时间：
     * @version  : 1.0  
     * @return
     *
     */
    private RaiseQueryDto getNewRaiseQueryDto(RaiseQueryDto raiseQueryDto){
   	 List<Raise> newlist = new ArrayList<Raise>();
   	 if(raiseQueryDto!=null){
   		 List<Raise> list = raiseQueryDto.getRaiseList();
   		 if(list!=null && list.size()>0){
   			 newlist = new ArrayList<Raise>();
   			 for (int i = 0; i < list.size(); i++) {
   				 Raise raise = list.get(i);
   				 raise.setProductSpecifications("");
   				 raise.setProductDetails("");
   				 newlist.add(raise);
				}
   			raiseQueryDto.setRaiseList(newlist);
   		 }
   	 }
   	 newlist = raiseQueryDto.getRaiseList();
   	 Collections.sort(newlist,new RaiseComparator());
   	 raiseQueryDto.setRaiseList(newlist);
		return raiseQueryDto;
    }
	
    /**
     * 物理分页
     * @author   : zdh
     * @date     :
     * @version  : 1.0  
     * @return   :
     */
	private RaiseQueryDto getByPage(RaiseForm raiseForm,RaiseQueryDto raiseQueryDto){
    	if(raiseQueryDto!=null && raiseQueryDto.getRaiseList()!=null){
    		raiseQueryDto.setTotalCount(raiseQueryDto.getRaiseList().size());
    		//活动总数比 raiseForm.getPageSize() 小 ，不截取
    		if(raiseQueryDto.getRaiseList().size()<=raiseForm.getPageSize()){
    			log.error("总记录数比没页显示条数少=====raiseQueryDto.getRaiseList().size()<=raiseForm.getPageSize()==="+(raiseQueryDto.getRaiseList().size()<=raiseForm.getPageSize()));
    			raiseQueryDto.setTotalPage(Long.valueOf(1));
    			return raiseQueryDto;
    		}
    		/*活动总数比 raiseForm.getPageSize() 大 ，截取*/
    		//起始页
    		int fromIndex = (raiseForm.getPageNum()-1)*(raiseForm.getPageSize());
    		//结束页
    		int toIndex = fromIndex + raiseForm.getPageSize();
    		if(raiseQueryDto.getRaiseList().size()<=toIndex){
    			toIndex = raiseQueryDto.getRaiseList().size();
    		}
    		// 总页数
    		raiseQueryDto.setTotalPage(Long.valueOf((raiseQueryDto.getRaiseList().size()/raiseForm.getPageSize()+1)));
    		raiseQueryDto.setRaiseList(raiseQueryDto.getRaiseList().subList(fromIndex,toIndex));
    				
    	}
		return raiseQueryDto;
    }
    
	/**中众筹活动列表**/
	@RequestMapping(value = "/listByPage")
	 public void listByPage(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request);  
		log.error("jsonText=="+jsonText);
		RaiseForm raiseForm = new RaiseForm();
		RaiseQueryDto  baseDto = null;
		try {
			if(jsonText!=null && !"".equals(jsonText)){
				raiseForm = JSON.parseObject(jsonText,RaiseForm.class);
			}
			baseDto = raiseService.listBypPage(raiseForm);
		} catch (Exception e) {
		   	 e.printStackTrace();
		     baseDto = new RaiseQueryDto("fail","10001","查询失败",null);
		}
		/*
		测试法模板消息中的单个方法能否成功!
		Raise raise = raiseService.selectByPrimaryKey(new Long(1));
		log.error("众筹ID===="+raise.getId());
		log.error("众筹名字==="+raise.getRaiseName());
		
		PrizeRecord prizeRecord  =new PrizeRecord();
		prizeRecord.setUserId(new Long(1));
		
		String xxOpenId = prizeRecordService.getOPenidByUserId(prizeRecord);
		log.error("openId=="+xxOpenId);
		
		prizeRecord.setOrderId(new Long(1));
		prizeRecord.setIsNotify("1");
		prizeRecordService.updateMsgSendState(prizeRecord);
		 */

		
		outObject(JSONObject.toJSONString(baseDto));
    }
	
    /**
     * 众筹详情
     * @author   : zdh
     * @return
     */
    @RequestMapping(value = { "raiseDetail" }, method = RequestMethod.POST)
	public void raiseDetail(HttpServletRequest request, HttpServletResponse response,ModelMap model){
    	RaiseDetailDto dto = null;
		try {
			JSONObject jsonObj = RequestUtil.readToJSONObect(request);
			dto = raiseService.getRaiseById(jsonObj.getLongValue("id"));
		} catch (Exception e) {
			e.printStackTrace();
			dto = new RaiseDetailDto("fail", "10001", null);
			dto.setResultCode("10001");
			dto.setResultType("fail");
			dto.setErrorMsg("查询失败");
		}
		String json = JSONObject.toJSONString(dto);
		log.error(json);
		outObject(json);
    }
        
    
    /**
     * 修改状态
     */
    @RequestMapping(value = { "changeStatus" }, method = RequestMethod.POST)
	public void changeStatus(HttpServletRequest request){
    	String jsonText = RequestUtil.readPostContent(request);  
		log.error("jsonText=="+jsonText);
		RaiseForm raiseForm =null;
		BaseDto  baseDto  = new BaseDto("fail","10001",null);
		try {
			if(jsonText!=null && !"".equals(jsonText)){
				raiseForm = JSON.parseObject(jsonText,RaiseForm.class);
				Raise newraise = raiseForm.getRaise();
				Raise  raise = raiseService.selectByPrimaryKey(newraise.getId());
				if(raise.getCurrentStatus().equals( newraise.getCurrentStatus())){
					baseDto.setErrorMsg("已更新");
					outObject(JSONObject.toJSONString(baseDto));
				}
				int a = raiseService.updateByPrimaryKeySelective(raiseForm.getRaise());
				if(a==1){
					baseDto.setResultCode("10000");
					baseDto.setResultType("success");
				}
			}
		} catch (Exception e) {
		   	 e.printStackTrace();
			 baseDto.setErrorMsg("更新失败");
		}
		outObject(JSONObject.toJSONString(baseDto));
    } 
 
	/**
	 * 参与的用户记录
	 * 我参与过的记录
	 * @author   : zdh
	 * @date     : 创建时间：2017年4月14日 下午6:20:05  
	 * @version  : 1.0  
	 */
	@RequestMapping(value = "/recondList")
	 public void recondList(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request);  
		log.error("jsonText=="+jsonText);
		RecordForm recordForm = new RecordForm();
		RecordDto  baseDto = null;
		try {
			if(jsonText!=null && !"".equals(jsonText)){
				recordForm = JSON.parseObject(jsonText,RecordForm.class);
			}
			baseDto = raiseOrderService.recondList(recordForm);
		} catch (Exception e) {
		   	 e.printStackTrace();
		     baseDto = new RecordDto("fail","10001",null);
			 baseDto.setResultCode("10001");
			 baseDto.setResultType("fail");
			 baseDto.setErrorMsg("查询失败");
		}
		outObject(JSONObject.toJSONString(baseDto));
    }	 
	/**
	 * 添加众筹记录
	 * @author : ldf
	 */
	@RequestMapping(value = "/raiseAddDo")
	public void raiseAddDo(HttpServletRequest request) throws Exception {
    	String jsonText = RequestUtil.readPostContent(request);  
		log.error("jsonText=="+jsonText);
		RaiseForm raiseForm =null;
		BaseDto  baseDto  = new BaseDto("fail","10001",null);
		try {
			if(jsonText!=null && !"".equals(jsonText)){
				raiseForm = JSON.parseObject(jsonText,RaiseForm.class);
				Raise raise = raiseForm.getRaise();
				raiseService.insertSelective(raise);
				baseDto.setResultCode("10000");
				baseDto.setResultType("success");
			}
		} catch (Exception e) {
		   	 e.printStackTrace();
			 baseDto.setErrorMsg("新增失败");
		}
		outObject(JSONObject.toJSONString(baseDto));		
    }
	/**
	 * 修改众筹记录
	 * @author : ldf
	 */
	@RequestMapping(value = "/raiseUpdateDo")
	public void raiseUpdateDo(HttpServletRequest request) throws Exception {
    	String jsonText = RequestUtil.readPostContent(request);  
		log.error("jsonText=="+jsonText);
		RaiseForm raiseForm =null;
		BaseDto  baseDto  = new BaseDto("fail","10001",null);
		try {
			if(jsonText!=null && !"".equals(jsonText)){
				raiseForm = JSON.parseObject(jsonText,RaiseForm.class);
				Raise raise = raiseForm.getRaise();
				raiseService.updateByPrimaryKeySelective(raise);
				baseDto.setResultCode("10000");
				baseDto.setResultType("success");
			}
		} catch (Exception e) {
		   	 e.printStackTrace();
			 baseDto.setErrorMsg("更新失败");
		}
		outObject(JSONObject.toJSONString(baseDto));		
    }	
	/**
	 * 删除一个众筹
	 * @author : ldf
	 */
	@RequestMapping(value = "/raiseDelOne")
	public void raiseDelOne(HttpServletRequest request) throws Exception {
		String id = RequestUtil.readPostContent(request); 
		BaseDto  baseDto  = new BaseDto("fail","10001",null);
		try {
				raiseService.deleteByPrimaryKey(Long.parseLong(id));
				baseDto.setResultCode("10000");
				baseDto.setResultType("success");
		} catch (Exception e) {
		   	 e.printStackTrace();
			 baseDto.setErrorMsg("更新失败!");
		}
		outObject(JSONObject.toJSONString(baseDto));
    }	
	/**
	 * 删除多个众筹
	 * @author : ldf
	 */
	@RequestMapping(value = "/raiseDelMore")
	public void raiseDelMore(HttpServletRequest request) throws Exception {
		
    }
	/**
	 * 过往众筹活动列表
	 * @author : ldf
	 */
	@RequestMapping(value = "/raisePast")
	 public void raisePast(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request);  
		//log.error("jsonText=="+jsonText);//查看传递过来的参数
		RaiseForm raiseForm = new RaiseForm();
		RaiseQueryDto  baseDto = null;
		Map<String,Object> map = null;
		try {
			if(jsonText!=null && !"".equals(jsonText)){
				map = JSON.parseObject(jsonText,Map.class);
				//log.error("openid=="+map.get("openid"));
			}
			baseDto = raiseService.raisePast(map);
		} catch (Exception e) {
		   	 e.printStackTrace();
		     baseDto = new RaiseQueryDto("fail","10001",null);
			 baseDto.setResultCode("10001");
			 baseDto.setResultType("fail");
			 baseDto.setErrorMsg("查询失败");
		}
		String ss = JSONObject.toJSONString(baseDto);
		outObject(JSONObject.toJSONString(baseDto));
    }
    /**
     * 通过id获取众筹信息,只返回实体
     * @author: ldf
     */
    @RequestMapping(value = { "/raiseGetById" }, method = RequestMethod.POST)
	public void raiseGetById(HttpServletRequest request, HttpServletResponse response){
    	RaiseEntityDto dto = null;
		try {
			JSONObject jsonObj = RequestUtil.readToJSONObect(request);
			dto = raiseService.raiseGetById(jsonObj.getLongValue("id"));
		} catch (Exception e) {
			e.printStackTrace();
			dto = new RaiseEntityDto();
			dto.setResultCode("10001");
			dto.setResultType("fail");
			dto.setErrorMsg("查询失败");
		}
		String json = JSONObject.toJSONString(dto);
		outObject(json);
    }	
    
    /**已完成的众筹活动列表**/
	@RequestMapping(value = "/raiseCompletedList")
	public void raiseCompletedList(HttpServletRequest request) throws Exception {
		String jsonText = RequestUtil.readPostContent(request);
		log.error("jsonText==" + jsonText);
		RaiseForm raiseForm = new RaiseForm();
		RaiseQueryDto baseDto = null;
		try {
			if (jsonText != null && !"".equals(jsonText)) {
				raiseForm = JSON.parseObject(jsonText, RaiseForm.class);
			}
			baseDto = raiseService.findRaiseCompletedList(raiseForm);
		} catch (Exception e) {
			e.printStackTrace();
			baseDto = new RaiseQueryDto("fail", "10001", "查询失败", null);
		}
		outObject(JSONObject.toJSONString(baseDto));
	}
	
	 /**根据订单号查询众筹活动的信息**/
		@RequestMapping(value = "/selectRaiseByOrderId")
		public void selectRaiseByOrderId(HttpServletRequest request) throws Exception {
			String orderId = RequestUtil.readPostContent(request);
			log.error("jsonText==" + orderId);
//			String orderId = "8807";
			RaiseEntityDto dto = null;
			try {
				dto = raiseService.selectRaiseByOrderId(Long.valueOf(orderId));
			} catch (Exception e) {
				e.printStackTrace();
				dto.setResultCode("10001");
				dto.setResultType("fail");
				dto.setErrorMsg("查询失败");
			}
			outObject(JSONObject.toJSONString(dto));
		}
    
//
//	 @RequestMapping(value = "/raise/add")
//	 public void add(HttpServletRequest request) throws Exception {
//		String jsonText = RequestUtil.readPostContent(request);  
//		log.error("jsonText=="+jsonText);
//		
//		for (int i = 1; i < 26; i++) {
//			log.error("i=="+i);
//			Raise raise = new Raise();
//			raise.setId(Long.valueOf(i));
//			raise.setCurrentPeriod(5L);
//			raise.setCurrentNum(Long.valueOf(i));
//			raise.setCurrentStatus("0");
//			Calendar c = Calendar.getInstance();
//			c.setTime(new Date());
//			
//			raise.setStartDate(c.getTime());
//			c.add(Calendar.DATE, i);
//			raise.setEndDate(c.getTime());
//			c.add(Calendar.DATE,  i+1);
//			raise.setOutTime(c.getTime());
//			raise.setMaxSare(1L);
//			raise.setEachTotal(10L);
//			raise.setPrizeAmount(10L);
//			raise.setProductDetails("");
//			raise.setProductSpecifications("feis");
//			raise.setRaiseIcon("");
//			raise.setTotalShare(20L);
//			raise.setRaiseName("iphone9");
//			raise.setProductPrice(2399D);
//			raise.setProductName("iphone9");
//			raise.setTotalLedou(20L);
//			raiseService.add(raise); 
//		}
//		
//	 }
		
}