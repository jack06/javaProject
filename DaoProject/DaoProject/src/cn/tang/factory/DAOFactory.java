package cn.tang.factory;



import java.sql.Connection;

import cn.tang.dao.IEmpDAO;
import cn.tang.dao.impl.EmpDAOImpl;

public class DAOFactory {
	public static IEmpDAO getIEmpDAOInstance (Connection conn) {
		return new EmpDAOImpl((com.mysql.jdbc.Connection) conn);
	}
}
