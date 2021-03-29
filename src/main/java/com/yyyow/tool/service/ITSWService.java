package com.yyyow.tool.service;

import com.yyyow.tool.model.TSWSearchModel;

import java.util.List;
import java.util.Map;

public interface ITSWService {
    /**
     * TSW search
     * @param tswSearchModel
     * @return
     */
    List<Map> getSWList(TSWSearchModel tswSearchModel);
}
