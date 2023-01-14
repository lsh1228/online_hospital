package com.recommend.test;

import com.recommend.bean.User;
import com.recommend.service.RecommendService;
import com.recommend.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

public class RecommendTest {

    @Autowired
    RecommendService recommendService;

    @Test
    public void testGetRecommend() {
        Map<String, Float>  map = recommendService.getRecommend(null);
        assert(map != null);

        map = recommendService.getRecommend(Arrays.asList("发热"));
        assert(map != null);
    }

}
