package com.ex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "THIS IS INDEX PAGE";
    }

}
