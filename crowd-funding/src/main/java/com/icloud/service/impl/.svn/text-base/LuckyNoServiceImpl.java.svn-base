package com.icloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.dao.LuckyNoMapper;
import com.icloud.dto.LuckyNoListDto;
import com.icloud.model.LuckyNo;
import com.icloud.service.LuckyNoService;
@Service
public class LuckyNoServiceImpl implements  LuckyNoService{

	@Autowired
	private LuckyNoMapper luckyNoMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return luckyNoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LuckyNo record) {
		// TODO Auto-generated method stub
		return luckyNoMapper.insert(record);
	}

	@Override
	public int insertSelective(LuckyNo record) {
		// TODO Auto-generated method stub
		return luckyNoMapper.insertSelective(record);
	}

	@Override
	public LuckyNo selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return luckyNoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LuckyNo record) {
		// TODO Auto-generated method stub
		return luckyNoMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(LuckyNo record) {
		// TODO Auto-generated method stub
		return luckyNoMapper.updateByPrimaryKey(record);
	}

	/**
	 * 
	 */
	@Override
	public List<LuckyNo> selectByLuckyNo(LuckyNo luckyNo) {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(luckyNo!=null){
			if(luckyNo.getRaiseId()!=null){
				criteria.andFieldEqualTo("raiseId", luckyNo.getRaiseId());
			}
		}
		return luckyNoMapper.selectByExample(example);
	}

	@Override
	public Long selectMaxLuckyNo(Long raiseId) {
		// TODO Auto-generated method stub
		return luckyNoMapper.selectMaxLuckyNo(raiseId);
	}

	/**
	 * 通过众筹id查找中奖号码
	 */
	@Override
	public LuckyNoListDto selectByRaiseId(Long id) {
		List<LuckyNo> list = luckyNoMapper.selectByRaiseId(id);
		LuckyNoListDto dto = new LuckyNoListDto("success", "10000");
		dto.setLuckyNoList(list);
		return dto;
	}
 
}
