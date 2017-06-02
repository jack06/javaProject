package com.icloud.dao;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.form.zlProductShow.ZlProductListForm;
import com.icloud.model.zlProductShow.ZlProduct;

public interface ZlProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZlProduct record);

    int insertSelective(ZlProduct record);

    ZlProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZlProduct record);

    int updateByPrimaryKey(ZlProduct record);
    
    List<ZlProduct> listByPage(QueryBuilder example);
    
    int countListByPage4Wx(ZlProductListForm froms);
    
    List<ZlProduct> listByPage4Wx(ZlProductListForm froms);
}