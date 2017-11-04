package com.situ.crm.vo;

/**
 * 客户构成
 * @author Rocky
 *
 */
public class CustomerConstitute {
	/**
	 * 客户等级
	 */
	private String customerLevel;
	/**
	 * 客户数量
	 */
	private int customerNum;
	public CustomerConstitute() {
		super();
	}
	public CustomerConstitute(String customerLevel, int customerNum) {
		super();
		this.customerLevel = customerLevel;
		this.customerNum = customerNum;
	}
	public String getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	public int getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(int customerNum) {
		this.customerNum = customerNum;
	}
	@Override
	public String toString() {
		return "CustomerConstitute [customerLevel=" + customerLevel + ", customerNum=" + customerNum + "]";
	}
	
	
	
}
