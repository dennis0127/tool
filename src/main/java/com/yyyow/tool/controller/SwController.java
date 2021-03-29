package com.yyyow.tool.controller;

import com.yyyow.tool.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sw")
@Api(tags = "sw Account List")
public class SwController {



    @ApiOperation(value = "list", notes = "list", httpMethod = "GET")
    @GetMapping
    public R list() {



        return R.success();
    }
}
