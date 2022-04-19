package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import com.example.demo.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AccountRepo extends JpaRepository<Account,String> {
    
}
