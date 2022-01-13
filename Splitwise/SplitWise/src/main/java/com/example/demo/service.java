package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class service {

    @Autowired
	private  Grouprepo grrepo;
    
    @Autowired
    private userrepo user;
 
    @Autowired
    private amountentityrepo amtentity;
       public void save(User use,int groupcode) {
   	 
    	     Groupname group=grrepo.findByGroupid(groupcode);
    	     System.out.println(group.toString());
    	Collection<User> u=group.getUser();
    	u.add(use);
  	     grrepo.save(group);
  	     
       }

	public void savegroup(Groupname usr) {
		// TODO Auto-generated method stub
		grrepo.save(usr);
	}
	
	
	public void distribute(int groupid,double amt,int uid) {
		Groupname group=grrepo.findByGroupid(groupid);
		Collection<User> uname=(group.getUser());
		
		
		
		User userold=user.findByUid(uid);
		Collection<amountentity> amount=new ArrayList<amountentity>(userold.getAmt());
		int l=uname.size();
		 amt=amt/l;
		System.out.println("kj"+amount.toString());
		if(amount.isEmpty()) {
			System.out.println("klj");
		for(User name:uname) {
			Collection<amountentity> useramt=new ArrayList<amountentity>(name.getAmt());
			if(name.getUid()==uid)
				continue;
				System.out.println("user"+name.getUsername());
				amountentity am=new amountentity();
				am.setUid(name.getUid());
				am.setAmount(-amt); 
				System.out.println("lkj"+am.toString()+" "+amt);
				amtentity.save(am);
				System.out.println(am.toString());
				amount.add(am);
				userold.setAmt(amount);
				System.out.println("lk:"+userold.getAmt().toString());
				user.save(userold);
				System.out.println("kj"+userold.getAmt().toString());
				System.out.println("useramt"+useramt.toString());
				if(useramt.isEmpty()) {
					System.out.println("in");
					amountentity uamt=new amountentity();
					uamt.setUid(uid);
					uamt.setAmount(amt);
					amtentity.save(uamt);
					useramt.add(uamt);
					name.setAmt(useramt);
					user.save(name);
					System.out.println("useram"+name.toString());
				}
			}		
		}else {
			for(User name:uname) {
				if(name.getUid()==uid)
					continue;
			       Collection<amountentity> a=new ArrayList<amountentity>(userold.getAmt());
			       Collection<amountentity> at=name.getAmt();
			      boolean flag=true;
			      if(!amount.isEmpty())
			       for(amountentity amu:a) {
			    	int k=amu.getUid();
			    			int r=name.getUid();
			    			if(k==r) {
			    				flag=false;
			    				 amountentity am=new amountentity();
						    	 am.setUid(amu.getUid());
						    	am.setAmount( amu.getAmount()-amt);
						    	amtentity.save(am);
						    	amount.add(am);
						    	userold.setAmt(amount);
						    	user.save(userold);
						    	  amountentity a2=new amountentity();
						    	   a2.setUid(uid);
						    	   a2.setAmount(amu.getAmount()-amt);
						    	   amtentity.save(a2);
						    	   at.add(a2);
						    	   name.setAmt(at);
						    	   user.save(name);
						    	
			    			}
			    			}
			      
			       if(flag) {
			    	   amountentity a1=new amountentity();
			    	   a1.setUid(name.getUid());
			    	   a1.setAmount(-amt);
			    	   amtentity.save(a1);
			    	   amount.add(a1);
			    	   userold.setAmt(amount);
			    	   user.save(userold);
			    	   amountentity a2=new amountentity();
			    	   a2.setUid(uid);
			    	   a2.setAmount(amt);
			    	   amtentity.save(a2);
			    	   at.add(a2);
			    	   name.setAmt(at);
			    	   user.save(name);
			       }
			       }
			}
	}
	
	
	               public ArrayList<ArrayList<String>>borrow(int uid,int groupcode) {
	            	  Groupname group=grrepo.findByGroupid(groupcode);
	            	  Collection<User> u=(group.getUser());
	            	  int n=u.size();
	            	  System.out.println("n"+n);
	            	  double array[]=new double[n];
	            	  
	            	  User use=user.findByUid(uid);
	            	   ArrayList<String> borrow=new ArrayList<String>();
	            		 ArrayList<String> get=new ArrayList<>();
	            		
	            			
	            		
	            	  Collection<amountentity> a=use.getAmt();
	            	  System.out.println("use"+use.getAmt());
	            	  if(a!=null) {
	            	  for(amountentity j:a) {
	            		  System.out.println("j"+j.getUid());
	            		  array[j.getUid()-1]=j.getAmount();
	            	  }
	            	  }
	            	  System.out.println("kj");
	            	  for(int i=0;i<n;i++) {
	            		  if(i==uid-1)
	            			  continue;
	            		  User us=user.findByUid(i+1);
	            		  System.out.println("k"+i+" "+array[i]);
	            		  if(array[i]>0.0) {
	            			  System.out.println("i");
	            			  borrow.add(us.getUsername());
		            			borrow.add(String.valueOf(array[i]));
		            	
		            		
	            			 
	            		  }else if(array[i]<0.0) {
	            				get.add(us.getUsername());
		            			get.add(String.valueOf(Math.abs(array[i])));
	            			  
	            		  }
	            		  System.out.println(i+1+" "+array[i]);
	            		  System.out.println("b"+borrow);
	            		  System.out.println("b"+get);
	            	  }
	             ArrayList<ArrayList<String >> answer=new ArrayList<>();
	             answer.add(borrow);
	             answer.add(get);
	             return answer;
	               }
	               
	               
	               
	               public void exactdistribute(exactamount exact,int uid,int groupid) {
	            	   Groupname group=grrepo.findByGroupid(groupid);
	           		Collection<User> uname=(group.getUser());
	           		
	           		
	           		
	           		User userold=user.findByUid(uid);
	           		Collection<amountentity> amount=new ArrayList<amountentity>(userold.getAmt());
	           		User us=user.findByUsername(exact.getUsername());
	           		if(us!=null) {
	           		int l=us.getUid();
	           		 double amt=exact.getAmount();
	           		System.out.println("kj"+amount.toString());
	           		if(amount.isEmpty()) {
	           			System.out.println("klj");
	           		for(User name:uname) {
	           			Collection<amountentity> useramt=new ArrayList<amountentity>(name.getAmt());
	           			if(name.getUid()==uid)
	           				continue;
	           				System.out.println("user"+name.getUsername());
	           				if(name.getUid()==l) {
	           				amountentity am=new amountentity();
	           				am.setUid(name.getUid());
	           				am.setAmount(-amt); 
	           				System.out.println("lkj"+am.toString()+" "+amt);
	           				amtentity.save(am);
	           				System.out.println(am.toString());
	           				amount.add(am);
	           				userold.setAmt(amount);
	           				System.out.println("lk:"+userold.getAmt().toString());
	           				user.save(userold);
	           				System.out.println("kj"+userold.getAmt().toString());
	           				System.out.println("useramt"+useramt.toString());
	           				if(useramt.isEmpty()) {
	           					System.out.println("in");
	           					amountentity uamt=new amountentity();
	           					uamt.setUid(uid);
	           					uamt.setAmount(amt);
	           					amtentity.save(uamt);
	           					useramt.add(uamt);
	           					name.setAmt(useramt);
	           					user.save(name);
	           					System.out.println("useram"+name.toString());
	           				}
	           				}
	           			}		
	           		}else {
	           			for(User name:uname) {
	           				if(name.getUid()==uid)
	           					continue;
	           				if(name.getUid()==l) {
	           			       Collection<amountentity> a=new ArrayList<amountentity>(userold.getAmt());
	           			       Collection<amountentity> at=name.getAmt();
	           			      boolean flag=true;
	           			      if(!amount.isEmpty())
	           			       for(amountentity amu:a) {
	           			    	int k=amu.getUid();
	           			    			int r=name.getUid();
	           			    			if(k==r) {
	           			    				flag=false;
	           			    				 amountentity am=new amountentity();
	           						    	 am.setUid(amu.getUid());
	           						    	am.setAmount( amu.getAmount()-amt);
	           						    	amtentity.save(am);
	           						    	amount.add(am);
	           						    	userold.setAmt(amount);
	           						    	user.save(userold);
	           						    	  amountentity a2=new amountentity();
	           						    	   a2.setUid(uid);
	           						    	   a2.setAmount(amu.getAmount()-amt);
	           						    	   amtentity.save(a2);
	           						    	   at.add(a2);
	           						    	   name.setAmt(at);
	           						    	   user.save(name);
	           						    	
	           			    			}
	           			    			}
	           			      
	           			       if(flag) {
	           			    	   amountentity a1=new amountentity();
	           			    	   a1.setUid(name.getUid());
	           			    	   a1.setAmount(-amt);
	           			    	   amtentity.save(a1);
	           			    	   amount.add(a1);
	           			    	   userold.setAmt(amount);
	           			    	   user.save(userold);
	           			    	   amountentity a2=new amountentity();
	           			    	   a2.setUid(uid);
	           			    	   a2.setAmount(amt);
	           			    	   amtentity.save(a2);
	           			    	   at.add(a2);
	           			    	   name.setAmt(at);
	           			    	   user.save(name);
	           			       }
	           			       }
	           			}
	            	 
	               }
	           		}
}
	               
	               
	               public void percentdistribute(percentagehelper percent,int uid,int groupid,int amt) {
	            	   System.out.println(percent.toString());
	            	   Collection <Percentage> per=percent.getPercent();
	            	   for(Percentage p:per) {
	            		   Groupname group=grrepo.findByGroupid(groupid);
	   	           		Collection<User> uname=(group.getUser());
	   	           		
	   	           		
	   	           		System.out.println("l"+p.getUsername());
	   	           		User userold=user.findByUid(uid);
	   	           		Collection<amountentity> amount=new ArrayList<amountentity>(userold.getAmt());
	   	           		User us=user.findByUsername(p.getUsername());
	   	           	if(us!=null) {
	   	           		int l=us.getUid();
	   	           		
	   	           		 double amut=amt-((amt*p.getPercentage())/100);
	   	           		System.out.println("kj"+amount.toString());
	   	           		if(amount.isEmpty()) {
	   	           			System.out.println("klj");
	   	           		for(User name:uname) {
	   	           			Collection<amountentity> useramt=new ArrayList<amountentity>(name.getAmt());
	   	           			if(name.getUid()==uid)
	   	           				continue;
	   	           				System.out.println("user"+name.getUsername());
	   	           				if(name.getUid()==l) {
	   	           				amountentity am=new amountentity();
	   	           				am.setUid(name.getUid());
	   	           				am.setAmount(-amut); 
	   	           				System.out.println("lkj"+am.toString()+" "+amt);
	   	           				amtentity.save(am);
	   	           				System.out.println(am.toString());
	   	           				amount.add(am);
	   	           				userold.setAmt(amount);
	   	           				System.out.println("lk:"+userold.getAmt().toString());
	   	           				user.save(userold);
	   	           				System.out.println("kj"+userold.getAmt().toString());
	   	           				System.out.println("useramt"+useramt.toString());
	   	           				if(useramt.isEmpty()) {
	   	           					System.out.println("in");
	   	           					amountentity uamt=new amountentity();
	   	           					uamt.setUid(uid);
	   	           					uamt.setAmount(amut);
	   	           					amtentity.save(uamt);
	   	           					useramt.add(uamt);
	   	           					name.setAmt(useramt);
	   	           					user.save(name);
	   	           					System.out.println("useram"+name.toString());
	   	           				}
	   	           				}
	   	           			}		
	   	           		}else {
	   	           			for(User name:uname) {
	   	           				if(name.getUid()==uid)
	   	           					continue;
	   	           				if(name.getUid()==l) {
	   	           			       Collection<amountentity> a=new ArrayList<amountentity>(userold.getAmt());
	   	           			       Collection<amountentity> at=name.getAmt();
	   	           			      boolean flag=true;
	   	           			      if(!amount.isEmpty())
	   	           			       for(amountentity amu:a) {
	   	           			    	int k=amu.getUid();
	   	           			    			int r=name.getUid();
	   	           			    			if(k==r) {
	   	           			    				flag=false;
	   	           			    				 amountentity am=new amountentity();
	   	           						    	 am.setUid(amu.getUid());
	   	           						    	am.setAmount( amu.getAmount()-amt);
	   	           						    	amtentity.save(am);
	   	           						    	amount.add(am);
	   	           						    	userold.setAmt(amount);
	   	           						    	user.save(userold);
	   	           						    	  amountentity a2=new amountentity();
	   	           						    	   a2.setUid(uid);
	   	           						    	   a2.setAmount(amu.getAmount()-amt);
	   	           						    	   amtentity.save(a2);
	   	           						    	   at.add(a2);
	   	           						    	   name.setAmt(at);
	   	           						    	   user.save(name);
	   	           						    	
	   	           			    			}
	   	           			    			}
	   	           			      
	   	           			       if(flag) {
	   	           			    	   amountentity a1=new amountentity();
	   	           			    	   a1.setUid(name.getUid());
	   	           			    	   a1.setAmount(-amt);
	   	           			    	   amtentity.save(a1);
	   	           			    	   amount.add(a1);
	   	           			    	   userold.setAmt(amount);
	   	           			    	   user.save(userold);
	   	           			    	   amountentity a2=new amountentity();
	   	           			    	   a2.setUid(uid);
	   	           			    	   a2.setAmount(amt);
	   	           			    	   amtentity.save(a2);
	   	           			    	   at.add(a2);
	   	           			    	   name.setAmt(at);
	   	           			    	   user.save(name);
	   	           			       }
	   	           			       }
	   	           			}
	   	            	 
	   	               }
	   	           		}
	            	   }
	               }
}
