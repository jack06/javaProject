package cn.tang.sercice;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.tang.model.Dept;

public interface IDeptService {
	/**
	 * ʵ�ֹ�Ա���Ӳ���������IDeptDAO�ӿ��е����·���
	 * ������IDeptDAO.findById()�����ж�Ҫ���ӵĹ�Ա�仯�Ƿ����
	 * ��������ڣ���ʹ��IDeptDAO.doCreate()��������
	 * @param emp 
	 * @return 
	 * @throws Exception
	 */
	public boolean insert(Dept dept) throws Exception;
	
	/**
	 * ����IEmpDAP.doUpdate()
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public boolean update(Dept dept) throws Exception;

	/**
	 * ���ݵ�����ɾ����������Ҫִ�����¹��ܣ�
	 * ��Ҫ�ж�Ҫɾ���������Ƿ�Ϊ��
	 * �����Ϊ�������IEmpDAO.doRemove()����
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Set<Integer> ids) throws Exception;
	
	/**
	 * ��ѯ��Ա��������Ϣ��
	 * @param id ��ѯ��ID
	 * @return ��ѯ�ɹ�����Emp�������ʽ�����򷵻�null
	 * @throws Exception
	 */
	public Dept get(Integer id) throws Exception;
	
	/**
	 * ��ѯȫ����Ϣ������IEmpDAP.findALL()����
	 * @return ����list��¼
	 * @throws Exception
	 */
	public List<Dept> list() throws Exception;
	
}
