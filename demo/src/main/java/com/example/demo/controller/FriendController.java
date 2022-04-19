package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.Map;


//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;

import com.example.demo.Repository.Friendrepo;

import com.example.demo.model.Editlist;
import com.example.demo.model.Friend;
import com.example.demo.model.Initialize;
import com.example.demo.model.Profile;
import com.example.demo.model.ProfileObj;
import com.example.demo.model.Result2;
import com.example.demo.service.ProfileObjService;
import com.example.demo.service.ProfileService;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FriendController {
    @Autowired
    private Friendrepo friendrepo;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProfileObjService profileObjService;
    
    

//通过id添加朋友（bug）
    @PostMapping(value = "addfriend")
    public Result2 befriend(@RequestBody Editlist edit){
        Map<String,String> hostid=new HashMap<String,String>();
        hostid.put("username", edit.getUserid());
        Profile person = profileService.findSearch(hostid);
        ProfileObj pic=profileObjService.findSearch1(hostid);
        Friend fri = new Friend();
        fri.setHost(edit.getEdhost());
        fri.setEmail(person.getEmail());
        fri.setName(person.getUsername());
        fri.setPicture_large(pic.getThumburl());
        fri.setPicture_medium(pic.getThumburl());
        fri.setPicture_thumbnail(pic.getThumburl());

        friendrepo.save(fri);
        /*
        Map<String,String> userid=new HashMap<String,String>();
        userid.put("username", edit.getEdhost());
        Profile foruser =profileService.findSearch(userid);
        ProfileObj userpic=profileObjService.findSearch1(userid);
        Friend userfri=new Friend();
        userfri.setHost(edit.getUserid());
        userfri.setEmail(foruser.getEmail());
        userfri.setName(foruser.getUsername());
        userfri.setPicture_large(userpic.getThumburl());
        userfri.setPicture_medium(userpic.getThumburl());
        userfri.setPicture_thumbnail(userpic.getThumburl());
        friendrepo.save(userfri);
        */
        return new Result2(1);
    }

//通过id删除朋友
    @PostMapping(value = "removefriend")
    public Result2 goaway(@RequestBody Editlist edit){
        friendrepo.deleteById(edit.getUserid());
        return new Result2(1);
    }

//查看朋友列表
    @PostMapping(value = "friendlist")
    public List<Friend> getfriends(@RequestBody Initialize ini){
        return friendrepo.findAllByhost(ini.getInhost());
    }



 


}

