package cn.tang.dbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * ���ݿ������
 * @author chunyan
 *
 */

public class DatabaseConnection {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";  
	//MLDN��oracle��sid,�ҵ���orcl
	private static final String DBURL = "jdbc:oracle:thin:@192.168.2.100:1521:orcl";  
	private static final String user = "scott";  
	private static final String password = "oracle";  
  
	private Connection conn = null;  
	
  //���췽���������ݿ����Ӷ����ȡ��
    public DatabaseConnection() {  
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
