package controller;

import db.UserDB;
import object.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long SerialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String userVCode = request.getParameter("userVCode");

        UserDB userDB1 = new UserDB();
        String userid = userDB1.setid(request.getParameter("username"));

        HttpSession session = request.getSession();
        String valiadteCode = (String) session.getAttribute("validateCode");

        session.setAttribute("username",username);
        session.setAttribute("userid",userid);

        if (userVCode.equalsIgnoreCase(valiadteCode)){
            UserDB userDB = new UserDB();
            if (userDB.isLogin(username, password, role)){
                session.setAttribute("isLogin", "true");

                // 根据不同角色进行重定向
                if ("管理员".equals(role)){
                    response.sendRedirect("adminNotice.jsp");
                } else if ("教师".equals(role)){
                    response.sendRedirect("teacherNotice.jsp");
                } else if ("学生".equals(role)){
                    response.sendRedirect("studentNotice.jsp");
                } else {
                    // 其他角色的处理
                    response.sendRedirect("index.jsp");
                }
            } else {
                session.setAttribute("isLogin", "false");
                response.sendRedirect("error.jsp");
            }
            userDB.close();
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('验证码输入错误！');history.go(-1)</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
