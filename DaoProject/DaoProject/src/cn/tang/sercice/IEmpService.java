package cn.tang.sercice;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.tang.model.Emp;

public interface IEmpService {
	/**
	 * ʵ�ֹ�Ա���Ӳ���������IEmpDAO�ӿ��е����·���
	 * ������IEmpDAO.findById()�����ж�Ҫ���ӵĹ�Ա�仯�Ƿ����
	 * ��������ڣ���ʹ��IEmpDAO.doCreate()��������
	 * @param emp 
	 * @return 
	 * @throws Exception
	 */
	public boolean insert(Emp emp) throws Exception;
	
	/**
	 * ����IEmpDAP.doUpdate()
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public boolean update(Emp emp) throws Exception;

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
	public Emp get(Integer id) throws Exception;
	
	/**
	 * ��ѯȫ����Ϣ������IEmpDAP.findALL()����
	 * @return ����list��¼
	 * @throws Exception
	 */
	public List<Emp> list() throws Exception;
	
	/**
	 * ����IEmpDAO.findAllSplit()��IEmpDAO.getAllCount()��������
	 * @param column
	 * @param keyWord
	 * @param currentPate
	 * @param lineSize ÿҳ����ʾ����
	 * @return  �����������ݣ�������Map���Ϸ���
	 * 1��key=allEmps��value=IEmpDAO.findAllSplit()������list<Emp>
	 * 2��key= empCount��vulue=IEmpDA  O.getAllCount()����Integer
	 * @throws Exception
	 */
	public Map<String,Object> listSplit(String column,String keyWord,int currentPate,int lineSize) throws Exception;
}
