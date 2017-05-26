package cn.tang.junit;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.tang.factory.ServiceFactory;
import cn.tang.model.Emp;
import junit.framework.TestCase;

public class IEmpServiceTest extends TestCase {

	public void testInsert() {
		Emp emp =new Emp();
		emp.setEmpno(4);
		emp.setEname("aa2sd");
		emp.setJob("搬砖d");
		emp.setHiredate(new Date());
		emp.setSal(212.0);
		emp.setComm(11200.0);
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().insert(emp));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testUpdate() {
		Emp emp =new Emp();
		emp.setEmpno(4);
		emp.setEname("1111111");
		emp.setJob("3333d");
		emp.setHiredate(new Date());
		emp.setSal(21332.0);
		emp.setComm(133200.0);
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().update(emp));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testDelete() {
		Set<Integer> all=new HashSet<Integer>();
		all.add(4);
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().delete(all));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testGet() {
		try {
			//检查不为空即可
			TestCase.assertNotNull(ServiceFactory.getIEmpServiceInstance().get(8889));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testList() {
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().list().size()>0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testListSplit() {
		fail("Not yet implemented");
	}

}
