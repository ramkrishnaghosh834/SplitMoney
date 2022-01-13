package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface amountentityrepo extends JpaRepository<amountentity,Integer>{

	amountentity findByUid(int n);
}
