package cn.tang.factory;

import cn.tang.sercice.IEmpService;
import cn.tang.sercice.impl.EmpServiceImpl;

public class ServiceFactory {
	public static IEmpService getIEmpServiceInstance(){
		return new EmpServiceImpl();
	}
}
