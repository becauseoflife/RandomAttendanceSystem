package per.attendance.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.WebServiceException;

import per.attendance.mysql.WorkerManager;
import per.attendance.mysql.Workers;

public class RandomListName{
	private JPanel listNameJPanel;		// 存放生成的UI界面
	private List<Workers> workersList;	// 存放员工列表
	private String[] columnName;		// 点名表上的列信息

	public RandomListName(){ 
		listNameJPanel = new JPanel();
		columnName = new String[]{"工号", "姓名", "部门", "考勤"};
		try {
			WorkerManager workerManager = new WorkerManager();
			String sql = "select Wno, Wname, Wdepartment, Wleader, Wrecords, Wkeeprds, Wtotalrds from workers";
			workersList = workerManager.Qurey(sql);
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	// 获得考勤名单的规模
	public JPanel getWokersCounts()
	{  
		JPanel contentJPanel = new JPanel();
		
		// 显示员工的数量
		JPanel tipTextJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel tipWorkersNumber = new JLabel("员工数量为: ");
		JTextArea workerNumber = new JTextArea(1, 16);	
		workerNumber.setText("\t"+workersList.size());
		workerNumber.setEditable(false);
		tipTextJPanel.add(tipWorkersNumber);
		tipTextJPanel.add(workerNumber);
		
		// 名单规模输入框
		JPanel inputJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel tipText = new JLabel("请输入随机生成名单的规模:");
		JTextField inputNumber = new JTextField(16);
		inputJPanel.add(tipText);
		inputJPanel.add(inputNumber);
		
		// 按钮
		JPanel buttonJPanel = new JPanel(new GridLayout(1, 2, 20, 10));
		JButton okBtn = new JButton("确认");
		JButton clearBtn = new JButton("清除");
		buttonJPanel.add(okBtn);
		buttonJPanel.add(clearBtn);
		
		// 垂直布局
		Box verticall = Box.createVerticalBox();
		verticall.add(Box.createVerticalStrut(80));
		verticall.add(tipTextJPanel);
		verticall.add(inputJPanel);
		verticall.add(Box.createVerticalStrut(50));
		verticall.add(buttonJPanel);
		contentJPanel.add(verticall);
		
		listNameJPanel.setLayout(new BorderLayout());
		listNameJPanel.add(contentJPanel, BorderLayout.CENTER);
		//listNameJPanel.add(buttonJPanel, BorderLayout.SOUTH);
		
		// 按钮监听事件
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				inputNumber.setText("");
			}
		});
		
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if (inputNumber.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入名单规模！！");
					return;
				}
				int randomCounts = Integer.valueOf(inputNumber.getText());
				if (workersList.size() < randomCounts || randomCounts == 0 ) {
					JOptionPane.showMessageDialog(null, "超过员工数量，你会算数吗？");
					inputNumber.setText("");
					return;
				}
				listNameJPanel.setVisible(false);		// 输入界面隐藏
				listNameJPanel.removeAll();				// 将输入界面内容删除
				
