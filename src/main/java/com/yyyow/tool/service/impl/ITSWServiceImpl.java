package com.yyyow.tool.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yyyow.tool.config.SiteUrl;
import com.yyyow.tool.model.TSWGoodsModel;
import com.yyyow.tool.model.TSWSearchModel;
import com.yyyow.tool.service.ITSWService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ITSWServiceImpl implements ITSWService {

    @Override
    public List<TSWGoodsModel> getSWList(TSWSearchModel tswSearchModel) {
        List<TSWGoodsModel> list = new ArrayList<>();

        HttpRequest httpRequest = new HttpRequest(SiteUrl.TSWLIST);
        httpRequest.setMethod(Method.POST);
        httpRequest.header("Cache-Control","no-cache");
        httpRequest.header("Accept","application/json, text/javascript, */*; q=0.01");
        httpRequest.header("User-Agent","Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.96 Mobile Safari/537.36");
        httpRequest.header("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        httpRequest.header("Origin","https://m.taoshouyou.com");
        httpRequest.header("Referer","https://m.taoshouyou.com/game/shenwu4-345-20-1");
        httpRequest.header("Accept-Encoding","gzip, deflate, br");
        Map<String,Object> param = new HashMap<>();
        param.put("gameid",345);
        param.put("parentgoodsid",20);
        param.put("goodsid",1);
        param.put("clientid",3);
        param.put("areaid",6923809);
        param.put("price",0);
        param.put("minPrice","");
        param.put("maxPrice","");
        param.put("mobileid",2);
        param.put("wd","");
        param.put("sort",0);
        param.put("account","");
        param.put("URL_Referer","https%3A%2F%2Fm.taoshouyou.com%2F");
        param.put("page",1);
        param.put("amount",15);
        param.put("isCertificationShop",2);
        param.put("antiAddictionStatus",0);
        param.put("isActivity",0);
        param.put("selfsupport",0);
        param.put("spelling","shenwu4");
        param.put("tags","");
        param.put("attr21","");
        param.put("attr23","");
        param.put("attr19","");
        httpRequest.form(param);
        String body = httpRequest.execute().body();
        JSONArray goodsList = JSONObject.parseObject(body).getJSONArray("goodsList");
        if (null!=goodsList && goodsList.size()>0){
            for (Object o : goodsList) {
                TSWGoodsModel tswGoodsModel = new TSWGoodsModel();
                JSONObject jsonObject = (JSONObject) o;
                tswGoodsModel.setName(jsonObject.getString("name"));
                tswGoodsModel.setBrightSpot(jsonObject.getString("bright_spot"));
                tswGoodsModel.setAreaName(jsonObject.getString("areaname"));
                tswGoodsModel.setClintName(jsonObject.getString("clientname"));
                tswGoodsModel.setLastActiveDate(jsonObject.getString("lastactivedate"));
                list.add(tswGoodsModel);
            }
        }

        return list;
    }
}
