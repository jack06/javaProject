package com.icloud.service.staffmanage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.github.pagehelper.PageInfo;
import com.icloud.dao.staffmanage.TuserroleMapper;
import com.icloud.model.bms.Tadmin;
import com.icloud.model.staffmanage.Tuserrole;
import com.icloud.service.staffmanage.StaffRoleManageService;

@Service
public class StaffRoleManageServiceImpl implements StaffRoleManageService {

	@Autowired
	private TuserroleMapper userroleMapper;
	
	

	@Override
	public List<Tuserrole> selectMenuByUser(Tadmin admin) {
		return userroleMapper.selectMenuByUser(admin);
	}

	@Override
	public int insertSelective(Tuserrole record) {
		return userroleMapper.insertSelective(record);
	}
	@Override
	public int selectCountByName(String roleName) {
		return userroleMapper.selectCountByName(roleName);
	}
	@Override
	public List<Tuserrole> selectAllList() {
		return userroleMapper.selectAllList();
	}

	@Override
	public int countByParent(String id) {
		return userroleMapper.countByParent(id);
	}

	@Override
	public List<Tuserrole> selectByRole(String roleId) {
		return userroleMapper.selectByRole(roleId);
	}

	@Override
	public void save(Tuserrole t) throws Exception {
		userroleMapper.save(t);

	}

	@Override
	public void update(Tuserrole t) throws Exception {
		userroleMapper.update(t);
	}

	@Override
	public List<Tuserrole> findList(Tuserrole t) throws Exception {
		t.setTotalNum(userroleMapper.findCount(t));
		return userroleMapper.findForList(t);
	}

	@Override
	public Integer findCount(Tuserrole t) throws Exception {
		return userroleMapper.findCount(t);
	}

	@Override
	public void delete(String id) throws Exception {
		userroleMapper.deleteByKey(id);
	}

	@Override
	public Tuserrole findByKey(String id) throws Exception {
		return userroleMapper.findForObject(id);
	}

	@Override
	public PageInfo<Tuserrole> findByPage(PageInfo<Tuserrole> page, Tuserrole t)
			throws Exception {
		return null;
	}

	@Override
	public List<Tuserrole> selectRoleList(String[] list) {
		// TODO Auto-generated method stub
		return userroleMapper.selectRoleList(list);
	}
}
