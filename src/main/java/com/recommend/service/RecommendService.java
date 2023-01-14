package com.recommend.service;

import com.recommend.bean.Recommend;
import com.recommend.bean.User;
import com.recommend.repository.RecommendRepository;
import com.recommend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecommendService {

    @Autowired
    public RecommendRepository recommendRepository;

    public Map<String, Float> getRecommend(List<String> keywordList) {

        List<Recommend> recommendList = recommendRepository.getTagRecommend(keywordList);
        Map<String, List<Recommend>> recommendMap = new HashMap<>();

        for (Recommend recommend : recommendList) {
            String medicine = recommend.getMedicine();

            if (!recommendMap.containsKey(medicine)) {
                recommendMap.put(medicine, new ArrayList<>());
            }
            recommendMap.get(medicine).add(recommend);
        }

        Map<String, Float> recommendRes = new HashMap<>();

        float total = 0;
        for(String medicine : recommendMap.keySet()) {

            List<Recommend> l = recommendMap.get(medicine);
            if (l.size() == keywordList.size()) {
                float ratio = 1;
                for (Recommend recommend : l) {
                    ratio *= recommend.getFactor();
                }
                recommendRes.put(medicine, ratio);
                total += ratio;
            }
        }

        if (total > 0) {
            for (String medicine : recommendRes.keySet()) {
                recommendRes.put(medicine, recommendRes.get(medicine) / total);
            }
        }

        return recommendRes;
    }
}