				ListNameInterface(randomCounts);		// 绘制考勤界面
				listNameJPanel.setVisible(true);		// 显示考勤界面
			}

		});
		
		return listNameJPanel;
	}
	
	// 显示点名单
	private void ListNameInterface(int size) {
		// TODO 自动生成的方法存根
		// 创建记录表的名称
		Date nowData = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tableName = dateFormat.format(nowData);
		// 显示的数据
		Object[][] workers = new Object[size][columnName.length];
		// 产生不重复的随机数
		Random random = new Random();
        HashSet<Integer> hs = new HashSet<Integer>();
        for(;;){
            int temp = random.nextInt(workersList.size());
            hs.add(temp);
            if(hs.size() == size) break;
        }
        // 获取随机名单
        int rowI=0;
		for(int index:hs){
        	workers[rowI][0] = workersList.get(index).getWno();
        	workers[rowI][1] = workersList.get(index).getWname();
        	workers[rowI][2] = workersList.get(index).getWdepartment();
        	workers[rowI][3] = new Boolean(false);
        	rowI++;
		}


        MyTable model = new MyTable(columnName, workers);
        JTable table = new JTable(model);
        // 设置居中显示
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        // 设置不可拖动
        table.getTableHeader().setReorderingAllowed(false);
        // 设置滚动条
        table.setRowHeight(30);
        JScrollPane jScrollPane = new JScrollPane(table);
        
        // 设置按钮
        JPanel btnJPanel = new JPanel();
        JButton okBtn = new JButton("完成");
        JButton cancelBtn = new JButton("取消");
        btnJPanel.add(okBtn);
        btnJPanel.add(cancelBtn);
        
        // 提交监听事件
        okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int pass = JOptionPane.showConfirmDialog(null, "确认提交？", "提交", JOptionPane.YES_NO_OPTION);
				if (pass==JOptionPane.YES_OPTION) {
					try {
						saveRecords(tableName, table);		// 保存考勤记录
						JOptionPane.showMessageDialog(null, "提交成功！");
						listNameJPanel.setVisible(false);	// 界面隐藏
						listNameJPanel.removeAll();			// 删除界面
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			}
		});
        // 取消监听事件
        cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				listNameJPanel.setVisible(false);		// 把点名表隐藏
				listNameJPanel.removeAll();				// 把点名表界面删除
				getWokersCounts();						// 绘制输入规模界面
				listNameJPanel.setVisible(true);		// 显示输入规模界面
			}
		});
        
        
        Box verticall = Box.createVerticalBox();
        verticall.add(jScrollPane);
        verticall.add(btnJPanel);
		listNameJPanel.add(verticall);

		}
	
	// 保存考勤记录
	protected void saveRecords(String tableName, JTable table) throws SQLException {
		// TODO 自动生成的方法存根
		System.out.println(table.getValueAt(0, columnName.length-1));
		// 创建记录表
		String tabelSql = "CREATE TABLE `" + tableName +"` ("
				+ "`Wno` CHAR(8) NOT NULL,"
				+ "`Wname` CHAR(5) NOT NULL,"
				+ "`Wrecord` CHAR(5) NOT NULL,"
				+ "PRIMARY KEY (`Wno`)"
				+ ")";
		WorkerManager cTableManager = new WorkerManager();
		cTableManager.execUpdate(tabelSql);
		// 将所有人写入
		//StringBuffer insertSql = new StringBuffer();
		for (int i = 0; i < workersList.size(); i++) {
			String insertSql =
					"INSERT INTO `" + tableName + "` VALUE("
					+ "'" + workersList.get(i).getWno() + "',"
					+ "'" + workersList.get(i).getWname() + "',"
					+ "'通过'" + ");";
			WorkerManager insertManager = new WorkerManager();
			insertManager.execUpdate(insertSql);
		}
		// 记录缺勤的人
		for (int row = 0; row < table.getRowCount(); row++) {
			if(!(boolean) table.getValueAt(row, columnName.length-1)){
				// 更新记录表格
				String updateSql = "update `"+ tableName + "` set Wrecord='缺勤' where Wno='" + table.getValueAt(row, 0) + "'";
				//System.out.println(updateSql);
				WorkerManager updateManager = new WorkerManager();
				updateManager.execUpdate(updateSql);
			}
		}
		
		// 更新考勤成绩
		for(int i=0; i<workersList.size(); i++){
			// 查看记录表单中是否通过
			WorkerManager selectManager = new WorkerManager();
			String result = selectManager.getRecord(tableName, workersList.get(i).getWno());
			//System.out.println(result);
			// 考勤通过的人
			if(result.equals("通过")){
				// 小于5次考勤不通过，考勤成绩 + 1
				if(workersList.get(i).getWtotalrds() < 5)
					workersList.get(i).setWrecords(workersList.get(i).getWrecords() + 1); // 成绩+1
				// 考勤通过 将连续考勤记录置0
				workersList.get(i).setWkeeprds(0);
			}
			// 考勤不通过的人
			if (result.equals("缺勤")) {
				// 总缺勤记录 + 1
				workersList.get(i).setWtotalrds(workersList.get(i).getWtotalrds() + 1); 
				// 累计5次考勤不通过，考勤成绩置零
				if(workersList.get(i).getWtotalrds() >= 5)		
					workersList.get(i).setWrecords(0);
				// 连续缺勤记录 + 1
				workersList.get(i).setWkeeprds(workersList.get(i).getWkeeprds() + 1); 
				// 连续缺勤记录超过3次，考勤成绩置0
				if (workersList.get(i).getWkeeprds() >= 3)
					workersList.get(i).setWrecords(0);
			}
		}
		
		// 更新数据库
		for(int i=0; i<workersList.size(); i++)
		{
			String updateWorkerSql = "update `workers` set" + 
					" Wrecords=" + workersList.get(i).getWrecords() + "," +
					" Wkeeprds=" + workersList.get(i).getWkeeprds() + "," +
					" Wtotalrds=" + workersList.get(i).getWtotalrds()+ 
					" where Wno='" + workersList.get(i).getWno() + "'";
			//System.out.println(updateWorkerSql);
			WorkerManager updateManager = new WorkerManager();
			updateManager.execUpdate(updateWorkerSql);
		}
		
	}

	// 表格适配器
	private class MyTable extends AbstractTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		// 标题栏
		String[] columnNames;
		Object[][] workers;
		
		public MyTable(String[] columnName, Object[][] data) {
			// TODO 自动生成的构造函数存根
			this.columnNames = columnName;
			this.workers = data;
		}
		
		@Override
		public int getRowCount() {
			// TODO 自动生成的方法存根
			return workers.length;
		}

		@Override
		public int getColumnCount() {
			// TODO 自动生成的方法存根
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO 自动生成的方法存根
			return workers[rowIndex][columnIndex];
		}

		@Override
		public String getColumnName(int column) {
			// TODO 自动生成的方法存根
			return columnNames[column];
		}

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO 自动生成的方法存根
			return getValueAt(0, columnIndex).getClass();
		}
		
        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO 自动生成的方法存根
            //Note that the data/cell address is constant,
            //no matter where the cell appears on screen.
			if (columnIndex == columnNames.length-1)
				return true;
			else
				return false;

		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO 自动生成的方法存根
			workers[rowIndex][columnIndex] = aValue;
			fireTableRowsUpdated(0, rowIndex);
		}
		
	}
	
}
