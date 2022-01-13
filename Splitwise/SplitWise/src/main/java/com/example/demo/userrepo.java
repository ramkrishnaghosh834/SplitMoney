package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
//@Repository
public interface userrepo extends JpaRepository<User,Long> {
	
 User findByUsername(String s);

 User findByUid(int uid) ;
	
}
