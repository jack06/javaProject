package cn.tang.test;

import java.util.Date;

import cn.tang.factory.ServiceFactory;
import cn.tang.model.Emp;

public class TestEmpInsert {

	public static void main(String[] args) throws Exception {

		Emp emp =new Emp();
		emp.setEmpno(24);
		emp.setEname("aa2sd");
		emp.setJob("°á×©d");
		emp.setHiredate(new Date());
		emp.setSal(2.0);
		emp.setComm(100.0);
		System.out.println(ServiceFactory.getIEmpServiceInstance().insert(emp));
	}

}
