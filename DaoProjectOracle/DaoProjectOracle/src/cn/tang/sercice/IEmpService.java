package cn.tang.sercice;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.tang.model.Emp;

public interface IEmpService {
	/**
	 * 实现雇员增加操作，是有IEmpDAO接口中的如下方法
	 * 首先用IEmpDAO.findById()方法判断要增加的雇员变化是否存在
	 * 如果不存在，则使用IEmpDAO.doCreate()方法保存
	 * @param emp 
	 * @return 
	 * @throws Exception
	 */
	public boolean insert(Emp emp) throws Exception;
	
	/**
	 * 调用IEmpDAP.doUpdate()
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public boolean update(Emp emp) throws Exception;

	/**
	 * 数据的批量删除操作，需要执行如下功能：
	 * 需要判断要删除的内容是否为空
	 * 如果不为空则调用IEmpDAO.doRemove()方法
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Set<Integer> ids) throws Exception;
	
	/**
	 * 查询雇员的完整信息，
	 * @param id 查询的ID
	 * @return 查询成功返回Emp对象的形式，否则返回null
	 * @throws Exception
	 */
	public Emp get(Integer id) throws Exception;
	
	/**
	 * 查询全部信息，调用IEmpDAP.findALL()方法
	 * @return 返回list记录
	 * @throws Exception
	 */
	public List<Emp> list() throws Exception;
	
	/**
	 * 调用IEmpDAO.findAllSplit()和IEmpDAO.getAllCount()两个方法
	 * @param column
	 * @param keyWord
	 * @param currentPate
	 * @param lineSize 每页的显示长度
	 * @return  返回两个数据，所以用Map集合返回
	 * 1、key=allEmps、value=IEmpDAO.findAllSplit()返回是list<Emp>
	 * 2、key= empCount、vulue=IEmpDA  O.getAllCount()返回Integer
	 * @throws Exception
	 */
	public Map<String,Object> listSplit(String column,String keyWord,int currentPate,int lineSize) throws Exception;
}
