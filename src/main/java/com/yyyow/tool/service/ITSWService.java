package com.yyyow.tool.service;

import com.yyyow.tool.model.TSWGoodsModel;
import com.yyyow.tool.model.TSWSearchModel;

import java.util.List;

public interface ITSWService {
    /**
     * TSW search
     * @param tswSearchModel
     * @return
     */
    List<TSWGoodsModel> getSWList(TSWSearchModel tswSearchModel);
}
