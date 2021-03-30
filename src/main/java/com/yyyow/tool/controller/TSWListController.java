package com.yyyow.tool.controller;

import com.yyyow.tool.model.TSWGoodsModel;
import com.yyyow.tool.service.ITSWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TSWListController {

    @Autowired
    ITSWService itswService;

    @GetMapping("/tsw/list")
    public String list (Model model) {
        List<TSWGoodsModel> swList = itswService.getSWList(null);
        model.addAttribute("tsws",swList);
        return "tsw/list";
    }
}
