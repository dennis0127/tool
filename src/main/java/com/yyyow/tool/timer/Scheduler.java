package com.yyyow.tool.timer;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yyyow.tool.model.BAPriceInfo;
import com.yyyow.tool.model.HBInfoDto;
import com.yyyow.tool.model.HBReturnInfoDto;
import com.yyyow.tool.model.MyPriceModel;
import com.yyyow.tool.service.IHBService;
import com.yyyow.tool.util.DingUtil;
import com.yyyow.tool.util.RedisUtil;
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

    @Autowired
    RedisUtil redisUtil;

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

//    @Scheduled(cron = "0 */1 * * * ?") //每分钟执行一次
    public void getBAInfo() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String result = HttpUtil.get(BABASEURL + GETNEWPRICE, 6000);
            List<BAPriceInfo> list = JSON.parseArray(result, BAPriceInfo.class);
            if (null == list || list.size() == 0) {
                return;
            }
            for (BAPriceInfo baPriceInfo : list) {
                switch (baPriceInfo.getSymbol()) {
                    case "ATOMUSDT":
                        stringBuilder.append("### ATOM\n");
                        stringBuilder.append("价格: " + baPriceInfo.getPrice() + "\n");
                        break;
                    case "BTCUSDT":
                        stringBuilder.append("### BTC\n");
                        stringBuilder.append("价格: " + baPriceInfo.getPrice() + "\n");
                        break;
                    case "BNBUSDT":
                        stringBuilder.append("### BNB\n");
                        stringBuilder.append("价格: " + baPriceInfo.getPrice() + "\n");
                        break;
                    case "DOGEUSDT":
                        stringBuilder.append("### DOGE\n");
                        stringBuilder.append("价格: " + baPriceInfo.getPrice() + "\n");
                        break;
                    case "ETHUSDT":
                        stringBuilder.append("### ETH\n");
                        stringBuilder.append("价格: " + baPriceInfo.getPrice() + "\n");
                        break;
                    case "LTCUSDT":
                        stringBuilder.append("### LTC\n");
                        stringBuilder.append("价格: " + baPriceInfo.getPrice() + "\n");
                        break;
                    case "XRPUSDT":
                        stringBuilder.append("### XRP\n");
                        stringBuilder.append("价格: " + baPriceInfo.getPrice() + "\n");
                        break;
                    default:
                        break;
                }
            }
            DingUtil.SendMsg("markdown", "消息提醒", stringBuilder.toString());
            log.info("执行结束 {}");

        } catch (Exception e) {
            DingUtil.SendMsg("markdown", "消息提醒", e.getMessage());

        }

    }

//    @Scheduled(cron = "*/30 * * * * ?") //每分钟执行一次
    public void getMyPriceMsg() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String result = HttpUtil.get(BABASEURL + GETNEWPRICE, 6000);
            List<BAPriceInfo> list = JSON.parseArray(result, BAPriceInfo.class);
            if (null == list || list.size() == 0) {
                return;
            }
            for (BAPriceInfo baPriceInfo : list) {
                switch (baPriceInfo.getSymbol()) {
                    case "ATOMUSDT":
                        stringBuilder.append("### ATOM\n");
                        baMsgHandle(stringBuilder, baPriceInfo);
                        break;
                    case "BTCUSDT":
                        stringBuilder.append("### BTC\n");
                        baMsgHandle(stringBuilder, baPriceInfo);
                        break;
                    case "BNBUSDT":
                        stringBuilder.append("### BNB\n");
                        baMsgHandle(stringBuilder, baPriceInfo);
                        break;
                    case "DOGEUSDT":
                        stringBuilder.append("### DOGE\n");
                        baMsgHandle(stringBuilder, baPriceInfo);
                        break;
                    case "ETHUSDT":
                        stringBuilder.append("### ETH\n");
                        baMsgHandle(stringBuilder, baPriceInfo);
                        break;
                    case "LTCUSDT":
                        stringBuilder.append("### LTC\n");
                        baMsgHandle(stringBuilder, baPriceInfo);
                        break;
                    case "XRPUSDT":
                        stringBuilder.append("### XRP\n");
                        baMsgHandle(stringBuilder, baPriceInfo);
                        break;
                    default:
                        break;
                }
            }
            String s = stringBuilder.toString();
            if (s.contains("高位") || s.contains("低位")) {
                DingUtil.SendMsg("markdown", "消息提醒", stringBuilder.toString());
            }

            log.info("执行结束 {}");

        } catch (Exception e) {
            log.error("出现异常 {}", e.getMessage());
//            DingUtil.SendMsg("markdown", "异常提醒", e.getMessage());

        }

    }

    void baMsgHandle(StringBuilder sb, BAPriceInfo baPriceInfo) {
        if (null != redisUtil.get("lxd")) {
//            String lxd = redisUtil.get("lxd");
            try {
                List<MyPriceModel> lists = (List<MyPriceModel>) redisUtil.get("lxd");
                if (null != lists || lists.size() > 0) {
                    lists.forEach(
                            i -> {
                                if (baPriceInfo.getSymbol().equals(i.getSymbol())) {
                                    if (null != i.getMaxPrice()) {
                                        if (baPriceInfo.getPrice() > i.getMaxPrice()) {
                                            sb.append("高位预警 当前价格: " + baPriceInfo.getPrice() +"\t 设置价格: "+i.getMaxPrice()+ "\n");

                                        }
                                    }
                                    if (null != i.getMinPrice()) {
                                        if (baPriceInfo.getPrice() < i.getMinPrice()) {
                                            sb.append("低位预警 当前价格: " + baPriceInfo.getPrice() +"\t 设置价格: "+i.getMinPrice()+ "\n");
                                        }
                                    }
                                }

                            }
                    );
                }
            } catch (Exception e) {
                log.error("处理自定义价格失败 {}", e.getMessage());
            }
        }
        if (null != redisUtil.get("wkx")) {
//            String wkx = redisUtil.get("wkx").toString();
            try {
                List<MyPriceModel> lists = (List<MyPriceModel>) redisUtil.get("wkx");
                if (null != lists || lists.size() > 0) {
                    lists.forEach(
                            i -> {
                                if (baPriceInfo.getSymbol().equals(i.getSymbol())) {
                                    if (null != i.getMaxPrice()) {
                                        if (baPriceInfo.getPrice() > i.getMaxPrice()) {
                                            sb.append("高位预警 当前价格: " + baPriceInfo.getPrice() +"\t 设置价格: "+i.getMaxPrice()+ "\n");
                                        }
                                    }
                                    if (null != i.getMinPrice()) {
                                        if (baPriceInfo.getPrice() < i.getMinPrice()) {
                                            sb.append("低位预警 当前价格: " + baPriceInfo.getPrice() +"\t 设置价格: "+i.getMinPrice()+ "\n");
                                        }
                                    }
                                }
                            }
                    );
                }
            } catch (Exception e) {
                log.error("处理自定义价格失败 {}", e.getMessage());
            }
        }

    }


    //    @Scheduled(cron = "0 0/1 * * * ?") //每分钟执行一次
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

        DingUtil.SendMsg("markdown", "消息", stringBuilder.toString());

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
