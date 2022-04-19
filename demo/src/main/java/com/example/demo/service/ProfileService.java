package com.example.demo.service;




//import com.example.demo.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.example.demo.dao.ProfileDao;
import com.example.demo.model.Profile;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
import java.util.*;
//业务层，用于对方法的声明以及，以及通过对连接池的调用，对数据库进行操作
@Service
@Transactional
public class ProfileService {
    @Autowired
    private ProfileDao profileDao;

    public void update(Profile profile){
        profileDao.save(profile);
    }

    public Profile findSearch(Map searchMap){
        Specification<Profile> specification = createSpecification(searchMap);
        return profileDao.findOne(specification).get();
    }



    private Specification<Profile> createSpecification(Map searchMap) {
        return new Specification<Profile>() {
            @Override
            public Predicate toPredicate(Root<Profile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (searchMap.get("username") != null && !"".equals(searchMap.get("username"))) {
                    predicateList.add(cb.like(root.get("username").as(String.class), (String) searchMap.get("username")));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

}
