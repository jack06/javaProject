package com.icloud.dao;

import com.icloud.model.TestOrder;

public interface TestOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestOrder record);

    int insertSelective(TestOrder record);

    TestOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestOrder record);

    int updateByPrimaryKey(TestOrder record);

	TestOrder findByTxId(String txId);
}