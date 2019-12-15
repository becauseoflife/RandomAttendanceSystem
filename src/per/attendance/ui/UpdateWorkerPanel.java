package per.attendance.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import per.attendance.mysql.WorkerManager;
import per.attendance.mysql.Workers;

public class UpdateWorkerPanel extends JPanel {
	private WorkerUI userInterface1, userInterface2;
	private JButton firstButton1, secondButton1, firstButton2, secondButton2;
	String wnoUpdate;
	String sqlString;
	public UpdateWorkerPanel()
	{
		// 修改条件
		String[] name1 = {"请输入需要修改的学生的工号："};
		userInterface1 = new WorkerUI(name1);
		// 显示要修改的记录界面
		String[] name2 = {"工	 号", "姓 名", "部 门", "部门 领导"};
		userInterface2 = new WorkerUI(name2);
		this.setLayout(new FlowLayout());
		// 垂直布局
		Box box = Box.createVerticalBox();
		box.add(userInterface1);
		box.add(userInterface2);
		this.add(box);
		
		// 查询按钮和按钮点击事件
		firstButton1 = userInterface1.getDoTask1Button();
		firstButton1.setText("查看");
		firstButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				DisplayRecord();
			}
		});
		// 清除按钮和按钮点击事件
		firstButton2 = userInterface1.getDoTask2Button();
		firstButton2.setText("清除");
		firstButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				userInterface1.clearFields();
			}
		});
		// 确认修改按钮和点击事件
		secondButton1 = userInterface2.getDoTask1Button();
		secondButton1.setText("确认修改");
		secondButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				UpdateRecord();
			}
		});
		//放弃按钮和按钮点击事件
		secondButton2 = userInterface2.getDoTask2Button();
		secondButton2.setText("放弃");
		secondButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				userInterface2.clearFields();
			}
		});
		setSize(400, 260);
		setVisible(true);
	}
	
	protected void DisplayRecord() {
		// TODO 自动生成的方法存根
		String fielValues1[] = userInterface1.getFieldValues();
		String fielValues2[] = new String[4];
		if (!fielValues1[WorkerUI.WNO].equals("")) {
			wnoUpdate = fielValues1[0];
			try {
				String sqlString = "select * from workers where Wno='" + fielValues1[0] + "'";
				WorkerManager workerManager = new WorkerManager();
				
				List<Workers> list = workerManager.Qurey(sqlString);
				if (list.size() != 0) {
					fielValues2[0] = list.get(0).getWno();
					fielValues2[1] = list.get(0).getWname();
					fielValues2[2] = list.get(0).getWdepartment();
					fielValues2[3] = list.get(0).getWleader();
					userInterface2.setFieldVlues(fielValues2);
				}else {
					userInterface2.clearFields();
					JOptionPane.showMessageDialog(null, "请检查输入工号是否存在!");
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "请输入工号！");
		}
	}

	protected void UpdateRecord() {
		// TODO 自动生成的方法存根
		String fieldValues[] = userInterface2.getFieldValues();
		// 判断信息
		if (!fieldValues[WorkerUI.WNO].equals("") && !fieldValues[WorkerUI.WNAME].equals("") && !fieldValues[WorkerUI.WDEPARTMENT].equals("")) {
			try {
				String sqlString = "UPDATE `workers` SET " +
						"Wno='" + fieldValues[0] + "'," +
						"Wname='" + fieldValues[1] + "'," +
						"Wdepartment='" + fieldValues[2] + "'," +
						"Wleader='" + fieldValues[3] + "'" + 
						" where Wno='" + wnoUpdate + "'";
				System.out.println(sqlString);
				WorkerManager workerManager = new WorkerManager();
				int result = workerManager.execUpdate(sqlString);
				if (result != 0) {
					JOptionPane.showMessageDialog(null, "修改成功！");
				}else {
					JOptionPane.showMessageDialog(null, "请检查信息是否输入正确！");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			JOptionPane.showMessageDialog(null, "检查输入信息是否正确！");
		}
	}
}
