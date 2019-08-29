package com.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo2 {

    @RequestMapping(value = "/aa")
    public String aa(){
        return "aa";
    }
}
