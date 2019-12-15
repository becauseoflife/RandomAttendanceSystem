package per.attendance.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.naming.event.NamespaceChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class WorkerUI extends JPanel {
	protected JLabel labels[];
	protected JTextField fields[];
	protected JButton doTask1,doTask2;
	protected JPanel innerPanelCenter,innerPanelSouth;
	protected int size;
	public static final int WNO = 0 ,WNAME = 1 ,WDEPARTMENT = 2,
			WLEADER = 3;
	public WorkerUI(String arrayString[]){
		size = arrayString.length;
		labels = new JLabel[size];
		fields = new JTextField[size];
		// 创建标签
		for (int count =0; count<labels.length;count++)
			labels[count] = new JLabel(arrayString[count]);
		// 创建输入框
		for(int count = 0; count<fields.length;count++)
			fields[count] = new JTextField();
		// 标签和输入框的容器
		innerPanelCenter = new JPanel();
		innerPanelCenter.setLayout(new GridLayout(size,2));
		for (int count = 0; count<size;count++){
			innerPanelCenter.add(labels[count]);
			innerPanelCenter.add(fields[count]);
		}
		// 创建两个按钮
		doTask1 = new JButton(); doTask2 = new JButton();
		innerPanelSouth = new JPanel();
		innerPanelSouth.add(doTask1);
		innerPanelSouth.add(doTask2);
		// 标签、输入框、按钮位置
		setLayout(new BorderLayout());
		add(innerPanelCenter,BorderLayout.CENTER);
		add(innerPanelSouth,BorderLayout.SOUTH);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		validate();
	}
		
		public JButton getDoTask1Button(){
			return doTask1;
		}
		
		public JButton getDoTask2Button(){
			return doTask2;
		}
		
		public JTextField[] getFields(){
			return fields;
		}
		// 清空输入框的内容
		public void clearFields(){
			for(int count = 0;count<size; count++)
				fields[count].setText("");
		}
		// 设置输入框的内容
		public void setFieldVlues(String strings[])throws IllegalArgumentException{
			if (strings.length != size)
				throw new IllegalArgumentException("There must be"+size+"String in the array");
			for (int count = 0; count<size; count++)
				fields[count].setText(strings[count]);
		}
		// 获得输入框的内容
		public String[] getFieldValues()
		{
			String[] values = new String[size];
			for(int count=0;count<size; count++)
				values[count] = fields[count].getText();
			return values;
		}
}
