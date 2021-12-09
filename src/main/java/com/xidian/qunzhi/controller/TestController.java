package com.xidian.qunzhi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: CaoStudy
 * @date: 2021-12-01 22:17
 * @desc: 测试用Controller
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/a")
    public String searchByAdmin(){
        return "a";
    }
}
