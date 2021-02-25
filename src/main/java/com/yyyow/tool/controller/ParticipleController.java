package com.yyyow.tool.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.StringJoiner;

@RestController
@RequestMapping("/api/participle")
@Api(tags = "分词管理")
public class ParticipleController {


    @ApiOperation(value = "精准分词", notes = "精准分词", httpMethod = "GET")
    @GetMapping("/toAnalysis")
    public String toAnalysis(String word) {
        StringJoiner joiner = new StringJoiner(",");
        for (Term term : ToAnalysis.parse(word).getTerms()) {
            joiner.add(term.getName());
        }
        return joiner.toString();
    }

}
