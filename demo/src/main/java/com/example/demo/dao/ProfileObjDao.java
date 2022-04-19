package com.example.demo.dao;

import com.example.demo.model.ProfileObj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;

public interface ProfileObjDao extends JpaRepository<ProfileObj, String>, JpaSpecificationExecutor<ProfileObj> {

}
