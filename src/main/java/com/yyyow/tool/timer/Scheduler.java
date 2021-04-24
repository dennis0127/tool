package com.yyyow.tool.timer;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yyyow.tool.model.BAPriceInfo;
import com.yyyow.tool.model.HBInfoDto;
import com.yyyow.tool.model.HBReturnInfoDto;
import com.yyyow.tool.service.IHBService;
import com.yyyow.tool.util.DingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@EnableAsync
@EnableScheduling
@Component
public class Scheduler {

    @Autowired
    IHBService ihbService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String URL = "https://api.hbdm.com/linear-swap-api/v1/swap_index?contract_code=";
    private static final String BABASEURL = "https://fapi.binance.com/fapi";
    private static final String GETNEWPRICE = "/v1/ticker/price";
    private static final String LTC = "LTC-USDT";
    private static final String DOGE = "DOGE-USDT";
    private static final String XRP = "XRP-USDT";
    private static final String ATOM = "ATOM-USDT";
    private static final String BTC = "BTC-USDT";
    private static final String ETH = "ETH-USDT";

//    @Scheduled(cron = "0 0/1 * * * ?") //每分钟执行一次
    public void getBAInfo() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String result = HttpUtil.get(BABASEURL + GETNEWPRICE, 6000);
            List<BAPriceInfo> list = (List<BAPriceInfo>) JSONArray.parse(result);
            if (null == list || list.size() == 0) {
                return;
            }
            for (BAPriceInfo baPriceInfo : list) {
                switch (baPriceInfo.getSymbol()) {
                    case "BTCUSDT":

                    break;
                    default:
                        break;
                }
            }

            DingUtil.SendMsg("markdown","消息","");
            log.info("执行结束 {}");

        } catch (Exception e) {
            DingUtil.SendMsg("markdown","异常提醒",e.getMessage());

        }

    }


    @Scheduled(cron = "0 0/1 * * * ?") //每分钟执行一次
    public void getHBInfo() {
        log.info("开始执行");
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("### ATOM\n");
        HBReturnInfoDto hbReturnInfoDto = ihbService.getHBInfo(URL + ATOM);
        InfoHanle(stringBuilder, hbReturnInfoDto);

        stringBuilder.append("### BTC\n");
        hbReturnInfoDto = ihbService.getHBInfo(URL + BTC);
        InfoHanle(stringBuilder, hbReturnInfoDto);

        stringBuilder.append("### DOGE\n");
        hbReturnInfoDto = ihbService.getHBInfo(URL + DOGE);
        InfoHanle(stringBuilder, hbReturnInfoDto);

        stringBuilder.append("### ETH\n");
        hbReturnInfoDto = ihbService.getHBInfo(URL + ETH);
        InfoHanle(stringBuilder, hbReturnInfoDto);

        stringBuilder.append("### LTC\n");
        hbReturnInfoDto = ihbService.getHBInfo(URL + LTC);
        InfoHanle(stringBuilder, hbReturnInfoDto);

        stringBuilder.append("### XRP\n");
        hbReturnInfoDto = ihbService.getHBInfo(URL + XRP);
        InfoHanle(stringBuilder, hbReturnInfoDto);

        DingUtil.SendMsg("markdown","消息",stringBuilder.toString());

        log.info("执行结束 {}");

    }

    void InfoHanle(StringBuilder stringBuilder, HBReturnInfoDto hbReturnInfoDto) {
        if ("ok".equals(hbReturnInfoDto.getStatus())) {
            HBInfoDto hbInfoDto = hbReturnInfoDto.getData().get(0);
            stringBuilder.append("价格: " + hbInfoDto.getIndex_price() + "\n");
        } else {
            stringBuilder.append("获取失败: status" + hbReturnInfoDto.getStatus() + "\n");
        }
    }
}
