package cn.tang.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.Connection;

import cn.tang.dao.IEmpDAO;
import cn.tang.model.Emp;

/**
 * 实现接口类--数据层 想操作数据层子类，就要在构造方法中传入Connection接口对象
 * 
 * @author chunyan
 *
 */
public class EmpDAOImpl implements IEmpDAO {

	private Connection conn; // 数据链接
	private PreparedStatement pstmt; // 数据库操作对象

	/**
	 * 实例化之类对象，同时传入一个数据库链接对象
	 * 
	 * @param conn
	 *            Connectin连接对象，如果是NULL表示数据库没有打开
	 */
	public EmpDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Emp emp) throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO t_emp(empno,ename,job,hiredate,sal,comm) VALUES (?,?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, emp.getEmpno());
		this.pstmt.setString(2, emp.getEname());
		this.pstmt.setString(3, emp.getJob());
		this.pstmt.setDate(4, new java.sql.Date(emp.getHiredate().getTime()));
		this.pstmt.setDouble(5, emp.getSal());
		this.pstmt.setDouble(6, emp.getComm());
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Emp emp) throws Exception {
		// TODO Auto-generated method stub
		String sql = "UPDATE  t_emp SET ename=?,job=?,hiredate=?,sal=?,comm=? WHERE empno=?";
		this.pstmt = this.conn.prepareStatement(sql);
		// 顺序最好安装问好的写
		this.pstmt.setString(1, emp.getEname());
		this.pstmt.setString(2, emp.getJob());
		this.pstmt.setDate(3, new java.sql.Date(emp.getHiredate().getTime()));
		this.pstmt.setDouble(4, emp.getSal());
		this.pstmt.setDouble(5, emp.getComm());
		this.pstmt.setInt(6, emp.getEmpno());
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM t_emp WHERE empno IN(");
		Iterator<Integer> iter = ids.iterator();
		while (iter.hasNext()) {
			sql.append(iter.next()).append(",");
		}
		sql.delete(sql.length() - 1, sql.length());// 删除最后的逗号
		sql.append(")");
		this.pstmt = this.conn.prepareStatement(sql.toString());

		return this.pstmt.executeUpdate() == ids.size();
	}

	@Override
	public Emp findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Emp emp = null;
		String sql = "SELECT empno,ename,job,hiredate,sal,comm FROM t_emp WHERE empno= ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getDouble(5));
			emp.setComm(rs.getDouble(6));

		}
		return emp;
	}

	@Override
	public List<Emp> finldAll() throws Exception {
		// TODO Auto-generated method stub
		List<Emp> all = new ArrayList<Emp>();
		String sql = "SELECT empno,ename,job,hiredate,sal,comm FROM t_emp ";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getDouble(5));
			emp.setComm(rs.getDouble(6));
			all.add(emp);

		}
		return all;
	}

	@Override
	public List<Emp> findAllSplit(String column, String keyWord, Integer currentPate, Integer lineSize)
			throws Exception {
		// TODO Auto-generated method stub
		List<Emp> all = new ArrayList<Emp>();
		String sql = "SELECT * FROM " + "(SELECT empno,ename,job,hiredate,sal,comm,ROWNUM rn " + " FROM t_emp WHERE "
				+column+ " LIKE ? AND ROWNUM<=? ) temp " + " WHERE temp.rn>? ";
		
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, "%" + keyWord + "%");
		this.pstmt.setInt(2, currentPate * lineSize);
		this.pstmt.setInt(3, (currentPate - 1) * lineSize);
		System.out.println(sql);
		ResultSet rs = this.pstmt.executeQuery();
		
		while (rs.next()) {
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt(1));
			emp.setEname(rs.getString(2));
			emp.setJob(rs.getString(3));
			emp.setHiredate(rs.getDate(4));
			emp.setSal(rs.getDouble(5));
			emp.setComm(rs.getDouble(6));
			all.add(emp);

		}
		return all;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("2222");
		String sql = "SELECT COUNT(*) FROM t_emp WHERE " + column + " LIKE ? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return null;
	}

}
