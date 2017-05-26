package cn.tang.test;

import java.util.Date;

import cn.tang.factory.ServiceFactory;
import cn.tang.model.Emp;

public class TestEmpInsert {

	public static void main(String[] args) throws Exception {

		Emp emp =new Emp();
		emp.setEmpno(131);
		emp.setEname("3aŒ§∑„");
		emp.setJob("ºº ı‘±");
		emp.setHiredate(new Date());
		emp.setSal(5.0);
		emp.setComm(220.0);
		System.out.println(ServiceFactory.getIEmpServiceInstance().insert(emp));
	}

}
