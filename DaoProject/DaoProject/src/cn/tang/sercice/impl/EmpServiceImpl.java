package cn.tang.sercice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.tang.dbc.DatabaseConnection;
import cn.tang.factory.DAOFactory;
import cn.tang.model.Emp;
import cn.tang.sercice.IEmpService;
/**
 * ��ȡ�ñ���Ķ���ʱ�򣬿��Խ������ݿ�Ĳ���
 * @author chunyan
 */
public class EmpServiceImpl implements IEmpService {
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public boolean insert(Emp emp) throws Exception {
		// TODO Auto-generated method stub
		
		try{
			//����DAOFactory���е�getIEmpDAOInstance��������ȡ��IEmpDAO�ӿڶ���
			//�ٵ���getIEmpDAOInstance()ʱ����Ҫͨ��DatabasceConnection�����ȡConnection�ӿڶ��󣬴��ݹ�ȥ
			//����IEmpDAO�ӿ��е�findById()�����ж��������ݵĹ�Ա���Ƿ����
			
			if(DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findById(emp.getEmpno())==null){

				return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doCreate(emp);
			}
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return false;
	}

	@Override
	public boolean update(Emp emp) throws Exception {
		// TODO Auto-generated method stub
		try{
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doUpdate(emp);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("111233");
		try{
			if(ids.size()==0) //û��Ҫɾ��������
			{
				return false;
			}
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doRemove(ids);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public Emp get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		try{
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findById(id);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}	
	}

	@Override
	public List<Emp> list() throws Exception {
		// TODO Auto-generated method stub
		try{
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).finldAll();
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}


	@Override
	public Map<String, Object> listSplit(String column, String keyWord, int currentPate, int lineSize)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("allEmps", DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAllSplit(column, keyWord, currentPate, lineSize));
			map.put("empCount", DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
			return map;
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
		
	}

}
