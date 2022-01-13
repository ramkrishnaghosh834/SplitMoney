package com.example.demo;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="groupname")
public class Groupname {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupid;
     private String grpname;
	
@Override
	public String toString() {
		return "Groupname [groupid=" + groupid + ", user=" + user + "]";
	}
		public String getGrpname() {
		return grpname;
	}
	public void setGrpname(String groupname) {
		this.grpname = groupname;
	}
	@OneToMany(targetEntity=User.class,cascade=CascadeType.ALL)
	@JoinColumn(name="cp_fk",referencedColumnName ="groupid")
	private Collection<User> user;
	
	
	public int getgroupId() {
		return groupid;
	}
	public void setgroupId(int groupid) {
		this.groupid = groupid;
	}
	
	public Collection<User> getUser() {
		return user;
	}
	public void setUser(Collection<User> user) {
		this.user = user;
	}
}
