package com.yyyow.tool.controller;

import com.yyyow.tool.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/sw")
@Api(tags = "sw Account List")
public class SwController {


    @ApiOperation(value = "list", notes = "list", httpMethod = "GET")
    @GetMapping
    @ResponseBody
    public R list() {



        return R.success();
    }
}
