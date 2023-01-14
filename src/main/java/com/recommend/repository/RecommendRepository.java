package com.recommend.repository;

import com.recommend.bean.Recommend;
import com.recommend.util.DatabaseUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecommendRepository {

    public List<Recommend> getTagRecommend(List<String> keywordList) {

        List<Recommend> recommendsList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.connect();
            stmt = conn.createStatement();

            for(String keyword: keywordList) {

                String sql = "select * from tag_recommend where tag=\"" + keyword + "\"";
                System.out.println(sql);
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String medicine = rs.getString("medicine");
                    float ratio = rs.getFloat("ratio");
                    Recommend recommend = new Recommend();
                    recommend.setMedicine(medicine);
                    recommend.setFactor(ratio);
                    recommend.setKeyword(keyword);
                    recommendsList.add(recommend);
                }
            }

            System.out.println(recommendsList.size());
            return recommendsList;
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            DatabaseUtil.close(rs, stmt, conn);
        }
        return recommendsList;
    }
}
