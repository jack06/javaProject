package cn.tang.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.tang.factory.ServiceFactory;
import cn.tang.model.Emp;

public class TestEmpFindById {

	public static void main(String[] args) throws Exception {
		Set<Integer> all=new HashSet<Integer>();
		all.add(189);
		ServiceFactory.getIEmpServiceInstance().delete(all);
	}

}
