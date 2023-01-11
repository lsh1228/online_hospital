package com.recommend.util;


/**
 * DatabaseUtils.java
 */
import java.sql.*;

public class DatabaseUtil {
    private static final String url;
    private static final String dbUser;
    private static final String dbPass;

    static {
        url = "jdbc:mysql://127.0.0.1:3306/recommend_db"
                + "?useUnicode=true&characterEncoding=utf-8"
                + "&useSSL=false&serverTimezone=Asia/Shanghai";
        dbUser = "root";
        dbPass = "root";
    }

    public static void main(String[] args) throws Exception{
        Connection conn = DatabaseUtil.connect();
        DatabaseMetaData meta = conn.getMetaData();
        System.out.print(meta.getDatabaseProductName()+" ");
        System.out.print(meta.getDatabaseProductVersion());
        DatabaseUtil.close(conn);
    }

    public static Connection connect() throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,dbUser,dbPass);
        return conn;
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs!=null)
            try{rs.close();}catch(Exception ignore){ }
        if(stmt!=null){
            try{stmt.close();}catch(Exception ignore){ }
        }
        if(conn!=null){
            try{conn.close();}catch(Exception ignore){ }
        }
    }
    public static void close(Statement pstmt,Connection conn) {
        close(null, pstmt, conn);
    }
    public static void close(Connection conn) {
        close(null, null, conn);
    }
}




