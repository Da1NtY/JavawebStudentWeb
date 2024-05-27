package controller;

import db.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import object.User;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long SerialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 从请求中获取用户注册信息
        String userid = request.getParameter("userid");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // 创建User对象并设置用户信息
        User user = new User();
        user.setUserid(userid);
        user.setUsername(username);
        user.setUserpwd(password);
        user.setUserrole(role);

        // 创建UserDB实例并将用户信息添加到数据库中
        UserDB userDB = new UserDB();
        userDB.adduser(user);
        // 注册完成后重定向到Login.jsp页面
        response.sendRedirect("Login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

