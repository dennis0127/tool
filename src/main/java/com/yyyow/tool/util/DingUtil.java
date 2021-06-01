package com.yyyow.tool.util;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DingUtil {

    private static final String DDURL = "https://oapi.dingtalk.com/robot/send?access_token=bb35de98c1063cce29a319614c68c5b8033cbf20834cc7c9df6140b9e7a9297d";

    private static final Logger log = LoggerFactory.getLogger(DingUtil.class);

    public static boolean SendMsg(String msgtype, String title, String text) {
        JSONObject map = new JSONObject();
        map.put("msgtype", msgtype);
        JSONObject markdown = new JSONObject();
        markdown.put("title", title);
        markdown.put("text", text);
        map.put("markdown", markdown);
        String result = HttpUtil.post(DDURL, map.toJSONString(), 6000);
        log.info("ding send msg :{} ",result);
        return true;
    }
}
