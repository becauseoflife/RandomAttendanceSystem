package per.attendance.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection conn = null;
	private static String username = "root";
	private static String password = "";
	private static String url = "jdbc:mysql://localhost:3306/workers?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	// 获得数据库的连接
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password); 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return conn;
	}
	// 关闭数据库的连接
	public void closeConnection(){
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
}
