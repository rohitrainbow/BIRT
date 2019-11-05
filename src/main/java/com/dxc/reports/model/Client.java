package com.dxc.reports.model;

public class Client {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Client(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + "]";
	}
}
