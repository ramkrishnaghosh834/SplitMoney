package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainer {

	

	@Autowired
	public Grouprepo g;
	 

	
	@Autowired
	private service ser;
	
	@RequestMapping("/ramkrishna")
	public void name() {
		System.out.println("kj");
	}
	
		@PostMapping("/NewUser")
		 public  void newuser(@RequestBody User user,@RequestParam("groupcode") String  groupc) {
			
			System.out.println("User");
			
			System.out.println(user.toString());
			String UserName=user.getUsername();
			int groupcode=Integer.parseInt(groupc);
			
		
			System.out.println(UserName+" "+groupcode);

			ser.save(user,groupcode);
			
		}
		
		@PostMapping("/NewGroup")
		public void newgroup(@RequestBody Groupname usr) {
			ser.savegroup(usr);

				
		}
		@PostMapping("/AddExpenses/Equal")
		public void addexp(@RequestParam("amount") String amount,@RequestParam("Groupid")String Grpid,
				@RequestParam("uid") String u) {
			int amt=Integer.parseInt(amount);
			int groupid=Integer.parseInt(Grpid);
			int uid=Integer.parseInt(u);
			ser.distribute(groupid,amt,uid);
			
		}
		@PostMapping("/Borrow")
		public ArrayList<ArrayList<String>> borrow(@RequestParam("uid")String id,@RequestParam("groupcode") String groupcode) {
			int uid=Integer.parseInt(id);
			
					ArrayList<ArrayList<String>>s=new ArrayList<ArrayList<String>>(ser.borrow(uid,Integer.parseInt(groupcode)));

			return s;
			
		}
		@PostMapping("/AddExpenses/Exact")
		public void addexact(@RequestBody exactamount exact,@RequestParam("uid") String uid,@RequestParam("Groupcode") String groupcode) {
			ser.exactdistribute(exact,Integer.parseInt(uid),Integer.parseInt(groupcode));
		}
		
		@PostMapping("/AddExpenses/Percentage")
		public void addpercentage(@RequestBody percentagehelper percent,@RequestParam("amount") String amount,
				@RequestParam("Groupid") String groupid,@RequestParam("uid")String uid) {
			ser.percentdistribute(percent,Integer.parseInt(uid),Integer.parseInt(groupid),Integer.parseInt(amount));
		}
	
}
