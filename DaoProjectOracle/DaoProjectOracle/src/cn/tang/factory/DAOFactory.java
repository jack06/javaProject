package cn.tang.factory;



import java.sql.Connection;

import cn.tang.dao.IDeptDAO;
import cn.tang.dao.IEmpDAO;
import cn.tang.dao.impl.DeptDAOImpl;
import cn.tang.dao.impl.EmpDAOImpl;
/**
 * ͨ��DAOFactoryȡ�����ݲ�
 * �ӿڹ��࣬���ݱ���ʱ��������д
 * ����connection�Ľӿڶ���
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
