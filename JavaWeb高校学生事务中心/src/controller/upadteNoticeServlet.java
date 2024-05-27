package controller;

import db.NoticeDB;
import object.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateNoticeServlet")
public class upadteNoticeServlet extends HttpServlet {
    private static final long SerialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        // 获取表单数据
        String noticeId = request.getParameter("noticeId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // 创建 Notice 对象
        Notice notice = new Notice();
        notice.setNoticeid(noticeId);
        notice.setTitle(title);
        notice.setContent(content);

        // 调用数据库操作类更新公告信息
        NoticeDB noticeDB = new NoticeDB();
        boolean success = noticeDB.updateNotice(notice);

        if (success) {
            // 更新成功，重定向回管理员公告页面或其他页面
            response.sendRedirect("adminNotice.jsp");
        } else {
            // 更新失败，可重定向到错误页面或其他处理方式
            response.sendRedirect("error.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
