package com.icloud.service.eventmanage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.github.pagehelper.PageInfo;
import com.icloud.dao.eventmanage.TeventTypeMapper;
import com.icloud.model.bms.Tadmin;
import com.icloud.model.eventmanage.TeventType;
import com.icloud.service.eventmanage.EventTypeManageService;

@Service
public class EventTypeManageServiceImpl implements EventTypeManageService {

	@Autowired
	private TeventTypeMapper eventTypeMapper;
	
	

	@Override
	public List<TeventType> selectMenuByUser(Tadmin admin) {
		return eventTypeMapper.selectMenuByUser(admin);
	}

	@Override
	public int insertSelective(TeventType record) {
		return eventTypeMapper.insertSelective(record);
	}
	@Override
	public int selectCountByName(String typeName) {
		return eventTypeMapper.selectCountByName(typeName);
	}
	
	@Override
	public int selectCountByTypeMark(String typeMark) {
		return eventTypeMapper.selectCountByTypeMark(typeMark);
	}
	@Override
	public List<TeventType> selectAllList() {
		return eventTypeMapper.selectAllList();
	}

	@Override
	public int countByParent(String id) {
		return eventTypeMapper.countByParent(id);
	}

	@Override
	public List<TeventType> selectByRole(String roleId) {
		return eventTypeMapper.selectByRole(roleId);
	}

	@Override
	public void save(TeventType t) throws Exception {
		eventTypeMapper.save(t);

	}

	@Override
	public void update(TeventType t) throws Exception {
		eventTypeMapper.update(t);
	}

	@Override
	public List<TeventType> findList(TeventType t) throws Exception {
		t.setTotalNum(eventTypeMapper.findCount(t));
		return eventTypeMapper.findForList(t);
	}

	@Override
	public Integer findCount(TeventType t) throws Exception {
		return eventTypeMapper.findCount(t);
	}

	@Override
	public void delete(String id) throws Exception {
		eventTypeMapper.deleteByKey(id);
	}

	@Override
	public TeventType findByKey(String id) throws Exception {
		return eventTypeMapper.findForObject(id);
	}


	@Override
	public PageInfo<TeventType> findByPage(PageInfo<TeventType> page,TeventType t) throws Exception {
		return null;
	}

}
