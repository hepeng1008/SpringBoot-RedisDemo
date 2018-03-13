package com.example.controller;

import com.example.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private RedisDao redisDao;

    @RequestMapping("/set")
    public String set(String key,String value){
        redisDao.setKey(key,value);
        return "success";
    }


    @RequestMapping("/get")
    public String get(String key){
        return redisDao.getValue(key);
    }
}
