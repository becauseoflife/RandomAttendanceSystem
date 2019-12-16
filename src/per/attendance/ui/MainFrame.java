package per.attendance.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener {
	//初始化主菜单
	JMenuBar jMenuBar= new JMenuBar();
	JMenu JMenuFile = new JMenu("菜单");
	JMenuItem jMenuFileExit = new JMenuItem("退出系统");
	JMenu jMenuListName = new JMenu("员工考勤");
	JMenuItem jMenuItemListName = new JMenuItem("生成考勤名单");
	JMenuItem jMenuItemCheckRecords = new JMenuItem("查看考勤记录");
	JMenu jMenuManageWorkers = new JMenu("员工管理");
	JMenuItem jMenuItemAddWorker = new JMenuItem("添加员工");
	JMenuItem jMenuItemDeleteWorker = new JMenuItem("删除员工");
	JMenuItem jMenuItemUpdateWorker = new JMenuItem("修改员工信息");
	JMenu JMenuSelectWorkers = new JMenu("员工查询");
	JMenuItem jMenuItemAllWokers = new JMenuItem("浏览全部员工");
	//构造方法，界面初始化
	public MainFrame(){
		//创建内容面板，设置其布局
		JPanel contentPanel = (JPanel) getContentPane();
		contentPanel.setLayout( new BorderLayout());
		//框架的大小和其标题
		setSize(new Dimension(800,560));
		setTitle("随机考勤系统");
		//添加菜单条
		setJMenuBar(jMenuBar);
		// 添加菜单组件到菜单条
		jMenuBar.add(JMenuFile);
		jMenuBar.add(jMenuListName);
		jMenuBar.add(jMenuManageWorkers);
		jMenuBar.add(JMenuSelectWorkers);
		jMenuBar.add(jMenuFileExit);
		// 添加菜单项到组件到菜单组件
		JMenuFile.add(jMenuFileExit);
		jMenuListName.add(jMenuItemListName);
		jMenuListName.add(jMenuItemCheckRecords);
		jMenuManageWorkers.add(jMenuItemAddWorker);
		jMenuManageWorkers.add(jMenuItemDeleteWorker);
		jMenuManageWorkers.add(jMenuItemUpdateWorker);
		JMenuSelectWorkers.add(jMenuItemAllWokers);
		// 给菜单添加事件监听器
		jMenuFileExit.addActionListener(this);			// 退出
		jMenuItemListName.addActionListener(this);		// 随机生成点名单
		jMenuItemCheckRecords.addActionListener(this);	// 查看点名记录
		jMenuItemAddWorker.addActionListener(this);		// 添加员工
		jMenuItemDeleteWorker.addActionListener(this);	// 删除员工
		jMenuItemUpdateWorker.addActionListener(this);	// 修改员工
		jMenuItemAllWokers.addActionListener(this);		// 浏览全部员工
	}
			

	// 菜单事件的处理方法
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		// 退出
		if (e.getSource() == jMenuFileExit) {
			System.exit(0);
		}
		// 随机生成名单
		if (e.getSource() == jMenuItemListName) {
			this.remove(this.getContentPane());
			RandomListName randomListName = new RandomListName();
			this.setContentPane(randomListName.getWokersCounts());
			this.setVisible(true);
		}
		// 查看点名记录
		if (e.getSource() == jMenuItemCheckRecords) {
			this.remove(this.getContentPane());
			AttendanceRecord attendanceRecord = new AttendanceRecord();
			this.setContentPane(attendanceRecord.showRecords());
			this.setVisible(true);
		}
		// 添加员工
		if (e.getSource() == jMenuItemAddWorker) {
			AddWorkerPanel add = new AddWorkerPanel();
			this.remove(this.getContentPane());
			this.setContentPane(add);
			this.setVisible(true);
		}
		// 删除员工
		if (e.getSource() == jMenuItemDeleteWorker) {
			DeleteWorkePanel delete = new DeleteWorkePanel();
			this.remove(this.getContentPane());
			this.setContentPane(delete);
			this.setVisible(true);
		}
		// 修改员工
		if (e.getSource() == jMenuItemUpdateWorker) {
			UpdateWorkerPanel update = new UpdateWorkerPanel();
			this.remove(this.getContentPane());
			this.setContentPane(update);
			this.setVisible(true);
		}
		// 浏览全部员工
		if (e.getSource() == jMenuItemAllWokers) {
			ListWorkerPanel allWorker = new ListWorkerPanel();
			this.remove(this.getContentPane());
			this.setContentPane(allWorker);
			this.setVisible(true);
		}
	}
	
	
}





