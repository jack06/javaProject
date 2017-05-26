package cn.tang.test;

import java.util.Date;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.tang.factory.ServiceFactory;
import cn.tang.model.Emp;

public class TestFindAll {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		List<Emp> list = ServiceFactory.getIEmpServiceInstance().list();
		Iterator<Emp> iter=list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
	}

}
