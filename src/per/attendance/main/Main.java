package per.attendance.main;

import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import per.attendance.mysql.DBConnection;
import per.attendance.ui.MainFrame;

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

