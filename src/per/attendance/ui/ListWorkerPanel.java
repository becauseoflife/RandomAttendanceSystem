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
			String[] columnNames = {"����", "����", "����", "�����쵼", "���ڳɼ�", "����ȱ�ڴ���", "��ȱ�ڴ���"};
			WorkerManager workerManager = new WorkerManager();
			String selectSql = "select * from `workers`";
			Object[][] allWorkers = workerManager.getAllWorkers(selectSql);
			
			JTable table = new JTable(allWorkers, columnNames);
	        // ���þ�����ʾ
	        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	        r.setHorizontalAlignment(JLabel.CENTER);
	        table.setDefaultRenderer(Object.class, r);
	        // ���ò����϶�
	        table.getTableHeader().setReorderingAllowed(false);
	        // ���ò��ɱ༭
	        table.setEnabled(false);
	         // ���ø߶�
	        table.setRowHeight(30);
	        // ���ù�����
	        JScrollPane jScrollPane = new JScrollPane(table);
	        
			this.setLayout(new BorderLayout());
			this.add(jScrollPane, BorderLayout.CENTER);
			setSize(500, 300);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
