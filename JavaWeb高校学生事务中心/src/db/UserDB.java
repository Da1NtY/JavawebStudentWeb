package db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import object.Leave;
import object.User;

public class UserDB extends DbConn{
    // 判断用户是否登录成功的方法
    public  boolean isLogin(String userName, String userPwd, String Role){
        boolean flag = false;
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("select * from users where username = ? and password = ? and role = ?");
                pst.setString(1, userName);
                pst.setString(2, userPwd);
                pst.setString(3, Role);
                ResultSet rs = pst.executeQuery();
                if(rs.next()) {
                    flag = true; 			// 若查询结果存在，表示登录成功
                }
                rs.close();
            }
            catch(SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return flag;
    }
    // 向数据库添加新用户的方法
    public void adduser(User user)
    {
        if(conn!=null)
        {
            try
            {
                PreparedStatement ps=conn.prepareStatement("INSERT INTO users VALUES(?,?,?,?)");
                ps.setString(1,user.getUserid());
                ps.setString(2,user.getUsername());
                ps.setString(3,user.getUserpwd());
                ps.setString(4,user.getUserrole());
                ps.execute();
                System.out.println("插入成功！");		// 打印成功信息到控制台
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<User> getTeacherList() {
        List<User> TeacherList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE role = ?");
            pstmt.setString(1,"教师");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserid(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));

                TeacherList.add(user);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return TeacherList;
    }

    public List<User> getAdminList() {
        List<User> AdminList = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE role = ?");
            pstmt.setString(1,"管理员");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserid(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));

                AdminList.add(user);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return AdminList;
    }

    public String setid(String username){
        String id = "";
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserid(rs.getString("user_id"));
                id = user.getUserid();
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}