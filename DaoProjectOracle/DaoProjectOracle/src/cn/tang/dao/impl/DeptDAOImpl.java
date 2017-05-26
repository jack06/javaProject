package cn.tang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Set;

import cn.tang.dao.IDeptDAO;
import cn.tang.model.Dept;

public class DeptDAOImpl implements IDeptDAO {
	
	private Connection conn;
	private PreparedStatement psmt;
	public DeptDAOImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public boolean doCreate(Dept emp) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Dept emp) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dept findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> finldAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> findAllSplit(String column, String keyWord, Integer currentPate, Integer lineSize)
			throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("此方法未实现！");
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("此方法未实现！");
	}

}
