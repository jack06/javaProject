package cn.tang.dao;

import java.util.List;
import java.util.Set;

import cn.tang.model.Emp;

/**
 * 定义t_emp数据表的数据层操作
 * @author chunyan
 *
 * @param <K> 要操作数据表的主键数据类型
 * @param <V> 要操作的VO(简单类)类型
 */
public interface IDAO<K,V> {
	/**
	 * 数据的增加操作，执行insert语句
	 * @param emp 包含了增加的数据信息
	 * @return 增加成功返回t rue，否则返回false
	 * @throws Exception 如果数据库连接失败，否则出现NullPoiterException,如果是SQL语句错误，抛出SQLException
	 */
	public boolean doCreate(V emp) throws Exception ;//抛出Exception,用jdbc直接抛出SQLException
	
	/**
	 * 修改操作，执行的是update语句，修改会根据ID将所有数据进行更改
	 * @param emp 包涵修改的信息
	 * @return 成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doUpdate(V emp) throws Exception ;
	
	/**
	 * 修改操作，执行的是update语句，修改会根据ID将所有数据进行更改
	 * @param emp 包涵修改的信息
	 * @return 成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doRemove(Set<K> ids) throws Exception ;
	
	/**
	 * 根据雇员编号查询雇员的完整信息，返回结果填充到Emp类中
	 * @param id 编号
	 * @return 查询到数据以emp对象的形式返回，否则null
	 * @throws Exception
	 */
	public V findById(K id) throws Exception ;
	
	/**
	 * 查询全部数据，每行数据通过Emp类包装，然后通过List保存多个返回结果
	 * @return 如果没有数据返回长的为0（size()==0）
	 * @throws Exception
	 */
	public List<V> finldAll() throws Exception ;
	
	/**
	 * 分页进行模糊查询
	 * @param column 数据列
	 * @param keyWord 关键字
	 * @param currentPate 当前所在页
	 * @param lineSize  行数
	 * @return
	 * @throws Exception
	 */
	 
	public List<V> findAllSplit(String column, String keyWord,Integer currentPate,Integer lineSize) throws Exception ;

	/**
	 * 用count函数统计查询数据表中的符合查询要求的数据个数
	 * @param column 数据列
	 * @param keyWord 关键字
	 * @param currentPate 当前所在页
	 * @param lineSize  行数
	 * @return 返回count()的统计结果，没有则返回0
	 * @throws Exception
	 */

	public Integer getAllCount(String column, String keyWord) throws Exception;
}
