package cn.tang.factory;



import java.sql.Connection;

import cn.tang.dao.IDeptDAO;
import cn.tang.dao.IEmpDAO;
import cn.tang.dao.impl.DeptDAOImpl;
import cn.tang.dao.impl.EmpDAOImpl;
/**
 * 通过DAOFactory取得数据层
 * 接口过多，数据表多的时候不能这样写
 * 传递connection的接口对象
 * @author chunyan
 *
 */
public class DAOFactory {
	
	public static IEmpDAO getIEmpDAOInstance (Connection conn) {
		return new EmpDAOImpl(conn);
	}
	
	public static IDeptDAO getIDeptDAOInstance (Connection conn) {
		return new DeptDAOImpl(conn);
	}
}
