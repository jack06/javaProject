package cn.tang.dao;

import java.util.List;
import java.util.Set;

import cn.tang.model.Emp;

/**
 * ����t_emp���ݱ�����ݲ����
 * @author chunyan
 *
 * @param <K> Ҫ�������ݱ��������������
 * @param <V> Ҫ������VO(����)����
 */
public interface IDAO<K,V> {
	/**
	 * ���ݵ����Ӳ�����ִ��insert���
	 * @param emp ���������ӵ�������Ϣ
	 * @return ���ӳɹ�����t rue�����򷵻�false
	 * @throws Exception ������ݿ�����ʧ�ܣ��������NullPoiterException,�����SQL�������׳�SQLException
	 */
	public boolean doCreate(V emp) throws Exception ;//�׳�Exception,��jdbcֱ���׳�SQLException
	
	/**
	 * �޸Ĳ�����ִ�е���update��䣬�޸Ļ����ID���������ݽ��и���
	 * @param emp �����޸ĵ���Ϣ
	 * @return �ɹ�����true�����򷵻�false
	 * @throws Exception
	 */
	public boolean doUpdate(V emp) throws Exception ;
	
	/**
	 * �޸Ĳ�����ִ�е���update��䣬�޸Ļ����ID���������ݽ��и���
	 * @param emp �����޸ĵ���Ϣ
	 * @return �ɹ�����true�����򷵻�false
	 * @throws Exception
	 */
	public boolean doRemove(Set<K> ids) throws Exception ;
	
	/**
	 * ���ݹ�Ա��Ų�ѯ��Ա��������Ϣ�����ؽ����䵽Emp����
	 * @param id ���
	 * @return ��ѯ��������emp�������ʽ���أ�����null
	 * @throws Exception
	 */
	public V findById(K id) throws Exception ;
	
	/**
	 * ��ѯȫ�����ݣ�ÿ������ͨ��Emp���װ��Ȼ��ͨ��List���������ؽ��
	 * @return ���û�����ݷ��س���Ϊ0��size()==0��
	 * @throws Exception
	 */
	public List<V> finldAll() throws Exception ;
	
	/**
	 * ��ҳ����ģ����ѯ
	 * @param column ������
	 * @param keyWord �ؼ���
	 * @param currentPate ��ǰ����ҳ
	 * @param lineSize  ����
	 * @return
	 * @throws Exception
	 */
	 
	public List<V> findAllSplit(String column, String keyWord,Integer currentPate,Integer lineSize) throws Exception ;

	/**
	 * ��count����ͳ�Ʋ�ѯ���ݱ��еķ��ϲ�ѯҪ������ݸ���
	 * @param column ������
	 * @param keyWord �ؼ���
	 * @param currentPate ��ǰ����ҳ
	 * @param lineSize  ����
	 * @return ����count()��ͳ�ƽ����û���򷵻�0
	 * @throws Exception
	 */

	public Integer getAllCount(String column, String keyWord) throws Exception;
}
