package cn.tang.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Dept implements Serializable {
	private Integer deptno;
	private String dname;
	private String log;
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", log=" + log + "]";
	}
	
	
}
