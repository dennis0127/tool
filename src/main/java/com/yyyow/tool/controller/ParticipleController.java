package com.yyyow.tool.controller;

import com.yyyow.tool.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ansj.splitWord.analysis.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/participle")
@Api(tags = "分词管理")
public class ParticipleController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "精准分词", notes = "精准分词", httpMethod = "GET")
    @GetMapping("/toAnalysis")
    public R toAnalysis(String word) {
        return R.success(ToAnalysis.parse(word).getTerms());
    }

    @ApiOperation(value = "nlp分词", notes = "nlp分词", httpMethod = "GET")
    @GetMapping("/nlpAnalysis")
    public R nlpAnalysis(String word) {
        return R.success(NlpAnalysis.parse(word).getTerms());
    }

    @ApiOperation(value = "索引分词", notes = "索引的分词", httpMethod = "GET")
    @GetMapping("/indexAnalysis")
    public R indexAnalysis(String word) {
        return R.success(IndexAnalysis.parse(word).getTerms());
    }

}
