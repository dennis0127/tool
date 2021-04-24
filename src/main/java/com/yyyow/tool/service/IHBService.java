package com.yyyow.tool.service;

import com.yyyow.tool.model.HBReturnInfoDto;

public interface IHBService {
    /**
     * 查询hb信息
     * @param url
     * @return
     */
    HBReturnInfoDto getHBInfo(String url);
}
