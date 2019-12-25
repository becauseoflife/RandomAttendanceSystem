package per.attendance.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import per.attendance.mysql.WorkerManager;

public class ListWorkerPanel extends JPanel {
	
	public ListWorkerPanel(){
		try {
			String[] columnNames = {"工号", "姓名", "部门", "部门领导", "考勤成绩", "连续缺勤次数", "总缺勤次数"};
			WorkerManager workerManager = new WorkerManager();
			String selectSql = "select * from `workers`";
			Object[][] allWorkers = workerManager.getAllWorkers(selectSql);
			
			JTable table = new JTable(allWorkers, columnNames);
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
	        
			this.setLayout(new BorderLayout());
			this.add(jScrollPane, BorderLayout.CENTER);
			setSize(500, 300);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
