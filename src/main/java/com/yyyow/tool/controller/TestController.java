package com.yyyow.tool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {


    @RequestMapping("/test")
    public String test() {
        return "test";
    }

}
