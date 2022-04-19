package com.example.demo.Repository;

import java.util.List;
//import java.util.Optional;

import com.example.demo.model.Friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface Friendrepo extends JpaRepository<Friend, String>{

    public List<Friend> findAllByhost(String string);
    
}
