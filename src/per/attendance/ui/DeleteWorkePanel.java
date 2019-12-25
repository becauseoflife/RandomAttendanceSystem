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

public class DeleteWorkePanel extends JPanel {
	private WorkerUI userInterface1, userInterface2;
	private JButton firstButton1, secondButton1, firstButton2, secondButton2;
	String wnoUpdate;
	String sqlString;
	public DeleteWorkePanel()
	{
		// �޸�����
		String[] name1 = {"������Ҫɾ����Ա���Ĺ��ţ�"};
		userInterface1 = new WorkerUI(name1);
		// ��ʾҪ�޸ĵļ�¼����
		String[] name2 = {"��	 ��", "�� ��", "�� ��", "���� �쵼"};
		userInterface2 = new WorkerUI(name2);
		this.setLayout(new FlowLayout());
		// ��ֱ����
		Box box = Box.createVerticalBox();
		box.add(userInterface1);
		box.add(userInterface2);
		this.add(box);
		
		// ��ѯ��ť�Ͱ�ť����¼�
		firstButton1 = userInterface1.getDoTask1Button();
		firstButton1.setText("�鿴");
		firstButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				DisplayRecord();
			}
		});
		// �����ť�Ͱ�ť����¼�
		firstButton2 = userInterface1.getDoTask2Button();
		firstButton2.setText("���");
		firstButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				userInterface1.clearFields();
			}
		});
		// ȷ���޸İ�ť�͵���¼�
		secondButton1 = userInterface2.getDoTask1Button();
		secondButton1.setText("ȷ��ɾ��");
		secondButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				deleteRecord();
			}
		});
		//������ť�Ͱ�ť����¼�
		secondButton2 = userInterface2.getDoTask2Button();
		secondButton2.setText("����");
		secondButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				userInterface2.clearFields();
			}
		});
		setSize(400, 260);
		setVisible(true);
}
	
	
	protected void deleteRecord() {
		// TODO �Զ����ɵķ������
		String fieldValues[] = userInterface2.getFieldValues();
		// �ж���Ϣ
		if (!fieldValues[WorkerUI.WNO].equals("")) {
			try {
				String sqlString = "DELETE from `workers`" +
						" where Wno='" + wnoUpdate + "'";
				System.out.println(sqlString);
				WorkerManager workerManager = new WorkerManager();
				int result = workerManager.execUpdate(sqlString);
				if (result != 0) {
					JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
				}else {
					JOptionPane.showMessageDialog(null, "�������빤���Ƿ���ڣ�");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			JOptionPane.showMessageDialog(null, "�������빤���Ƿ���ڣ�");
		}
	}

	
	protected void DisplayRecord() {
		// TODO �Զ����ɵķ������
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
					JOptionPane.showMessageDialog(null, "�������빤���Ƿ����!");
				}
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "�����빤�ţ�");
		}
	}
}
