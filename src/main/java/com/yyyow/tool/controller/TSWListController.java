package com.yyyow.tool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TSWListController {

    @GetMapping("/tsw/list")
    public String list () {
        return "tsw/list";
    }
}
