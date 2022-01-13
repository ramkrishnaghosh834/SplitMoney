package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Grouprepo extends JpaRepository<Groupname,Integer> {

	 Groupname findByGroupid(int groupcode);

	Groupname findByGrpname(String s);


	


}
