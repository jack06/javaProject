package cn.tang.dbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 数据库的链接
 * @author chunyan
 *
 */

public class DatabaseConnection {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";  
	//MLDN是oracle的sid,我的是orcl
	private static final String DBURL = "jdbc:oracle:thin:@192.168.2.100:1521:orcl";  
	private static final String user = "scott";  
	private static final String password = "oracle";  
  
	private Connection conn = null;  
	
  //构造方法进行数据库连接对象的取得
    public DatabaseConnection() {  
        try {  
            Class.forName(DBDRIVER);//加载数据驱动
            this.conn = DriverManager.getConnection(DBURL, user, password);//
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    /**
     * 取得数据库连接对象
     * @return 实例化Connection对象，如果返回Null表示没有连接成功
     */
    
    public Connection getConnection(){
    	return this.conn;
    }
  /**
   * 关闭数据库
   */
    public void close() {  
        try {  
            this.conn.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
