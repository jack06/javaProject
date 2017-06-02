package com.icloud.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.dao.AwardRecordMapper;
import com.icloud.dto.AwardRecordExportExcleDto;
import com.icloud.dto.AwardRecordListDto;
import com.icloud.dto.BaseDto;
import com.icloud.dto.ExportExcleDto;
import com.icloud.dto.RaiseQueryDto;
import com.icloud.form.AwardRecordFrom;
import com.icloud.model.AwardRecord;
import com.icloud.model.Raise;
import com.icloud.service.AwardRecordService;
import com.icloud.util.ConfigUtil;
import com.icloud.util.EmsUtil;
import com.icloud.vo.AwardRecordVo;

import net.sf.json.JSONArray;

@Service
public class AwardRecordServiceImpl implements AwardRecordService {

	@Autowired
	private AwardRecordMapper awardRecordMapper;
	
	@Override
	public ExportExcleDto ExportExlceAwardRecord(AwardRecordFrom awardRecordFrom) {
		List<AwardRecordExportExcleDto> list = awardRecordMapper.ExportExlceAwardRecord(awardRecordFrom);
		ExportExcleDto dto = new ExportExcleDto("success","10001",list);
		return dto;
	}

	@Override
	public AwardRecordListDto listByPage(AwardRecordFrom from) {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		
		com.github.pagehelper.Page  pages = PageHelper.startPage(from.getPageNum(),from.getPageSize());
		AwardRecordListDto dto = null;
		List<AwardRecordVo> list;
		if(from != null){
			if(from.getDeliveryPhone() != null && !"".equals(from.getDeliveryPhone())){
				criteria.andFieldEqualTo("DELIVERY_PHONE", from.getDeliveryPhone());
			}
			if(from.getDeliveryStatus() != null && !"".equals(from.getDeliveryStatus())){
				criteria.andFieldEqualTo("DELIVERY_STATUS", from.getDeliveryStatus());
			}
		}
		try {
			list = awardRecordMapper.listByPage(example);
			System.out.println("list.size="+list.size());
//			criteria.andFieldEqualTo("isvalid", "1");
			dto = new AwardRecordListDto("success","10000",list);
			dto.setPageNum(pages.getPageNum());
		    dto.setStartIndex(pages.getStartRow());
		    dto.setPageSize(pages.getPageSize());
		    dto.setTotalCount(Long.valueOf(pages.getTotal()).intValue());
		    dto.setTotalPage(Long.valueOf(pages.getPages()));
			dto.setAwardRecords(list);
		} catch (Exception e) {
			e.printStackTrace();
			dto = new AwardRecordListDto("success","10000","查询失败");
		}
		return dto;
	}

	@Override
	public AwardRecord findByOpenid(String id) {
		return awardRecordMapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public BaseDto updateAwardRecord(AwardRecord record) {
		BaseDto dto = null;
		try {
//			awardRecordMapper.updateByPrimaryKeySelective(record);
			awardRecordMapper.updateByPrimaryKey(record);
			dto = new BaseDto("success","10000");
		} catch (Exception e) {
			e.printStackTrace();
			dto = new BaseDto("fail","10001","修改失败");
		}
		return dto;
	}

	@Override
	public BaseDto deleteById(String id) {
		BaseDto dto = null;
		try {
			awardRecordMapper.deleteByPrimaryKey(Long.valueOf(id));
			dto = new BaseDto("success","10000");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			dto = new BaseDto("fail","10001","删除失败");
		}
		return dto;
	}

	@Override
	public void getEmsNo() {
		//1.获取已发货并且快递单号为空的订单
		List<AwardRecord> awardRecords = awardRecordMapper.getDeliveredRecord();
		//2.获取快递单号
		if(awardRecords != null){
			for(AwardRecord awardRecord : awardRecords){
				Long orderId = awardRecord.getOrderId();
				
				//获取邮件号码--通过订单
		    	String url = "http://120.76.25.35/qjlup/batchEmsnoByOrdernoServlet";
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				
//				String ip="180.136.146.54";
				String ip=ConfigUtil.get("server_ip");
				String reverseIp = new StringBuilder(ip).reverse().toString();
				
//				String ordernoList = "20170420001";
				String ordernoList = ConfigUtil.get("order_prefix")+String.valueOf(orderId);
				
				nvps.add(new BasicNameValuePair("validateKey",EmsUtil.getMd5String(reverseIp)));
				nvps.add(new BasicNameValuePair("ordernoList", ordernoList));
				String jsonstr;
				try {
					jsonstr = EmsUtil.post2Server_GetJsonData(url, nvps);
					net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonstr);
					
					if(jsonObject.get("status").equals("error")){
						
					}else{
						JSONArray arr = JSONArray.fromObject(jsonstr);//先转化成json数组
						//获取数组第一个json的字符串 并转化成json对象
						net.sf.json.JSONObject jObj = net.sf.json.JSONObject.fromObject(arr.get(0).toString());
						String emsNo = jObj.getString("ems_no");
						//保存邮件单号
						awardRecord.setCourierNo(emsNo);
						awardRecord.setCourierName("EMS");

						//3.保存快递单号
						awardRecordMapper.updateByPrimaryKeySelective(awardRecord);
					}
					
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public AwardRecord findAwardRecordById(String id) {
		return awardRecordMapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public BaseDto addAwardRecord(AwardRecord record) {
		BaseDto dto = null;
		try {
			awardRecordMapper.insert(record);
			dto = new BaseDto("success","10000");
		} catch (Exception e) {
			e.printStackTrace();
			dto = new BaseDto("fail","10001","领奖失败");
		}
		return dto;
	}

	@Override
	public AwardRecordVo findAwardRecordVoById(String id) {
		return awardRecordMapper.findAwardRecordVoById(Long.valueOf(id));
	}

}
