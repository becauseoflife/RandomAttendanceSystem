package per.attendance.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import per.attendance.mysql.WorkerManager;


public class AttendanceRecord {
	private JPanel recordJPanel;	// 显示的界面
	
	public AttendanceRecord(){
		recordJPanel = new JPanel();
	}
	
	// 显示选择查看那条记录界面
	public JPanel showRecords()
	{
		// 获取记录表名
		WorkerManager reManager = new WorkerManager();
		String tableName = "";
		try {
			tableName = reManager.getRecordTableName();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String[] itemName = tableName.split("\n");
		JComboBox<String> recoredBox = new JComboBox<String>(itemName);
		recoredBox.setMaximumRowCount(10); 				// 最大显示行数
		
		JButton okBtn = new JButton("查看");
		
		JPanel selectJPanel = new JPanel();
		selectJPanel.add(recoredBox);
		selectJPanel.add(okBtn);
		
		JPanel tableJPanel = new JPanel();
		
		// 确认按钮监听事件
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				try {
					recordJPanel.setVisible(false);
					tableJPanel.setVisible(false);
					tableJPanel.removeAll();
					showDetail((String)recoredBox.getSelectedItem(), tableJPanel);
					tableJPanel.setVisible(true);
					recordJPanel.setVisible(true);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		
		Box verticall = Box.createVerticalBox();
		verticall.add(selectJPanel);
		verticall.add(tableJPanel);
		
		recordJPanel.add(verticall);
		
		return recordJPanel;
	}
	
	// 显示记录详情界面
	public void showDetail(String tableName, JPanel tableJPanel) throws SQLException
	{
		WorkerManager selectManager = new WorkerManager();
		String selectSql = "select * from `" + tableName + "`";
		//System.out.println(selectSql);
		Object[][] data = selectManager.getRecordContent(selectSql);
		
		String[] columnNames = {"工号", "姓名", "记录"};
		
		JTable table = new JTable(data, columnNames);
        // 设置居中显示
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        // 设置不可拖动
        table.getTableHeader().setReorderingAllowed(false);
        // 设置不可编辑
        table.setEnabled(false);
         // 设置高度
        table.setRowHeight(30);
        // 设置滚动条
        JScrollPane jScrollPane = new JScrollPane(table);
        
        
		tableJPanel.add(jScrollPane);
	}
}
