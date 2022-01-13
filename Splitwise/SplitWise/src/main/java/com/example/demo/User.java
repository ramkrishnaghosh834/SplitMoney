package com.example.demo;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	private String username;
	private double amount;
//	private HashSet<useramount> useramt;
	@OneToMany(targetEntity=amountentity.class,cascade=CascadeType.ALL)
	@JoinColumn(name="cp_fk",referencedColumnName ="uid")
   private Collection<amountentity> amt;
	



	


	public Collection<amountentity> getAmt() {
		return amt;
	}



	public void setAmt(Collection<amountentity> amt) {
		this.amt = amt;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public int getUid() {
		return uid;
	}



	public void setUid(int uid) {
		this.uid = uid;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	@Override
	public String toString() {
		return "User [groupid=" + uid + ", username=" + username + "]";
	}



//	public HashMap<Integer, Double> getCalculation() {
//		return calculation;
//	}
//
//
//
//	public void setCalculation(HashMap<Integer, Double> calculation) {
//		this.calculation = calculation;
//	}



	

	
}
