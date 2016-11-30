package com.cg.model;

public class Customer {
	
	private int acc_no;
	private int balance;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int acc_no, int balance) {
		super();
		this.acc_no = acc_no;
		this.balance = balance;
	}

	public int getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(int acc_no) {
		this.acc_no = acc_no;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer [acc_no=" + acc_no + ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acc_no;
		result = prime * result + balance;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (acc_no != other.acc_no)
			return false;
		if (balance != other.balance)
			return false;
		return true;
	}
	
	
	
}
