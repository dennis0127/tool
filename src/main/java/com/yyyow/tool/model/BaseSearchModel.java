package com.yyyow.tool.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 搜索入参基类
 */
public class BaseSearchModel implements Serializable {
    @ApiModelProperty("页码")
    private Integer pageNo;

    @ApiModelProperty("条数")
    private Integer pageSize;

}
