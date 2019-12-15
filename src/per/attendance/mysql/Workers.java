package per.attendance.mysql;

public class Workers {
	private String Wno;				// 工号
	private String wname;			// 姓名
	private String Wdepartment;		// 部门
	private String Wleader;			// 部门，领导
	private int Wrecords;			// 考勤成绩
	private int Wkeeprds;			// 连续缺勤记录
	private int Wtotalrds;			// 总的缺勤记录
	
	public String getWno() {
		return Wno;
	}
	public void setWno(String wno) {
		Wno = wno;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWdepartment() {
		return Wdepartment;
	}
	public void setWdepartment(String wdepartment) {
		Wdepartment = wdepartment;
	}
	public String getWleader() {
		return Wleader;
	}
	public void setWleader(String wleader) {
		Wleader = wleader;
	}
	public int getWrecords() {
		return Wrecords;
	}
	public void setWrecords(int wrecords) {
		Wrecords = wrecords;
	}
	public int getWkeeprds() {
		return Wkeeprds;
	}
	public void setWkeeprds(int wkeeprds) {
		Wkeeprds = wkeeprds;
	}
	public int getWtotalrds() {
		return Wtotalrds;
	}
	public void setWtotalrds(int wtotalrds) {
		Wtotalrds = wtotalrds;
	}
}
