package com.yyyow.tool.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.yyyow.tool.model.HBReturnInfoDto;
import com.yyyow.tool.service.IHBService;
import org.springframework.stereotype.Service;

@Service
public class HBServiceImpl implements IHBService {
    @Override
    public HBReturnInfoDto getHBInfo(String url) {
        String content = HttpUtil.get(url, 6000);
        return JSONObject.parseObject(content, HBReturnInfoDto.class);
    }
}
