package per.attendance.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import per.attendance.mysql.WorkerManager;

public class AddWorkerPanel extends JPanel {
	private JButton clearButton, writeButton;
	private WorkerUI userInterface;
	String name[] = {"工	 号", "姓 名", "部 门", "部门 领导"};
	
	public AddWorkerPanel(){
		setLayout(new BorderLayout());
		userInterface = new WorkerUI(name);
		this.add(userInterface, BorderLayout.NORTH);
		
		// 保存按钮和按钮点击事件
		writeButton = userInterface.getDoTask1Button();
		writeButton.setText("保存");
		writeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String fielValues[] = userInterface.getFieldValues();
				// 主码非空
				if (!fielValues[WorkerUI.WNO].equals("") && !fielValues[WorkerUI.WNAME].equals("") && !fielValues[WorkerUI.WDEPARTMENT].equals("")) {
					try {
						String sqlString = "INSERT INTO `workers` VALUE ('" +
								fielValues[WorkerUI.WNO] + "','" +
								fielValues[WorkerUI.WNAME] + "','" +
								fielValues[WorkerUI.WDEPARTMENT] + "','" +
								fielValues[WorkerUI.WLEADER] + "'," +
								 "0,0,0)";
						//System.out.println(sqlString);
						WorkerManager insertManager = new WorkerManager();
						int result = insertManager.execUpdate(sqlString);
						if (result != 0) {
							userInterface.clearFields();
							JOptionPane.showMessageDialog(null, "添加成功！");
						}else {
							JOptionPane.showMessageDialog(null, "请检查工号是否已存在？");
						}
					} catch (SQLException e1) {
						// TODO: handle exception
						System.out.println(e1);
						
					}
				}else {
					JOptionPane.showMessageDialog(null, "输入的信息不符合!");
				}
			}
		});
		
		// 清除按钮和按钮点击事件
		clearButton = userInterface.getDoTask2Button();
		clearButton.setText("清除");
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				userInterface.clearFields();
			}
		});
		
	}
	
}