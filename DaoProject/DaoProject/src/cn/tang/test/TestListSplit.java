package cn.tang.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.tang.factory.ServiceFactory;
import cn.tang.model.Emp;

public class TestListSplit {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map=ServiceFactory.getIEmpServiceInstance().listSplit("ename", "a", 1, 5);
		List<Emp> allEmps = (List<Emp>) map.get("allEmps");
		Integer empCount=(Integer) map.get("empCount");
		System.out.println("符合查询的数据量："+empCount);
		Iterator<Emp> iter=allEmps.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}

}
