package com.yyyow.tool.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yyyow.tool.model.MyPriceModel;
import com.yyyow.tool.util.R;
import com.yyyow.tool.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/price")
public class MyPriceController {

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/update")
    public R update(@RequestBody List<MyPriceModel> prices, String key) {
        redisUtil.set(key, prices);
        return R.success();
    }
}
