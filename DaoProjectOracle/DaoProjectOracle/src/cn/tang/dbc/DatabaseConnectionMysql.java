package cn.tang.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * ���ݿ������
 * @author chunyan
 *
 */

public class DatabaseConnectionMysql {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";  
	private static final String DBURL = "jdbc:mysql://localhost:3306/students_manage";  
	private static final String user = "root";  
	private static final String password = "root";  
  
	private Connection conn = null;  
	
  //���췽���������ݿ����Ӷ����ȡ��
    public DatabaseConnectionMysql() {  
        try {  
            Class.forName(DBDRIVER);//������������
            this.conn = DriverManager.getConnection(DBURL, user, password);//
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    /**
     * ȡ�����ݿ����Ӷ���
     * @return ʵ����Connection�����������Null��ʾû�����ӳɹ�
     */
    
    public Connection getConnection(){
    	return this.conn;
    }
  /**
   * �ر����ݿ�
   */
    public void close() {  
        try {  
            this.conn.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
