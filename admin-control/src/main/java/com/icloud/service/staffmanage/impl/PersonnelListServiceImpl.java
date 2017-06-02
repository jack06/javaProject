package com.icloud.service.staffmanage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.dao.staffmanage.TuserMapper;
import com.icloud.model.bms.Tadmin;
import com.icloud.model.staffmanage.Tuser;
import com.icloud.service.staffmanage.PersonnelListService;

@Service
public class PersonnelListServiceImpl implements PersonnelListService{

	@Autowired
	private TuserMapper userMapper;
	
	@Override
	public List<Tuser> selectParentMenu() {
		return userMapper.selectParentMenu();
	}
	
	@Override
	public int insertSelective(Tuser record) {
		return userMapper.insertSelective(record);
	}
	@Override
	public int selectCountByName(String nick) {
		return userMapper.selectCountByName(nick);
	}
	@Override
	public List<Tuser> selectAllList() {
		return userMapper.selectAllList();
	}
	
	@Override
	public int countByParent(String id) {
		return userMapper.countByParent(id);
	}
	
	@Override
	public List<Tuser> selectByRole(String roleId) {
		return userMapper.selectByRole(roleId);
	}

	@Override
	public void save(Tuser t) throws Exception {
		userMapper.save(t);
		
	}

	@Override
	public void update(Tuser t) throws Exception {
		userMapper.update(t);
	}

	@Override
	public List<Tuser> findList(Tuser t) throws Exception {
		t.setTotalNum(userMapper.findCount(t));
		return userMapper.findForList(t);
	}

	@Override
	public Integer findCount(Tuser t) throws Exception {
		return userMapper.findCount(t);
	}

	@Override
	public void delete(String id) throws Exception {
		userMapper.deleteByKey(id);
	}

	@Override
	public Tuser findByKey(String id) throws Exception {
		return userMapper.findForObject(id);
	}

	@Override
	public PageInfo<Tuser> findByPage(PageInfo<Tuser> page, Tuser t)
			throws Exception {
		return null;
	}

	@Override
	public List<Tuser> selectMenuByUser(Tadmin admin) {
		return null;
	}
}
