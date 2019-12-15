package per.attendance.main;

import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try{
			// 加载JDBC驱动程序
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				System.out.println("找不到驱动程序，加载驱动失败！");
				e.printStackTrace();
			}
			// 连接数据库
			String url = "jdbc:mysql://localhost:3306/spj?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";	
			String username = "root";
			String password= "";
			//try {
				//Connection conn = DriverManager.getConnection(url, username, password);
				Connection conn = new DBConnection().getConnection();
/*			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				System.out.println("数据库连接失败");
				e.printStackTrace();
			}*/
			// 执行数据库
			Statement statement = conn.createStatement();
			String sqlQuery = "select * from workers";
			
			DatabaseMetaData data  = conn.getMetaData(); 
			ResultSet tableRet = data.getTables("spj", "spj", "%",new String[]{"TABLE"}); 
			while(tableRet.next()){
				System.out.println(tableRet.getString("TABLE_NAME"));
			}
			
			
			ResultSet rs = statement.executeQuery(sqlQuery);
			while (rs.next()) {
				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t ");
				System.out.println(rs.getString(4));
			}
			// 关闭连接
			rs.close();
			statement.close();
			conn.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		JFrame tableJFrame = new JFrame("测试表格");
		 // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(new BorderLayout());

        // 表头（列名）
        Object[] columnNames = {"姓名", "语文", "数学", "英语", "总分"};

        // 表格所有行数据
        Object[][] rowData = {
                {"张三", 80, 80, 80, new Boolean(false)},
                {"John", 70, 80, 90, new Boolean(false)},
                {"Sue", 70, 70, 70, new Boolean(false)},
                {"Jane", 80, 70, 60, new Boolean(false)},
                {"Joe", 80, 70, 60, new Boolean(false)}
        };

        // 创建一个表格，指定 所有行数据 和 表头
        JTable table = new JTable(rowData, columnNames);

        // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        // 把 表格内容 添加到容器中心
        panel.add(table, BorderLayout.CENTER);

        tableJFrame.setContentPane(panel);
        tableJFrame.pack();
        tableJFrame.setLocationRelativeTo(null);
        //tableJFrame.setVisible(true);
		
        //new MainInterface();
        
        // 创建主界面
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        // 令窗体居中
        if (frameSize.height > screenSize.height) {
			frameSize = screenSize; 
		}
        if (frameSize.width > screenSize.width) {
			frameSize = screenSize;
		}
        frame.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
        frame.setVisible(true);
	}
	
	
}

