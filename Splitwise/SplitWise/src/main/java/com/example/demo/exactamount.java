package com.example.demo;

public class exactamount {

	private String username;
	private double amount;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "exactamount [username=" + username + ", amount=" + amount + "]";
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
