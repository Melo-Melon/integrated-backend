package com.example.demo.dao;

//import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;

//import java.util.List;
//import java.util.Map;

import com.example.demo.model.Profile;
//连接池，一般用于对数据库的操作，自带的有增删改查，这里可以添加自己的特殊方法，类似于关注好友等等，多表联查等等
public interface ProfileDao extends JpaRepository<Profile, String>, JpaSpecificationExecutor<Profile> {


}