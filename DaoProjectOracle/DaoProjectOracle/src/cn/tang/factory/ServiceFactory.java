package cn.tang.factory;

import cn.tang.sercice.IDeptService;
import cn.tang.sercice.IEmpService;
import cn.tang.sercice.impl.DeptServiceImpl;
import cn.tang.sercice.impl.EmpServiceImpl;

public class ServiceFactory {
	public static IEmpService getIEmpServiceInstance(){
		return new EmpServiceImpl();
	}
	
	public static IDeptService getIDeptpServiceInstance(){
		return new DeptServiceImpl();
	}
}
