package com.recommend.repository;

import com.recommend.bean.Role;
import com.recommend.bean.User;
import com.recommend.util.DatabaseUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository {

    public String getUserPassword(String userName) {
        if (userName == null) {
            return null;
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.connect();
            stmt = conn.createStatement();
            String sql = "select * from hospital_user where name=\"" + userName + "\"";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getString("password");
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            DatabaseUtil.close(rs, stmt, conn);
        }
        return null;
    }

    public boolean createUser(User user) {

        if (user.getName() == null || user.getPassword() == null) {
            return false;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.connect();
            String sql = "insert into hospital_user(name,password, sex, age, role) values(?, ?, ?, ?, ?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getSex());
            pstmt.setInt(4, user.getAge());
            pstmt.setString(5, String.valueOf(user.getRole()));

            pstmt.execute();
            return pstmt.getUpdateCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstmt, conn);
        }

        return false;
    }

    public boolean delete(String userName) {

        if (userName== null) {
            return false;
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.connect();
            String sql = "delete from hospital_user where name=" + userName;
            stmt = conn.createStatement();
            stmt.execute(sql);

            return stmt.getUpdateCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, stmt, conn);
        }

        return false;
    }

    public User getUser(String userName) {
        if (userName == null) {
            return null;
        }

        User user = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.connect();
            stmt = conn.createStatement();
            String sql = "select * from user where name=\"" + userName + "\"";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getInt("sex"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            DatabaseUtil.close(rs, stmt, conn);
        }
        return user;
    }
}
