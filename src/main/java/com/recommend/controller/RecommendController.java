package com.recommend.controller;

import com.recommend.service.RecommendService;
import com.recommend.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @PostMapping("/medicine")
    public JsonData getRecommend(@RequestBody String keywords) {

        String []list = keywords.substring(1, keywords.length() - 1).split(",");
        System.out.println(keywords);
        List<String> keywordList = Arrays.asList(list);
        return JsonData.buildSuccess(recommendService.getRecommend(keywordList));
    }

}
