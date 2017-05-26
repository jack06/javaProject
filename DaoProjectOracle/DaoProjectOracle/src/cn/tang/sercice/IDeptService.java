package cn.tang.sercice;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.tang.model.Dept;

public interface IDeptService {
	/**
	 * 实现雇员增加操作，是有IDeptDAO接口中的如下方法
	 * 首先用IDeptDAO.findById()方法判断要增加的雇员变化是否存在
	 * 如果不存在，则使用IDeptDAO.doCreate()方法保存
	 * @param emp 
	 * @return 
	 * @throws Exception
	 */
	public boolean insert(Dept dept) throws Exception;
	
	/**
	 * 调用IEmpDAP.doUpdate()
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public boolean update(Dept dept) throws Exception;

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
	public Dept get(Integer id) throws Exception;
	
	/**
	 * 查询全部信息，调用IEmpDAP.findALL()方法
	 * @return 返回list记录
	 * @throws Exception
	 */
	public List<Dept> list() throws Exception;
	
}
