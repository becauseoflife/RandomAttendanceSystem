package per.attendance.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkerManager extends DBConnection {
	private Connection conn = null;

	// 构造方法中实现打开数据库的连接
	public WorkerManager() {
		conn = getConnection();
	}
	
	// 对workers表插入、删除、修改操作
	public int execUpdate(String sql) throws SQLException{
		int result = 0;
		Statement statement = null;
		try{
			statement = conn.createStatement();
			result = statement.executeUpdate(sql);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally{
			if (statement != null) {
				statement.close();
				closeConnection();
			}
		}
		return result;
	}
	
	// 对worker表进行查询操作，并已List结构返回结果， 并及时关闭数据库
	public List<Workers> Qurey(String sql) throws SQLException {
		List<Workers> list = new ArrayList<Workers>();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next()){
				Workers worker = new Workers();
				worker.setWno(rs.getString("Wno"));
				worker.setWname(rs.getString("Wname"));
				worker.setWdepartment(rs.getString("Wdepartment"));
				worker.setWleader(rs.getString("Wleader"));
				worker.setWrecords(rs.getInt("Wrecords"));
				worker.setWkeeprds(rs.getInt("Wkeeprds"));
				worker.setWtotalrds(rs.getInt("Wtotalrds"));
				list.add(worker);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally{
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
			closeConnection();
		}
		return list;
	}
	
	public String getRecord(String tableName ,String Wno) throws SQLException
	{
		Statement statement = null;
		ResultSet rs = null;
		String record = "";
		String selectSql = "select * from `" + tableName + "` where Wno='" + Wno + "';";
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(selectSql);
			while(rs.next())
				record = rs.getString("Wrecord");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
			closeConnection();
		}
		return record;
	}
	
}
