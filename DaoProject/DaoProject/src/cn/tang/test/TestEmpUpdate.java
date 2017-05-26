package cn.tang.test;

import java.util.Date;

import cn.tang.factory.ServiceFactory;
import cn.tang.model.Emp;

public class TestEmpUpdate {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Emp emp =new Emp();
		emp.setEmpno(189);
		emp.setEname("Œ§∑„");
		emp.setJob("ºº ı‘±");
		emp.setHiredate(new Date());
		emp.setSal(4.0);
		emp.setComm(120.0);
		System.out.println(ServiceFactory.getIEmpServiceInstance().update(emp));
	}

}
