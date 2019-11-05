package com.dxc.reports.model;

public class Report {

	private String repNames;

	public String getRepNames() {
		return repNames;
	}

	public void setRepNames(String repNames) {
		this.repNames = repNames;
	}

	public Report(String repNames) {
		super();
		this.repNames = repNames;
	}

	@Override
	public String toString() {
		return "Report [repNames=" + repNames + "]";
	}

}
