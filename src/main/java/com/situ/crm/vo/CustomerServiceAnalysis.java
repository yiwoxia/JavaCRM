package com.situ.crm.vo;

public class CustomerServiceAnalysis {
	/**
	 * 服务类型
	 */
	private String serviceType;
	/**
	 * 数量
	 */
	private int num;
	public CustomerServiceAnalysis() {
		super();
	}
	public CustomerServiceAnalysis(String serviceType, int num) {
		super();
		this.serviceType = serviceType;
		this.num = num;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "CustomerServiceAnalysis [serviceType=" + serviceType + ", num=" + num + "]";
	}
	
}
