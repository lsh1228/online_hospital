package com.recommend.repository.impl;

import com.recommend.bean.Treatment;
import com.recommend.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchRepository {

    public List<Treatment> getSearchArticle(String searchTitle) {
        List<Treatment> treatments = new ArrayList<Treatment>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DatabaseUtil.connect();
            stmt = conn.createStatement();
            String sql = "select * from treatment where title like \"%" + searchTitle + "%\"";
            rs = stmt.executeQuery(sql);
            treatments.clear();
            while(rs.next()){
                Treatment treatment = new Treatment();
                treatment.setId(rs.getLong("id"));
                treatment.setAuthor(rs.getString("author"));
                treatment.setPublish_time(rs.getString("publish_time"));
                treatment.setContent(rs.getString("content"));
                treatment.setTitle(rs.getString("title"));
                treatments.add(treatment);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DatabaseUtil.close(rs, stmt, conn);
        }
        return treatments;
    }

}
