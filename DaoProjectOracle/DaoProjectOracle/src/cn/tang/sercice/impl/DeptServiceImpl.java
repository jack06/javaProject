package cn.tang.sercice.impl;

import java.util.List;
import java.util.Set;

import cn.tang.dbc.DatabaseConnection;
import cn.tang.model.Dept;
import cn.tang.sercice.IDeptService;

public class DeptServiceImpl implements IDeptService{
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public boolean insert(Dept dept) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Dept dept) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dept get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
