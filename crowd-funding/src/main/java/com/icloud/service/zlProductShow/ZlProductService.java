package com.icloud.service.zlProductShow;

import com.icloud.bean.ResultMessage;
import com.icloud.dto.BaseAddDto;
import com.icloud.dto.BaseDto;
import com.icloud.dto.zlProductShow.ZlProductListDto;
import com.icloud.dto.zlProductShow.ZlProductQueryDto;
import com.icloud.form.zlProductShow.ZlProductListForm;
import com.icloud.model.zlProductShow.ZlProduct;

/**
 * 真龙产品展示Service层
 * @author chencl
 * @date 2017-05-11
 */
public interface ZlProductService {
	
	/**
	 * 通过id查找
	 * @param id
	 * @return
	 */
	ZlProductQueryDto findById(Long id);
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	BaseAddDto addProduct(ZlProduct record);
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	BaseDto updateProduct(ZlProduct record);
	/**
	 * 分页
	 * @return
	 */
	ZlProductListDto listByPage(ZlProductListForm froms);
	
	BaseDto deleteById(Long id);
	
	ResultMessage listByPage4Wx(ZlProductListForm froms);
}
