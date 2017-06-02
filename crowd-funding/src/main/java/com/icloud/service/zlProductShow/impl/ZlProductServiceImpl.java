package com.icloud.service.zlProductShow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.icloud.bean.Page;
import com.icloud.bean.ResultMessage;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.dao.ZlProductMapper;
import com.icloud.dto.AwardRecordListDto;
import com.icloud.dto.BaseAddDto;
import com.icloud.dto.BaseDto;
import com.icloud.dto.zlProductShow.ZlProductListDto;
import com.icloud.dto.zlProductShow.ZlProductQueryDto;
import com.icloud.form.zlProductShow.ZlProductListForm;
import com.icloud.model.zlProductShow.ZlProduct;
import com.icloud.service.zlProductShow.ZlProductService;

@Service
public class ZlProductServiceImpl implements ZlProductService {

	@Autowired
	private ZlProductMapper zlProductMapper;
	
	@Override
	public ZlProductQueryDto findById(Long id) {
		ZlProductQueryDto dto = null;
		try {
			ZlProduct reord = zlProductMapper.selectByPrimaryKey(id);
			dto = new ZlProductQueryDto("success","10000",reord);
		} catch (Exception e) {
			e.printStackTrace();
			dto = new ZlProductQueryDto("fail","10001","查询失败");
		}
		return dto;
	}

	@Override
	public BaseAddDto addProduct(ZlProduct record) {
		BaseAddDto dto = null;
		try {
			zlProductMapper.insert(record);
			dto = new BaseAddDto("success","10000","");
		} catch (Exception e) {
			e.printStackTrace();
			dto = new BaseAddDto("fail","10001","新增真龙产品失败");
		}
		return dto;
	}

	@Override
	public BaseDto updateProduct(ZlProduct record) {
		BaseDto dto = null;
		try {
			zlProductMapper.updateByPrimaryKeySelective(record);
			dto = new BaseAddDto("success","10000");
		} catch (Exception e) {
			e.printStackTrace();
			dto = new BaseDto("fail","10001","修改真龙产品失败");
		}
		return dto;
	}

	@Override
	public ZlProductListDto listByPage(ZlProductListForm from) {

		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		
		com.github.pagehelper.Page  pages = PageHelper.startPage(from.getPageNum(),from.getPageSize());
		ZlProductListDto dto = null;
		List<ZlProduct> list;
		if(from != null){
			if(from.getProductName() != null && !"".equals(from.getProductName())){
				criteria.andFieldLike("PRODUCT_NAME", "%"+from.getProductName()+"%");
			}
			if(from.getCigaretteType() != null && !"".equals(from.getCigaretteType())){
//				criteria.andFieldEqualTo("TYPE", from.getCigaretteType());
				criteria.andFieldLike("TYPE", "%"+from.getCigaretteType()+"%");
			}
		}
		try {
			list = zlProductMapper.listByPage(example);
			System.out.println("list.size="+list.size());
//			criteria.andFieldEqualTo("isvalid", "1");
			dto = new ZlProductListDto("success","10000",list);
			dto.setPageNum(pages.getPageNum());
		    dto.setStartIndex(pages.getStartRow());
		    dto.setPageSize(pages.getPageSize());
		    dto.setTotalCount(Long.valueOf(pages.getTotal()).intValue());
		    dto.setTotalPage(pages.getPages());
			dto.setZlProducts(list);
		} catch (Exception e) {
			e.printStackTrace();
			dto = new ZlProductListDto("success","10000","查询失败");
		}
		return dto;
	}

	@Override
	public BaseDto deleteById(Long id) {
		BaseDto dto = null;
		try {
			zlProductMapper.deleteByPrimaryKey(id);
			dto = new BaseDto("success","10000");
		} catch (Exception e) {
			e.printStackTrace();
			dto = new BaseDto("fail","10001","删除真龙产品失败");
		}
		return dto;
	}

	@Override
	public ResultMessage listByPage4Wx(ZlProductListForm froms) {
		List<ZlProduct> list = null;
		Page<ZlProduct> page = new Page<ZlProduct>(froms.getPageNum(),froms.getPageSize());
		int totalCount;
		try {
			totalCount = zlProductMapper.countListByPage4Wx(froms);
			froms.setTotalCount(Long.valueOf(totalCount));
			if (totalCount > 0) {
				list = zlProductMapper.listByPage4Wx(froms);
				page.setTotalCount(totalCount);
				page.setRecordList(list);
			}
		} catch (Exception e) {
			return new ResultMessage(false,"查询失败！",page);
		}
		return new ResultMessage(true,"查询成功！",page);
	}

}
