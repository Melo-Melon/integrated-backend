package com.example.demo.controller;


//import entity.StatusCode;


//import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
*/
import java.util.Map;

import com.example.demo.model.Profile;
import com.example.demo.model.ProfileObj;
import com.example.demo.model.Result2;
import com.example.demo.service.ProfileObjService;
import com.example.demo.service.ProfileService;
import com.example.demo.util.Result3;
//控制层，用于对控制方法的转发，最终转发到业务层
@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProfileObjService profileObjService;

    @PostMapping(value="/edit")
    public Result2 update(@RequestBody Profile profile){
//    public Result2 update(@PathVariable String username, @RequestBody Map<String, Object> user){
            String username = profile.getUsername();

            ProfileObj p = profile.getAvatar().get(0);
            p.setUsername(username);
            profileObjService.updateFile(p);
            profile.setUsername(username);
            profileService.update(profile);
//        p1.setUsername(username);
//        p1.setIntro(user.get("intro").toString());
//        p1.setEmail(user.get("gender").toString());
//        p1.setGender(user.get("gender").toString());
//        profileService.update(p1);
//
//        Array a = (Array) user.get("avatar");
//        ProfileObj p2 = (ProfileObj) a.getElement();
//        p2.setUsername("username");

        return new Result2(1);
    }

    @PostMapping()
    public Result3 findSearch(@RequestBody Map searchMap){
        Profile p = profileService.findSearch(searchMap);
        return new Result3(p.getUsername(), p.getGender(), p.getEmail(), p.getIntro(), profileObjService.findSearch1(searchMap));
    }


}
