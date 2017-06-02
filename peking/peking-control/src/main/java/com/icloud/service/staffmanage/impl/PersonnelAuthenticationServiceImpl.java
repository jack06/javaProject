package com.icloud.service.staffmanage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.dao.staffmanage.TuserextMapper;
import com.icloud.model.bms.Tadmin;
import com.icloud.model.staffmanage.Tuserext;
import com.icloud.service.staffmanage.PersonnelAuthenticationService;

@Service
public class PersonnelAuthenticationServiceImpl implements PersonnelAuthenticationService{

	@Autowired
	private TuserextMapper userextMapper;
	
	@Override
	public List<Tuserext> selectParentMenu() {
		return userextMapper.selectParentMenu();
	}
	
	@Override
	public int insertSelective(Tuserext record) {
		return userextMapper.insertSelective(record);
	}
	@Override
	public int selectCountByName(String realName) {
		return userextMapper.selectCountByName(realName);
	}
	@Override
	public List<Tuserext> selectAllList() {
		return userextMapper.selectAllList();
	}
	
	@Override
	public int countByParent(String id) {
		return userextMapper.countByParent(id);
	}
	
	@Override
	public List<Tuserext> selectByRole(String roleId) {
		return userextMapper.selectByRole(roleId);
	}

	@Override
	public void save(Tuserext t) throws Exception {
		userextMapper.save(t);
		
	}

	@Override
	public void update(Tuserext t) throws Exception {
		userextMapper.update(t);
	}

	@Override
	public List<Tuserext> findList(Tuserext t) throws Exception {
		t.setTotalNum(userextMapper.findCount(t));
		return userextMapper.findForList(t);
	}

	@Override
	public Integer findCount(Tuserext t) throws Exception {
		return userextMapper.findCount(t);
	}

	@Override
	public void delete(String id) throws Exception {
		userextMapper.deleteByKey(id);
	}

	@Override
	public Tuserext findByKey(String id) throws Exception {
		return userextMapper.findForObject(id);
	}

	@Override
	public PageInfo<Tuserext> findByPage(PageInfo<Tuserext> page, Tuserext t)
			throws Exception {
		return null;
	}

	@Override
	public List<Tuserext> selectMenuByUser(Tadmin admin) {
		return null;
	}
}
